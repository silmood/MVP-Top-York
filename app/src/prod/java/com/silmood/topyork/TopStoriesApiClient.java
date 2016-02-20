package com.silmood.topyork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silmood.topyork.data.TopStoriesApiService;
import com.silmood.topyork.model.TopStoriesResponse;
import com.silmood.topyork.model.TopStory;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * <p>
 * Created by Pedro Hernández on 02/2016.
 */
public class TopStoriesApiClient implements TopStoriesApiService{

    static TopStoriesApiClient mApiClient;
    TopStoriesRetrofitApiService mRetrofitApiService;

    public static TopStoriesApiClient getInstance(){
        if (mApiClient == null) {
            mApiClient = new TopStoriesApiClient();
        }

        return mApiClient;
    }

    private TopStoriesApiClient (){
        OkHttpClient client = new OkHttpClient();
        ApiInterceptor apiInterceptor = new ApiInterceptor();
        client.interceptors().add(apiInterceptor);

        if (BuildConfig.DEBUG){
            addLoggingInterceptor(client);
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TopStory.class, new TopStoryDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        mRetrofitApiService = retrofit.create(TopStoriesRetrofitApiService.class);
    }

    private static void addLoggingInterceptor(OkHttpClient client) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.interceptors().add(loggingInterceptor);
    }

    @Override
    public void fetchTopStories(final TopStoriesApiCallback<List<TopStory>> callback) {
        mRetrofitApiService.fetchTopStories().enqueue(new Callback<TopStoriesResponse>() {
            @Override
            public void onResponse(Response<TopStoriesResponse> response, Retrofit retrofit) {
                callback.onLoaded(response.body().getStories());
            }

            @Override
            public void onFailure(Throwable t) {
                if (BuildConfig.DEBUG)
                    t.printStackTrace();

                callback.onError(t);
            }
        });
    }
}
