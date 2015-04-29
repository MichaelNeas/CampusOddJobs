package uconn.campusoddjobs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Neas on 4/4/15.
 */
public class MyAccountFragment extends Fragment
{
    private static final String PROFILE_URL = "http://campusoddjobs.com/oddjobs/buildprofile.php";

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Profile profile = new Profile();
        // test if info carried over \/\/
        // Toast.makeText(MainActivity.getAppContext(), profile.username(), Toast.LENGTH_SHORT).show();
        rootview = inflater.inflate(R.layout.my_account_layout, container, false);
        return rootview;
    }


}
