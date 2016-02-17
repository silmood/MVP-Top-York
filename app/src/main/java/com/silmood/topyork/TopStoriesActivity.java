package com.silmood.topyork;

import android.support.v4.app.Fragment;

public class TopStoriesActivity extends FragmentContainerActivity {

    @Override
    protected Fragment createFragment() {
        return TopStoriesFragment.newInstance();
    }
}
