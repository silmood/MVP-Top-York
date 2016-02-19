package com.silmood.topyork;

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
public class Constants {
    public static final String TITLE = "title";
    public static final String ABSTRACT = "abstract";
    public static final String URL = "url";
    public static final String MULTIMEDIA = "multimedia";
    public static final String FORMAT = "format";
    public static final String HEIGHT = "height";
    public static final String WIDTH = "width";
    public static final String STATUS = "status";
    public static final String RESULTS = "results";
    public static final String SECTION = "section";
    public static final String SUBSECTION = "subsection";
    public static final String BY = "byline";

    public class Api {
        public static final String BASE_URL = "http://api.nytimes.com/svc/";
        public static final String VERSION = "/v1";
        public static final String FORMAT = "json";
        public static final String SECTION = "home";
        public static final String FETCH_TOP_STORIES = "topstories" +
                VERSION + "/" + SECTION + "." + FORMAT;
    }
}
