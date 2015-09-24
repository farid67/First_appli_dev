package com.example.farid.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by farid on 12/08/15.
 */
public class ScreenSlideFragment extends Fragment {

    private static final String IMAGE_DATA_EXTRA = "resId";
    private int mImageNum;
    private ImageView mImageView;

    static ScreenSlideFragment newInstance (int mImageNum)
    {
        final ScreenSlideFragment f = new ScreenSlideFragment();
        final Bundle args = new Bundle();
        args.putInt(IMAGE_DATA_EXTRA,mImageNum);
        f.setArguments(args);
        return f;
    }
    // an empty constructor is needed
    public ScreenSlideFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mImageNum = getArguments() != null ? getArguments().getInt(IMAGE_DATA_EXTRA) : -1 ;
    }



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // we need here to create the mImageView object
        final View v = inflater.inflate(R.layout.fragment_screen_slide_images,container,false);
        mImageView = (ImageView) v.findViewById(R.id.imageViewer);
        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        final int resId = ShowImagesActivity.choseImagesId[mImageNum];
        mImageView.setImageResource(resId); // load image into ImageView
    }
}
