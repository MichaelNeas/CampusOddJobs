package uconn.campusoddjobs;

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
    private TextView name;
    private static final String PROFILE_URL = "http://campusoddjobs.com/oddjobs/buildprofile.php";

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.my_account_layout, container, false);
        Profile profile = new Profile();
        // test if info carried over \/\/
        name = (TextView) rootview.findViewById(R.id.nameview);
        name.setText(profile.username());
        return rootview;
    }

}
