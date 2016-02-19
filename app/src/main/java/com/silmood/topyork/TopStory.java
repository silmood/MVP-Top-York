package com.silmood.topyork;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class TopStory {
    @SerializedName(Constants.TITLE)
    String mTitle;

    @SerializedName(Constants.ABSTRACT)
    String mAbstract;

    @SerializedName(Constants.URL)
    String mUrl;

    @SerializedName(Constants.SECTION)
    String mSection;

    @SerializedName(Constants.SUBSECTION)
    String mSubSection;

    @SerializedName(Constants.BY)
    String mBy;

    @Expose
    List<Multimedia> mMultimedias;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAbstract() {
        return mAbstract;
    }

    public void setAbstract(String anAbstract) {
        mAbstract = anAbstract;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
    }

    public String getSubSection() {
        return mSubSection;
    }

    public void setSubSection(String subSection) {
        mSubSection = subSection;
    }

    public String getBy() {
        return mBy;
    }

    public void setBy(String by) {
        mBy = by;
    }

    public List<Multimedia> getMultimedias() {
        return mMultimedias;
    }

    public void setMultimedias(List<Multimedia> multimedias) {
        mMultimedias = multimedias;
    }

    @Nullable
    public Multimedia getBestImage(){
        if (mMultimedias != null) {
            Collections.sort(mMultimedias);
            return mMultimedias.get(0);
        }

        return null;
    }

    public class Multimedia implements Comparable<Multimedia>{

        @SerializedName(Constants.URL)
        String mUrl;

        @SerializedName(Constants.FORMAT)
        String mFormat;

        @SerializedName(Constants.HEIGHT)
        int mHeight;

        @SerializedName(Constants.WIDTH)
        int mWidth;

        public String getUrl() {
            return mUrl;
        }

        public void setUrl(String url) {
            mUrl = url;
        }

        public int getHeight() {
            return mHeight;
        }

        public void setHeight(int height) {
            mHeight = height;
        }

        public String getFormat() {
            return mFormat;
        }

        public void setFormat(String format) {
            mFormat = format;
        }

        public int getWidth() {
            return mWidth;
        }

        public void setWidth(int width) {
            mWidth = width;
        }

        @Override
        public int compareTo(@NonNull Multimedia multimedia) {
            int compareWidth = multimedia.getWidth();
            return compareWidth - getWidth();
        }
    }
}
