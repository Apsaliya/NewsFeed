package com.newsfeed.Networking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;

import com.newsfeed.Utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ankit on 08/10/17.
 */

public class ApiAdapter {

    private static ApiClient sApiClinet;

    public static ApiClient getApiClient () {
        if (sApiClinet == null) {
            initApiClient();
        }
        return sApiClinet;
    }

    private static void initApiClient() {
        final String serverUrl = Constants.NetworkConstants.BASE_URL;

        // for loggin we use our own interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // read-connect-write timeouts, setting interceptor we created above
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .build();

                return chain.proceed(request);
            }
        });
        okHttpBuilder.interceptors().add(loggingInterceptor);
        OkHttpClient okHttpClient = okHttpBuilder.build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(serverUrl);
        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        sApiClinet = retrofitBuilder.build().create(ApiClient.class);
    }
}
