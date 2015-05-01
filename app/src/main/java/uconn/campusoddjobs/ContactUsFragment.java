package uconn.campusoddjobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
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
 * Created by Neas on 4/4/15.
 */
public class ContactUsFragment extends Fragment implements View.OnClickListener
{
    private Button sendButton;
    private EditText userComments;
    private String comments;
    private static final String CONTACT_URL = "http://campusoddjobs.com/oddjobs/contact.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONparser jsonParser = new JSONparser();

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.contact_us_layout, container, false);
        sendButton = (Button)rootview.findViewById(R.id.button);
        sendButton.setOnClickListener(this);
        userComments = (EditText)rootview.findViewById(R.id.editText2);
        return rootview;
    }

    @Override
    public void onClick(View v) {
        comments = userComments.getText().toString();

        if(comments.length() < 1)
            Toast.makeText(getActivity(), "More Please!",
                    Toast.LENGTH_SHORT).show();
        else {
            //Intent intent = new Intent(Intent.ACTION_SEND);
            new StoreMessage().execute();
            Toast.makeText(getActivity(), "Thanks!",
                    Toast.LENGTH_SHORT).show();

            getActivity().recreate();

        }
    }

//    public void storeMessage(String message)
//    {
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("message", message));
//
//        //Posting user data to script
//        JSONObject json = jsonParser.makeHttpRequest(
//                CONTACT_URL, "POST", params);
//
//        // full json response
//        Log.d("Post attempt", json.toString());
//        }
//



    class StoreMessage extends AsyncTask<String, String, String> {

        boolean failure = false;

        @Override
        protected String doInBackground(String... args) {
            // Check for success tag
            int success;
            String contactMessage = userComments.getText().toString();

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("message", contactMessage));
                Log.d("val pairs",params.toString());
                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        CONTACT_URL, "POST", params);

                // full json response
                Log.d("Login attempt", json.toString());

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

    }

