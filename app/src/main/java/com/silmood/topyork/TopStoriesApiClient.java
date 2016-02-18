package com.silmood.topyork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
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
public class TopStoriesApiClient {

    static TopStoriesApiService mApiService;

    public static TopStoriesApiService getInstance(){
        if (mApiService == null) {
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


            mApiService = retrofit.create(TopStoriesApiService.class);
        }

        return mApiService;
    }

    private static void addLoggingInterceptor(OkHttpClient client) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.interceptors().add(loggingInterceptor);
    }
}
