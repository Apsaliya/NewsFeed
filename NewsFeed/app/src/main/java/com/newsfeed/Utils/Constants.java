package com.newsfeed.Utils;

/**
 * Created by ankit on 08/10/17.
 */

public class Constants {

    public static String BACKEND = NetworkConstants.PROD;

    public static class NetworkConstants {
        public static final String PROD = "prod";
        public static final String TEST = "test";
        public static final String BASE_URL_PROD = "http://hn.algolia.com/api/v1/";
        public static final String BASE_URL_TEST = "http://hn.algolia.com/api/v1/";
    }
}
