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

import org.w3c.dom.Text;

/**
 * Created by Neas on 4/4/15.
 */
public class MyAccountFragment extends Fragment
{
    private String email;
    private TextView name;
    private TextView textEmail;
    private TextView bio;
    private TextView textKarma;

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

        textEmail = (TextView) rootview.findViewById(R.id.userEmail);
        textEmail.setText(email);

        bio = (TextView) rootview.findViewById(R.id.bio);
        bio.setText(getInfo()[1]);

        textKarma = (TextView) rootview.findViewById(R.id.karma);
        textKarma.setText(getKarma());


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
        s[1] = prefs.getString("bio", "no bio yet!");
        s[2] = prefs.getString("posted_jobs", "No jobs yet");

        return s;
    }

    private String getKarma() {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        int karmaFun = prefs.getInt("karma", 0);
        return String.valueOf(karmaFun);
    }
}
