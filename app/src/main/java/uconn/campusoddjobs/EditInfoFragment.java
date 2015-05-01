package uconn.campusoddjobs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private EditText userBio;
    private EditText userEmail;
    private EditText userName;
    private String bioChange;
    private String changeEmail;
    private String userChange;

    private static final String CONTACT_URL = "http://campusoddjobs.com/oddjobs/infochange.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONparser jsonParser = new JSONparser();


    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.edit_info_layout, container, false);

        changeEmail = getEmailFromMemory();

        userName = (EditText) rootview.findViewById(R.id.editText);
        userName.setText(getInfo()[0]);

        userEmail = (EditText) rootview.findViewById(R.id.editText3);
        userEmail.setText(changeEmail);

        userBio = (EditText) rootview.findViewById(R.id.editText2);
        userBio.setText(getInfo()[1]);

        updateButton = (Button)rootview.findViewById(R.id.button);
        updateButton.setOnClickListener(this);

        userBio = (EditText)rootview.findViewById(R.id.editText2);
        userEmail = (EditText)rootview.findViewById(R.id.editText3);
        userName = (EditText)rootview.findViewById(R.id.editText);
        return rootview;
    }

    @Override
    public void onClick(View v) {
        bioChange = userBio.getText().toString();
        userChange = userName.getText().toString();

        if(bioChange.length() < 1)
            Toast.makeText(getActivity(), "You know more than that!",
                    Toast.LENGTH_SHORT).show();
        else {
            //Intent intent = new Intent(Intent.ACTION_SEND);
            new UpdateInfo().execute();
            Toast.makeText(getActivity(), "Thanks!",
                    Toast.LENGTH_SHORT).show();
            getActivity().recreate();

        }
    }

    class UpdateInfo extends AsyncTask<String, String, String> {

        boolean failure = false;

        @Override
        protected String doInBackground(String... args) {
            // Check for success tag
            int success;
            String newBio = userBio.getText().toString();
            String newName = userName.getText().toString();
            String newEmail = userEmail.getText().toString();

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("message", newBio));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        CONTACT_URL, "POST", params);

                // full json response
                Log.d("Storing Attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Thanks!", json.toString());
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

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
}
