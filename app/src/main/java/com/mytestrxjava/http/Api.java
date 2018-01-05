package com.mytestrxjava.http;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by Yomoo on 2017/9/25.
 */

public interface Api {
    @GET
    Observable<String> login(@Body String request);

    @GET
    Observable<String> request(@Body String request);
}
