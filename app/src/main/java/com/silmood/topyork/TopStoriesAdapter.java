package com.silmood.topyork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.TopStoryHolder>{

    private List<TopStory> mTopStories;

    public TopStoriesAdapter(List<TopStory> topStories) {
        mTopStories = topStories;
    }

    @Override
    public TopStoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_story, parent, false);

        return new TopStoryHolder(root);
    }

    @Override
    public void onBindViewHolder(TopStoryHolder holder, int position) {
        TopStory topStory = mTopStories.get(position);
        holder.bindStory(topStory);
    }

    @Override
    public int getItemCount() {
        return mTopStories.size();
    }

    public void setItems(List<TopStory> items) {
        mTopStories = items;
        notifyDataSetChanged();
    }

    public class TopStoryHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.label_title)
        TextView mTitleLabel;

        TopStory mTopStory;

        public TopStoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindStory(TopStory topStory) {
            mTopStory = topStory;

            mTitleLabel.setText(topStory.getTitle());
        }
    }
}
