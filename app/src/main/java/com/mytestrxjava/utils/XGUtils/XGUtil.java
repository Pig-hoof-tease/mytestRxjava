package com.mytestrxjava.utils.XGUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.mytestrxjava.XGUrlHelper;
import com.mytestrxjava.activity.GiraffePlayerActivity;


import java.net.URLDecoder;

/**
 * Created by Yomoo on 2017/10/9.
 */

public class XGUtil {

    public static void playXG(String paramString, Activity paramActivity, int paramInt)
    {
        playXG(paramString, paramActivity, paramInt, false);
    }

    public static void playXG(String paramString, Activity paramActivity, int paramInt, boolean paramBoolean)
    {

        for (;;)
        {
            Object localObject1 = "xghome://home";
            try
            {
                Log.e("------------LS:--", "播放视频的主要参数---------------- ");
//                stopTask(paramActivity);
                Intent offLine = new Intent(Cache.broad_offLine);
                offLine.putExtra("type", 1);
                paramActivity.sendBroadcast(offLine);
                String Urlch = URLDecoder.decode(paramString, "UTF-8");
                String str1 = XGUrlHelper.c;
                String[] Url_sp = ((String)Urlch).split("\\|");
                System.out.println(Url_sp);
                String newurl=paramString;

                if ( Url_sp.length==3) {
                    str1 = Url_sp[1];
                    localObject1 = Url_sp[2];
                }
                    String str2 = Url_sp[0].replace("xg://", "ftp://");
                    String[]  httptype = str2.split("/");
                    String str3 = httptype[(httptype.length - 1)];
                    newurl = str2;
                    if (str2.contains("intent://"))
                    {
                        Url_sp = str2.split("#")[0].split("//");
                        newurl = "ftp://" + Url_sp[1];
                    }
                    str2 = "http://127.0.0.1:8083/" + Uri.parse((String)newurl).getLastPathSegment();
                    Intent localIntent = new Intent(paramActivity, GiraffePlayerActivity.class);
                    Bundle localBundle = new Bundle();
                    localBundle.putString("uri", str2);
                    localBundle.putString("refer", (String)localObject1);
                    localBundle.putString("ad", str1);
                    localBundle.putString("newurl", (String)newurl);
                    localBundle.putString("title", str3);
                    localBundle.putInt("movieId", paramInt);
                    localBundle.putString("movieurl", paramString);
                    Log.v("XGUtil", str2 + "..." + (String)localObject1 + "..." + str1);
                    localIntent.putExtras(localBundle);
                    localIntent.setFlags(4194304);
                    paramActivity.startActivityForResult(localIntent, 0);
//                    if (paramBoolean) {
//                        paramActivity.finish();
//                    }
                    return;

            }
            catch (Exception e)
            {
                e.printStackTrace();
                return;
            }

        }
    }

}
