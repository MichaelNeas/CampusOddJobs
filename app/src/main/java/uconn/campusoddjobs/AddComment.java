package uconn.campusoddjobs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class AddComment extends Activity implements OnClickListener{
	
	private EditText title, message;
	private Button  mSubmit;

    private ProgressDialog pDialog;
    JSONparser jsonParser = new JSONparser();

    private static final String POST_COMMENT_URL = "http://campusoddjobs.com/oddjobs/addcomment.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_comment);
		
		title = (EditText)findViewById(R.id.title);
		message = (EditText)findViewById(R.id.message);
		
		mSubmit = (Button)findViewById(R.id.submit);
		mSubmit.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
				new PostComment().execute();
	}
	
	
	class PostComment extends AsyncTask<String, String, String> {
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddComment.this);
            pDialog.setMessage("Posting Comment...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String post_title = title.getText().toString();
            String post_message = message.getText().toString();
            String post_username = getUsernameFromMemory();
            
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", post_username));
                params.add(new BasicNameValuePair("title", post_title));
                params.add(new BasicNameValuePair("message", post_message));
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(
                		POST_COMMENT_URL, "POST", params);
 
                // full json response
                Log.d("Post Comment attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("Comment Added!", json.toString());    
                	finish();
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Comment Failure!", json.getString(TAG_MESSAGE));
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
            if (file_url != null){
            	Toast.makeText(AddComment.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}

    private String getUsernameFromMemory() {          // pulls username from shared preferences
        SharedPreferences prefs = this.getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        String extractedText = prefs.getString("username", "error: no username");
        return extractedText;
    }


}
