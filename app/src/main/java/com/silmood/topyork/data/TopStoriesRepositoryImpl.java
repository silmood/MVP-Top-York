package com.silmood.topyork.data;


import com.silmood.topyork.model.TopStory;

import java.util.List;

/**
 * Created by Pedro Hernández on 02/2016.
 */
public class TopStoriesRepositoryImpl implements TopStoriesRepository{

    TopStoriesApiService mApiService;
    TopStoriesLocalService mLocalService;

    public TopStoriesRepositoryImpl(TopStoriesApiService apiService, TopStoriesLocalService localService) {
        mApiService = apiService;
        mLocalService = localService;
    }

    @Override
    public void fetchTopStories(final FetchStoriesCallback callback) {
        mApiService.fetchTopStories(new TopStoriesApiService.TopStoriesApiCallback<List<TopStory>>() {
            @Override
            public void onLoaded(List<TopStory> stories) {
                callback.onSuccess(stories);
            }

            @Override
            public void onError(Throwable t) {
                mLocalService.getLastTopStoriesSaved(new TopStoriesLocalService.TopStoriesLocalCallback<List<TopStory>>() {
                    @Override
                    public void onLoaded(List<TopStory> stories) {
                        callback.onSuccess(stories);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t);
                    }
                });
            }
        });
    }

}
