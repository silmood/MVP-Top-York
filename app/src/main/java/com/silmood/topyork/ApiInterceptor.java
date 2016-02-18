package com.silmood.topyork;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
public class ApiInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        String API_VALUE_TOKEN = "9a0a5f5c65703adf67e0537a494d2b14:15:74435779";
        String API_KEY = "api-key";

        Request original = chain.request();
        HttpUrl newUrl = original.httpUrl().newBuilder()
                .addQueryParameter(API_KEY, API_VALUE_TOKEN).build();

        Request request = original.newBuilder()
                .method(original.method(), original.body())
                .url(newUrl)
                .build();

        return chain.proceed(request);
    }
}
