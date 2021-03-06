package uconn.campusoddjobs;

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
public class Profile{

    private String email;
    private int id;
    private String username;
    private String bio;
    private String posted_jobs;
    private String accepted_jobs;
    private int karma;

    private static final String PROFILE_URL = "http://campusoddjobs.com/oddjobs/buildprofile.php";
    JSONparser jparser = new JSONparser();

    public Profile(){

        //email = getEmailFromMemory();
        new buildProfile().execute();

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
    // ---------- Setters ----------
    public void setEmail(String e){email = e;}
    public void setUsername(String u){username = u;}
    public void setBio(String b){bio = b;}
    public void setPosted_jobs(String pj){posted_jobs = pj;}
    public void setAccepted_jobs(String aj){accepted_jobs = aj;}
    public void setKarma(int k){karma = k;}
    // -----------------------------

    //private String getEmailFromMemory() {          // pulls email from shared preferences
        //Context context = MainActivity.getAppContext();
        //SharedPreferences prefs = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        //String extractedText = prefs.getString("email", "error: no email");
        //return extractedText;
    //}

    class buildProfile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", email));

                JSONObject json = jparser.makeHttpRequest(PROFILE_URL, "GET", params);

                setUsername(json.getString("username"));
                Log.d("Check", username);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

    }


}

