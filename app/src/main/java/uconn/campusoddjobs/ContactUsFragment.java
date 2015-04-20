package uconn.campusoddjobs;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Neas on 4/4/15.
 */
public class ContactUsFragment extends Fragment implements View.OnClickListener
{
    private Button sendButton;
    private EditText userComments;
    private String comments;

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

        if(comments.length() < 2)
            Toast.makeText(getActivity(), "More Please!",
                    Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            //send to database =)
            Toast.makeText(getActivity(), "Thanks for your contact! =)",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
