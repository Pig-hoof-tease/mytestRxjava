package com.mytestrxjava.http;

import com.mytestrxjava.entity.AliAddrsBean;
import com.mytestrxjava.entity.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Yomoo on 2017/9/25.
 * 定义接口后需要将返回的json与Bean完全相同才可以解析成功，原因是OKhttp请求的时候带的Bean用来解析
 * 如果需要拿到返回的自付出自己进行解析，那么就要在请求的时候new Callback，然后在callbak里自己解析Json
 */

public interface RetrofitService {
    @FormUrlEncoded
    @POST("account/login")
    Observable<BaseEntity<String>> login(@Field("userId") String userId,@Field("password")String password);

    @GET("video/getUrl")
    Observable<BaseEntity<String>> getVideoUrl(@Query("id") long id);

    @FormUrlEncoded
    @POST("user/addVideo")
    Observable<BaseEntity<Boolean>> addVideo(@FieldMap Map<String,Object> map);

//    @POST("geocoding?a=上海市&aa=松江区&aaa=车墩镇")
    @POST("geocoding?a=1")
    Observable<AliAddrsBean> getIndexContentOne();

}
