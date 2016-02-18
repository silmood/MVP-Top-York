package com.silmood.topyork;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
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
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Created by Pedro Hernández on 02/2016.
 */
public class TopStoriesFragment extends BaseFragment{

    @Bind(R.id.list_top_stories)
    RecyclerView mTopStoriesList;

    TopStoriesAdapter mStoriesAdapter;

    public static TopStoriesFragment newInstance(){
        return new TopStoriesFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_top_stories;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        initializeList(mTopStoriesList);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TopStoriesApiClient.getInstance()
                .fetchTopStories().enqueue(new Callback<TopStoriesResponse>() {
            @Override
            public void onResponse(Response<TopStoriesResponse> response, Retrofit retrofit) {
                updateList(response.body().getStories());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void updateList(List<TopStory> stories) {
        if (mStoriesAdapter == null)
            mStoriesAdapter = new TopStoriesAdapter(stories);
        else
            mStoriesAdapter.setItems(stories);

        mTopStoriesList.setAdapter(mStoriesAdapter);
    }

    private void initializeList(RecyclerView list) {
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.addItemDecoration(new SimpleSpaceDecorator(getContext(), R.dimen.list_margin));
    }
}
