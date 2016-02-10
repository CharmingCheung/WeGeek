package com.wegeekapps.android.wegeek.factroy;

import retrofit.GsonConverterFactory;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.Retrofit;

/**
 * Created by Charming on 16/2/9.
 */
public class ServiceFactory {
    public static <T> T createRetrofitService(final Class<T> clazz, final String baseUrl) {
        final Retrofit builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T service = builder.create(clazz);

        return service;
    }
}
