package com.silmood.topyork.model;

import com.google.gson.annotations.SerializedName;
import com.silmood.topyork.Constants;

import java.util.List;

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
public class TopStoriesResponse {

    @SerializedName(Constants.STATUS)
    String mStatus;

    @SerializedName(Constants.RESULTS)
    List<TopStory> mStories;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public List<TopStory> getStories() {
        return mStories;
    }

    public void setStories(List<TopStory> stories) {
        mStories = stories;
    }
}
