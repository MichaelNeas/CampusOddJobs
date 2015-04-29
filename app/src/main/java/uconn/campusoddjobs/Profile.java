package uconn.campusoddjobs;

import android.content.Context;
import android.content.SharedPreferences;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey on 4/23/15.
 */
public class Profile{

    private int id;
    private String email;
    private String username;
    private String bio;
    private String posted_jobs;
    private String accepted_jobs;
    private int karma;

    private static final String PROFILE_URL = "http://campusoddjobs.com/oddjobs/buildprofile.php";
    private JSONparser json = new JSONparser();

    public Profile() {

        email = getEmailFromMemory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", email));

        // try {

            // TODO create json object and properly communicate with buildprofile.php
            // email is stored already and that works fine... need to run against DB
            // JSONObject jobj = json.makeHttpRequest(PROFILE_URL,"GET", params);

            // username = jobj.getString("username");
            // bio = jobj.getString("bio");
            // karma = jobj.getInt("karma");
            // posted_jobs = jobj.getString("posted_jobs");
            // accepted_jobs = jobj.getString("accepted_jobs");

        // } catch (JSONException e) {
            // e.printStackTrace();
        // }

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
}

