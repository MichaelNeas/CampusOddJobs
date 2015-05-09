package uconn.campusoddjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Neas on 4/4/15.
 */


public class PostingBoardFragment extends Fragment implements View.OnClickListener
{
    View rootview;

    private Button newpost,read;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.posting_board_layout, container, false);

        newpost = (Button)rootview.findViewById(R.id.newpost);
        newpost.setOnClickListener(this);
        read = (Button)rootview.findViewById(R.id.toboard);
        read.setOnClickListener(this);


        return rootview;

        // button to board activity
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newpost:
                Intent i = new Intent(getActivity(), AddComment.class);
                startActivity(i);
                break;
            case R.id.toboard:
                Intent j = new Intent(getActivity(), ReadComments.class);
                startActivity(j);
                break;
            default:
                break;
        }
    }


}
