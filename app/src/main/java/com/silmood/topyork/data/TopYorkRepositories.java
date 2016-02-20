package com.silmood.topyork.data;

import android.support.annotation.NonNull;


/**
 * Created by Pedro Hernández on 02/2016.
 */
public class TopYorkRepositories {

    public static TopStoriesRepository sTopStoriesRepository = null;

    public static TopStoriesRepository getTopStoriesRepository(@NonNull TopStoriesApiService apiService, TopStoriesLocalService localService){
        if (sTopStoriesRepository == null)
            sTopStoriesRepository = new TopStoriesRepositoryImpl(apiService, localService);

        return sTopStoriesRepository;
    }
}
