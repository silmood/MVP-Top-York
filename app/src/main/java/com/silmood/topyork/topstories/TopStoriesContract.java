package com.silmood.topyork.topstories;

import android.content.Intent;

import com.silmood.topyork.model.TopStory;

import java.util.List;

/**
 * Created by Pedro Hernández on 02/2016.
 */
public class TopStoriesContract {

    public interface View{
        void setProgressVisible(boolean visible);

        void launchActivity(Intent intent);

        void showStories(List<TopStory> stories);

        void showRequestError();
    }

    public interface UserActionListener{
        void requestTopStories();

        void storyDetail(TopStory item);
    }
}
