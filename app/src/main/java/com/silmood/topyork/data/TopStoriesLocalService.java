package com.silmood.topyork.data;

import com.silmood.topyork.model.TopStory;

import java.util.List;

/**
 * Created by Pedro Hernández on 02/2016.
 */
public interface TopStoriesLocalService {

    interface TopStoriesLocalCallback<T>{
        void onLoaded(T stories);

        void onError(Throwable t);
    }

    void getLastTopStoriesSaved(TopStoriesLocalCallback<List<TopStory>> callback);
}
