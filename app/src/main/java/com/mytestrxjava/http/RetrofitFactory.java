package com.mytestrxjava.http;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mytestrxjava.entity.Wrapper;
import com.mytestrxjava.utils.LogUtils;
import com.mytestrxjava.utils.ToastUtil;

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
 * Created by Yomoo on 2017/9/25.
 */

public class RetrofitFactory {
    private static final String BASE_URL = "http://gc.ditu.aliyun.com/";
    private static final long TIMEOUT = 30;
    //retrofit是给予OKhttpCliet的，这里创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            //添加通用的Header
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
//                    builder.addHeader("token", "123");
                    return chain.proceed(builder.build());
                }
            })
            //添加一个log打印
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

                @Override
                public void log(String message) {
                    ToastUtil.showToastShort(message);
                    LogUtils.dLog("Log请求"+message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();
    private static RetrofitService retrofitService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            //添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(RetrofitService.class);

    public static RetrofitService getInstance(){
        return retrofitService;
    }
    private static Gson buildGson(){
        LogUtils.dLog("Gson请求");
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                //此处可以添加Gson 自定义TypeAdapter
                .registerTypeAdapter(String.class,new Wrapper.JsonAdapter())
                .create();
    }

}