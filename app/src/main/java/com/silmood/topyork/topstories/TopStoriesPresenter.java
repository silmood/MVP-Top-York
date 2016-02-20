package com.silmood.topyork.topstories;

import android.content.Intent;
import android.net.Uri;

import com.silmood.topyork.BuildConfig;
import com.silmood.topyork.data.TopStoriesRepository;
import com.silmood.topyork.model.TopStory;

import java.util.List;

/**
 * Created by Pedro Hernández on 02/2016.
 */
public class TopStoriesPresenter implements TopStoriesContract.UserActionListener{

    private TopStoriesContract.View mView;
    private TopStoriesRepository mStoriesRepository;

    public TopStoriesPresenter(TopStoriesContract.View view, TopStoriesRepository storiesRepository) {
        mView = view;
        mStoriesRepository = storiesRepository;
    }

    @Override
    public void requestTopStories() {
        mView.setProgressVisible(true);
        mStoriesRepository.fetchTopStories(new TopStoriesRepository.FetchStoriesCallback() {
            @Override
            public void onSuccess(List<TopStory> stories) {
                mView.setProgressVisible(false);
                mView.showStories(stories);
            }

            @Override
            public void onError(Throwable t) {
                if (BuildConfig.DEBUG)
                    t.printStackTrace();

                mView.setProgressVisible(false);
                mView.showRequestError();
            }
        });
    }

    @Override
    public void storyDetail(TopStory item) {
        Intent webIntent = createWebIntent(item.getUrl());
        mView.launchActivity(webIntent);
    }

    private Intent createWebIntent(String url) {
        Intent webIntent = new Intent();
        webIntent.setAction(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(url));

        return webIntent;
    }
}
