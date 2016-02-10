package com.wegeekapps.android.wegeek.service_model;

import com.wegeekapps.android.wegeek.model.V2exhot;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Charming on 16/2/9.
 */
public interface V2exHotService {
    String SERVICE_ENDPOINT = "http://www.v2ex.com";

    @GET("/api/topics/hot.json")
    Observable<List<V2exhot>> getHot();
}
