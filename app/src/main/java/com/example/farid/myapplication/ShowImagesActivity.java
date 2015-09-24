package com.example.farid.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;


public class ShowImagesActivity extends FragmentActivity {



    public Integer[] imageResIds = new Integer[]{R.drawable.rabbit,R.drawable.teddybear};


    // this variable is probably here to stay the state of the array containing the images
    public static final String EXTRA_IMAGE="extra_image";

    public ImagePagerAdapter mAdapter;
    public ViewPager mPager;

    public static Integer[] choseImagesId;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList chosenImages = intent.getIntegerArrayListExtra(MainActivity.EXTRA_ARRAY);
        choseImagesId = new Integer[chosenImages.size()];

        int cmpt=0;
        for (cmpt=0; cmpt < chosenImages.size(); cmpt ++)
        {
            choseImagesId[cmpt] =(Integer) imageResIds[(Integer)chosenImages.get(cmpt)];
        }



        setContentView(R.layout.activity_show_images);

        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(),chosenImages.size());
        mPager=(ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

    }


    public static class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final int mSize;

        public ImagePagerAdapter(FragmentManager fm, int size) {
            super(fm);
            mSize = size;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return ScreenSlideFragment.newInstance(position);
        }
    }


}
