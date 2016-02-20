package com.silmood.topyork;

import com.silmood.topyork.data.TopStoriesRepository;
import com.silmood.topyork.data.TopYorkRepositories;

/**
 * Created by Pedro Hernández on 02/2016.
 */
public class Injection {

    public static TopStoriesRepository provideTopStoriesRepository(){
        return TopYorkRepositories.getTopStoriesRepository(TopStoriesApiClient.getInstance(),
                TopStoriesLocalServiceImpl.getInstance());
    }
}
