/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.mindorks.framework.jetpack.data.remote;


import com.mindorks.framework.jetpack.BuildConfig;

/**
 * Created by amitshekhar on 07/07/17.
 */

public final class ApiEndPoint {

    public static final String ENDPOINT_BLOG = BuildConfig.BASE_URL + "/5926ce9d11000096006ccb30";

    public static final String ENDPOINT_FACEBOOK_LOGIN = BuildConfig.BASE_URL + "/588d15d3100000ae072d2944";

    public static final String ENDPOINT_GOOGLE_LOGIN = BuildConfig.BASE_URL + "/588d14f4100000a9072d2943";

    public static final String ENDPOINT_LOGOUT = BuildConfig.BASE_URL + "/588d161c100000a9072d2946";

    public static final String ENDPOINT_OPEN_SOURCE_PAGE_ONE = BuildConfig.BASE_URL + "/5926c34212000035026871cd";

    public static final String ENDPOINT_OPEN_SOURCE_PAGE_TWO = BuildConfig.BASE_URL + "/5c80e2063100002613771ba8";

    public static final String ENDPOINT_OPEN_SOURCE_PAGE_THREE = BuildConfig.BASE_URL + "/5c822f0831000070201d1c26";

    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL + "/588d15f5100000a8072d2945";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
