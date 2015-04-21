package uconn.campusoddjobs;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AutoCompleteTextView;

public class LoginActivity extends Activity implements OnClickListener{

    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mSignIn, mRegister;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONparser jsonParser = new JSONparser();

    //php login script location:

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";

    //testing on Emulator:
    private static final String LOGIN_URL = "http://campusoddjobs.com/oddjobs/login.php";

    //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";

    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //setup input fields
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        //setup buttons
        mSignIn = (Button)findViewById(R.id.email_sign_in_button);
        mRegister = (Button)findViewById(R.id.email_register_button);

        //register listeners
        mSignIn.setOnClickListener(this);
        mRegister.setOnClickListener(this);

            // -- Temporary login verification bypass --
            Button devBypass = (Button) findViewById(R.id.developer_bypass);
            devBypass.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    //LoginActivity.this.finish();
                }
            });
            // -- end --
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                new AttemptLogin().execute();
                break;
            case R.id.email_register_button:
                Intent i = new Intent(this, Register.class);
                startActivity(i);
                break;

            default:
                break;
        }
    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

        }

    }

}