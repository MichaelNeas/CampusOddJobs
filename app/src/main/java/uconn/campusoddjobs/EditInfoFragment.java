package uconn.campusoddjobs;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neas on 5/1/15.
 */
public class EditInfoFragment extends Fragment implements View.OnClickListener {

    private Button updateButton;
    private Button refreshButton;
    private Button passwordButton;
    private EditText userBio;
    private EditText userEmail;
    private EditText userName;
    private String bioChange;
    private String currentEmail;
    private String userChange;
    private String emailChange;

    private static final String INFO_URL = "http://campusoddjobs.com/oddjobs/infochange.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONparser jsonParser = new JSONparser();
    JSONparser jparse = new JSONparser();


    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.edit_info_layout, container, false);

        currentEmail = getEmailFromMemory();

        userName = (EditText) rootview.findViewById(R.id.editText);
        userName.setText(getInfo()[0]);

        userEmail = (EditText) rootview.findViewById(R.id.editText3);
        userEmail.setText(currentEmail);

        userBio = (EditText) rootview.findViewById(R.id.editText2);
        userBio.setText(getInfo()[1]);

        userBio = (EditText)rootview.findViewById(R.id.editText2);
        userEmail = (EditText)rootview.findViewById(R.id.editText3);
        userName = (EditText)rootview.findViewById(R.id.editText);

        updateButton = (Button)rootview.findViewById(R.id.button);
        updateButton.setOnClickListener(this);

        refreshButton = (Button)rootview.findViewById(R.id.button5);
        refreshButton.setOnClickListener(this);

        passwordButton = (Button)rootview.findViewById(R.id.button4);
        passwordButton.setOnClickListener(this);

        return rootview;
    }

    @Override
    public void onClick(View v) {
        bioChange = userBio.getText().toString();
        userChange = userName.getText().toString();
        emailChange = userEmail.getText().toString();

        if(bioChange.length() < 1 || userChange.length() == 0)
            Toast.makeText(getActivity(), "You gotta do more than that",
                    Toast.LENGTH_SHORT).show();
        else if(!emailChange.contains("@uconn.edu")){
            Toast.makeText(getActivity(), "Email not supported",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            //Intent intent = new Intent(Intent.ACTION_SEND);
            switch (v.getId()) {

                case R.id.button5:
                    getActivity().recreate();
                    break;

                case R.id.button4:
                    Toast.makeText(getActivity(), "Change request Sent",
                            Toast.LENGTH_SHORT).show();
                    break;

                case R.id.button:
                    new UpdateInfo().execute();
                    Toast.makeText(getActivity(), "Changes Successful!",
                            Toast.LENGTH_SHORT).show();
                    getActivity().recreate();
                    break;

                default:
                    break;
            }



        }
    }

    class UpdateInfo extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            // Check for success tag
            int success;
            String changeBio = userBio.getText().toString();
            String changeName = userName.getText().toString();
            String personEmail = userEmail.getText().toString();
            String personID = String.valueOf(getUserID());

            try {
                Log.d("bio", changeBio);
                Log.d("name", changeName);
                Log.d("email", personEmail);
                Log.d("ID", personID);
                // Building Parameters
                List<NameValuePair> namVals = new ArrayList<NameValuePair>();
                namVals.add(new BasicNameValuePair("id", personID));
                namVals.add(new BasicNameValuePair("email", personEmail));
                namVals.add(new BasicNameValuePair("username", changeName));
                namVals.add(new BasicNameValuePair("bio", changeBio));

                Log.d("val pairs",namVals.toString());
                Log.d("request!", "starting");
                //Posting user data to script
                JSONObject jObjectParser = jparse.makeHttpRequest(
                        INFO_URL, "POST", namVals);

                // full json response
                Log.d("Updating JSON:", jObjectParser.toString());
                // json success element
                success = jObjectParser.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Changes Successful!", jObjectParser.toString());
                    return jObjectParser.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", jObjectParser.getString(TAG_MESSAGE));
                    return jObjectParser.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
    private String getEmailFromMemory() {          // pulls email from shared preferences
        SharedPreferences prefs = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        String extractedText = prefs.getString("email", "error: no email");
        return extractedText;
    }

    private String[] getInfo() {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("user_settings",Context.MODE_PRIVATE);
        String[] s = new String[5];
        s[0] = prefs.getString("username","error:no username");
        s[1] = prefs.getString("bio", "no bio yet!");
        s[2] = prefs.getString("posted_jobs", "No jobs yet");

        return s;
    }

    private int getUserID() {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        int daID = prefs.getInt("userID", 0);
        return daID;
    }
}
