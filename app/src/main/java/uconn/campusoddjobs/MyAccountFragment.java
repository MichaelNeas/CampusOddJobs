package uconn.campusoddjobs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Neas on 4/4/15.
 */
public class MyAccountFragment extends Fragment
{
    private static final String PROFILE_URL = "http://campusoddjobs.com/oddjobs/buildprofile.php";

    private String email;
    private int id;
    private String username;
    private String bio;
    private String posted_jobs;
    private String accepted_jobs;
    private int karma;

    JSONparser jparser = new JSONparser();

    private TextView name;

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.my_account_layout, container, false);

        // Profile profile = new Profile();
        email = getEmailFromMemory();

        name = (TextView) rootview.findViewById(R.id.nameview);
        name.setText(getInfo()[0]);

        return rootview;
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

        return s;
    }

}
