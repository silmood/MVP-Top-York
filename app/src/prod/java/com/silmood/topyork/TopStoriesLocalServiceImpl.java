package com.silmood.topyork;

import com.silmood.topyork.data.TopStoriesLocalService;
import com.silmood.topyork.model.TopStory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Pedro Hernández on 02/2016.
 */
public class TopStoriesLocalServiceImpl implements TopStoriesLocalService{
    static TopStoriesLocalServiceImpl mInstance;

    public static TopStoriesLocalServiceImpl getInstance(){
        if (mInstance== null)
            mInstance = new TopStoriesLocalServiceImpl();

        return mInstance;
    }

    @Override
    public void getLastTopStoriesSaved(TopStoriesLocalCallback<List<TopStory>> callback) {
        //TODO: Query to DB
        callback.onError(new IOException());
    }
}
