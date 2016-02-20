package com.silmood.topyork.topstories;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.silmood.topyork.FragmentContainerActivity;
import com.silmood.topyork.topstories.TopStoriesFragment;

public class TopStoriesActivity extends FragmentContainerActivity {

    @Override
    protected Fragment createFragment() {
        return TopStoriesFragment.newInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setTitle("");
    }
}
