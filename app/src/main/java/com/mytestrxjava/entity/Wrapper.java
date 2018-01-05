package com.mytestrxjava.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mytestrxjava.utils.LogUtils;
import com.mytestrxjava.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by Yomoo on 2017/9/27.
 */

public class Wrapper {
    public int resultcode ;
    public String resultmsg ;
    public Object result ;
    public static class JsonAdapter implements JsonDeserializer<String> {
        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            LogUtils.dLog("Json请求");
            String jsonRoot = json.getAsJsonObject().toString() ;
            ToastUtil.showToastShort(jsonRoot);

//                Wrapper wrapper = new Wrapper() ;
//                JSONObject jsobRespData = new JSONObject(jsonRoot) ;
//                wrapper.resultcode = jsobRespData.getInt("resultcode") ;
//                wrapper.resultmsg = jsobRespData.getString("resultmsg") ;
//                wrapper.result = jsobRespData.get("result") ;
            return jsonRoot;

        }
    }
}
