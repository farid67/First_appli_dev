package com.example.farid.myapplication;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by farid on 07/08/15.
 * Class used to display the content of the message typed by the user in a fragment
 */
public class Frag_disp_message extends Fragment {


    // this variable is maybe not necessary, this was added to try to change the content of the TextView from the Activity
    public TextView disp_message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        disp_message = new TextView(getActivity());
        disp_message.setText("Hello World");
//        disp_message.setTag("@+id/disp_message");

        return (View) disp_message;
    }





}
