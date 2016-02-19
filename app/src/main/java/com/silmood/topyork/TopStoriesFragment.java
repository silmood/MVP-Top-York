package com.silmood.topyork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * <p/>
 * Created by Pedro Hernández on 02/2016.
 */
public class TopStoriesFragment extends BaseFragment implements OnItemClickListener<TopStory> {

    @Bind(R.id.list_top_stories)
    RecyclerView mTopStoriesList;

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    @Bind(R.id.layout_error)
    LinearLayout mErrorLayout;

    @Bind(R.id.button_retry)
    ImageButton mRetryButton;

    TopStoriesAdapter mStoriesAdapter;

    public static TopStoriesFragment newInstance() {
        return new TopStoriesFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_top_stories;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mStoriesAdapter = createStoriesAdapter(this);
        initializeList(mTopStoriesList, mStoriesAdapter);
        initializeRetryButton(mRetryButton);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchStories();
    }

    @Override
    public void onItemClicked(int position, TopStory item) {
        Intent webIntent = createWebIntent(item.getUrl());
        startActivity(webIntent);
    }

    private void fetchStories() {
        mStoriesAdapter.clear();
        mProgressBar.setVisibility(View.VISIBLE);
        TopStoriesApiClient.getInstance()
                .fetchTopStories().enqueue(new Callback<TopStoriesResponse>() {
            @Override
            public void onResponse(Response<TopStoriesResponse> response, Retrofit retrofit) {
                mProgressBar.setVisibility(View.INVISIBLE);
                updateList(response.body().getStories());
            }

            @Override
            public void onFailure(Throwable t) {
                if (BuildConfig.DEBUG)
                    t.printStackTrace();

                mProgressBar.setVisibility(View.INVISIBLE);
                showRequestError();
            }
        });
    }

    private void showRequestError() {
        mErrorLayout.setVisibility(View.VISIBLE);
    }

    private void updateList(List<TopStory> stories) {
        mStoriesAdapter.setItems(stories);
    }

    private void initializeList(RecyclerView list, RecyclerView.Adapter adapter) {
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.addItemDecoration(new SimpleSpaceDecorator(getContext(), R.dimen.spacing_medium));
        list.setAdapter(adapter);
    }

    private void initializeRetryButton(ImageButton retryButton) {
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mErrorLayout.setVisibility(View.INVISIBLE);
                fetchStories();
            }
        });
    }

    private TopStoriesAdapter createStoriesAdapter(@Nullable OnItemClickListener<TopStory> listener) {
        TopStoriesAdapter adapter = new TopStoriesAdapter();

        if(listener != null)
            adapter.setItemClickListener(listener);

        return adapter;
    }

    private Intent createWebIntent(String url) {
        Intent webIntent = new Intent();
        webIntent.setAction(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(url));

        return webIntent;
    }
}
