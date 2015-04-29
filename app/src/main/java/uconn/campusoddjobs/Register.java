package uconn.campusoddjobs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Register extends Activity {


    private EditText rEmailView;
    private EditText rUsernameView;
    private EditText rPasswordView;

    private Button rRegisterButton;
    private Button rBackToLoginButton;

    private ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://campusoddjobs.com/oddjobs/register.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONparser jsonParser = new JSONparser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // currently only email and password are registered
        rEmailView = (EditText) findViewById(R.id.email);
        rPasswordView = (EditText) findViewById(R.id.pword);
        // TODO implement the remaining elements in the database (and make a column for karma)
        rUsernameView = (EditText) findViewById(R.id.uname);

        rRegisterButton = (Button) findViewById(R.id.register);
        rRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (isValidEmail(rEmailView.getText().toString()) == true) {
                    new CreateUser().execute();
                }
                else{
                    Toast.makeText(getApplicationContext(),"School not yet supported!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rBackToLoginButton = (Button) findViewById(R.id.bktologin);
        rBackToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });
    }

    private void toLogin() {
        Intent intent = new Intent(Register.this, LoginActivity.class);
        Register.this.startActivity(intent);
        Register.this.finish();
    }

    // TODO support more schools, iterate through a string array of supported @ domains
    private boolean isValidEmail(String em){
        boolean validity = false;
        if (em.contains("@uconn.edu")){
            validity = true;
        }
        return validity;
    }

    class CreateUser extends AsyncTask<String, String, String> {

        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // Check for success tag
            int success;
            String email = rEmailView.getText().toString();
            String password = rPasswordView.getText().toString();
            // test add username and karma
            String username = rUsernameView.getText().toString();

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("password", password));
                params.add(new BasicNameValuePair("username", username));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // full json response
                Log.d("Login attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("User Created!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(Register.this, file_url, Toast.LENGTH_LONG).show();
            }


        }
    }
}
