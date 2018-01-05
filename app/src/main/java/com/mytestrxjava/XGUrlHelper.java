package com.mytestrxjava;

/**
 * Created by Yomoo on 2017/10/9.
 */



import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.mytestrxjava.utils.XGUtils.SHA;


public class XGUrlHelper
{
    public static String A = "VideoAd_Click";
    public static String B = "DownPage";
    public static String C = "NotDownFinish";
    public static String D = "NotDownFinish_Start_Down";
    public static String E = "NotDownFinish_Pause_Down";
    public static String F = "NotDownFinish_Delete";
    public static String G = "NotDownFinish_Play";
    public static String H = "DownFinish";
    public static String I = "DownFinish_Delete";
    public static String J = "DownFinish_Play";
    public static String K = "PlayRecord";
    public static String L = "PlayRecord_Delete";
    public static String M = "PlayRecord_Play";
    public static String N = "AddDownUrl";
    public static String O = "AddDownUrl_Submit";
    public static String P = "Browserpage";
    public static String Q = "Browserpage_Searchs";
    public static String R = "Browserpage_MainPages";
    public static String S = "Browserpage_Backs";
    public static String T = "Browserpage_Forwards";
    public static String U = "Browserpage_Collections";
    public static String V = "Browserpage_Collections_CleanAlls";
    public static String W = "Browserpage_Historys";
    public static String X = "Browserpage_Historys_CleanAlls";
    public static String Y = "Browserpage_Channel_Ref";
    public static String Z = "PersonalService";
    public static String a = "1.0.4";
    public static String aa = "PersonalService_LocalVideo";
    public static String ab = "PersonalService_LocalRefreshs";
    public static String ac = "PersonalService_LocalTimes";
    public static String ad = "PersonalService_LocalSizes";
    public static String ae = "Setting";
    public static String af = "Setting_3G4G_false";
    public static String ag = "Setting_3G4G_true";
    public static String ah = "Setting_Initialize";
    public static String ai = "Setting_CheckUpdate";
    public static String aj = "Setting_AboutUs";
    public static String ak = "Setting_FeedbackProblems";
    public static String al = "Setting_Locks_false";
    public static String am = "Setting_Locks_true";
    public static String an = "OutBrowserPlays";
    public static String ao = "Check_is_UpdateApp";
    public static String b = "http://anan.client51.com:8088/v2/";
    public static String c = b + "ad.asp";
    public static String d = b + "favor.asp?aid=com.xigua&c=0&k=";
    public static String e = b + "home.asp";
    public static String f = b + "version.asp";
    public static String g = b + "search.asp?word=";
    public static String h = "http://static.xigua.com/xigua.apk";
    public static String i = "http://anan.client51.com:8088/v3/home.asp";
    public static String j = "http://anan.client51.com:8088/v3/aside.asp?";
    public static String k = "http://anan.client51.com:8088/v3/haojs.asp";
    public static String l = "http://anan.client51.com:8088/v3/hao.asp";
    public static String m = "IsShowView";
    public static int n = 0;
    public static int o = 1;
    public static int p = 2;
    public static int q = 3;
    public static String r = "videodb.db";
    public static String s = "url_historydb.db";
    public static String t = "url_collectionsdb.db";
    public static String u = "http://mm.myzyzy.com:22588/";
    public static String v = "http://mm.myzyzy.com:22588";
    public static String w = "xigua.official@gmail.com";
    public static String x = "AD";
    public static String y = "FirstPageAd_Click";
    public static String z = "PauseAd_Click";

//    public static String a(Context paramContext)
//    {
//        String str1 = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
//        if (str1 == null) {
//            str1 = "";
//        }
//        for (;;)
//        {
//            String str2 = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
//            paramContext = str2;
//            if (str2 == null) {
//                paramContext = "";
//            }
//            try
//            {
//                paramContext = SHA.encryptSHA(SHA.encryptSHA(paramContext) + SHA.encryptSHA(str1));
//                return paramContext;
//            }
//            catch (Exception paramContext)
//            {
//                paramContext.printStackTrace();
//                return null;
//            }
//        }
//    }
}
