package uconn.campusoddjobs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey on 4/23/15.
 */
public class Profile extends Activity{

    private int id;
    private String email;
    private String username;
    private String bio;
    private String posted_jobs;
    private String accepted_jobs;
    private int karma;

    public String getTest;

    private static final String PROFILE_URL = "http://campusoddjobs.com/oddjobs/buildprofile.php";
    JSONparser jparser = new JSONparser();

    public Profile(){

        email = getEmailFromMemory();
        new buildProfile().execute();

       // try {

            //JSONObject c = new JSONObject(def);
            //username = c.getString("username");

            // TODO create json object and properly communicate with buildprofile.php
            // email is stored already and that works fine... need to run against DB
            // username = c.getString("username");
            // bio = jobj.getString("bio");
            // karma = jobj.getInt("karma");
            // posted_jobs = jobj.getString("posted_jobs");
            // accepted_jobs = jobj.getString("accepted_jobs");

         // } catch (JSONException e) {
          //   e.printStackTrace();
          //}

     }

    // ---------- Getters ----------
    public String email(){
        return email;
    }
    public String username(){return username;}
    public String bio(){
        return bio;
    }
    public String posted_jobs(){
        return posted_jobs;
    }
    public String accepted_jobs(){
        return accepted_jobs;
    }
    public int karma(){
        return karma;
    }
    // -----------------------------

    private String getEmailFromMemory() {          // pulls email from shared preferences
        Context context = MainActivity.getAppContext();
        SharedPreferences prefs = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        String extractedText = prefs.getString("email", "error: no email");
        return extractedText;
    }

    class buildProfile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", email));

                JSONObject json = jparser.makeHttpRequest(PROFILE_URL, "GET", params);

                username = json.getString("username");
                Log.d("Penis", username());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

    }
}

