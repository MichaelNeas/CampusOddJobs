package uconn.campusoddjobs;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new buildProfile().execute();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }


    }

    /**
     * User selection in the navigation bar
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objectFragment = null;
        Bundle bundle = new Bundle();

        switch (position)
        {
            case 0:
                objectFragment = new PostingBoardFragment();
                mTitle = getString(R.string.title_section1);
                break;
            case 1:
                objectFragment = new MessagesFragment();
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                objectFragment = new MyAccountFragment();
                mTitle = getString(R.string.title_section3);
                break;
            case 3:
                objectFragment = new EditInfoFragment();
                mTitle = getString(R.string.title_section5);
                break;
            case 4:
                objectFragment = new ContactUsFragment();
                mTitle = getString(R.string.title_section4);
                break;
        }

        objectFragment.setArguments(bundle);        // set email as argument for next fragment
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objectFragment)
                .commit();
    }

    /**
     * Assign String titles to the menu
     *
     */
    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section1);
                break;
            case 1:
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                mTitle = getString(R.string.title_section3);
                break;
            case 3:
                mTitle = getString(R.string.title_section5);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == R.id.action_example){
            onNavigationDrawerItemSelected(3);
                    }

        if(id == R.id.happy_button){
            recreate();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
    class buildProfile extends AsyncTask<String, String, String> {

        JSONparser jparser = new JSONparser();
        private static final String PROFILE_URL = "http://campusoddjobs.com/oddjobs/buildprofile.php";
        private String email = getEmailFromMemory();

        @Override
        protected String doInBackground(String... args) {
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", email));

                JSONObject json = jparser.makeHttpRequest(PROFILE_URL, "GET", params);

                storeInfo(json.getString("username"),
                        json.getString("bio"),
                        json.getString("posted_jobs"),
                        json.getString("accepted_jobs"),
                        json.getInt("karma"),
                        json.getInt("id"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private String getEmailFromMemory() {          // pulls email from shared preferences
        SharedPreferences prefs = getSharedPreferences("user_settings", MODE_PRIVATE);
        String extractedText = prefs.getString("email", "error: no email");
        return extractedText;
    }

    private void storeInfo(String un,String b,String pj,String aj,int k, int id){       // store email in shared preferences
        SharedPreferences prefs = getSharedPreferences("user_settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username",un);
        editor.putString("bio",b);
        editor.putString("posted_jobs",pj);
        editor.putString("accepted_jobs",aj);
        editor.putInt("karma",k);
        editor.putInt("userID", id);
        editor.commit();
    }


}
