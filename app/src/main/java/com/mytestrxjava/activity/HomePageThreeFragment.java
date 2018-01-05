package com.mytestrxjava.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mytestrxjava.MyApplication;
import com.mytestrxjava.R;
import com.mytestrxjava.activity.base.BaseFragment;
import com.mytestrxjava.utils.LogUtils;
import com.mytestrxjava.utils.XGUtils.XGUtil;


import bean.CommonException;
import bean.MeiJuVideoBean;
import butterknife.BindView;
import meijukankan.MeiJuKanKan;

/**
 * Created by Yomoo on 2017/8/30.
 */

public class HomePageThreeFragment extends BaseFragment {
@BindView(R.id.threeText)
    TextView textView;
    @BindView(R.id.chlid3_webView)
    WebView mWebView;
    ProgressDialog   dialog;

//    private String url="http://mobile.rr.tv/pages/videoShare/?id=350669";
    private String url="http://m.meijutt.com/video/23080-0-0.html";

    private String url2="http://m.meijukankan.net/mm/?s=Player-Index-vid-1482-part-1/geth5.php?mp=http://pan.meijukankan.net/tianyi/down.php/800080216/wl/3139731648139452.mp4";
    private String htmlurl;
    private MeiJuKanKan mMeiJuKanKan;
    private MeiJuVideoBean newsItems;
    @Override
    public int getLayoutResId() {
        return R.layout.home_page_three;
    }

    @Override
    public void finishCreateView(Bundle state) {
        loadData();


    }

    @Override
    protected void loadData() {
        super.loadData();
        mMeiJuKanKan =new MeiJuKanKan();
        getHtml(url);
//        new LoadDatasTask().execute();

    }

    private void getHtml(String videoUrl) {
//        WebView mWebView = new WebView(MyApplication.getInstance().getApplicationContext());
        // 允许执行javascript语句
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        mWebView.loadUrl(videoUrl);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {

                LogUtils.dLog("shouldOverrideUrlLoading");
                paramWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                WebView.HitTestResult localHitTestResult = paramWebView.getHitTestResult();
                LogUtils.dLog( "url:" + paramString);
                String str = paramString;
                if (paramString.startsWith("intent://"))
                {
                    int i = paramString.indexOf("//");
                    int j = paramString.indexOf("#Intent");
                    str = "xg:" + paramString.substring(i, j);
                  LogUtils.dLog("截取后url = " + str);
                }
                LogUtils.dLog("-------count5"+"url:" + str);
                if ((str.startsWith("xg://")) || (str.startsWith("ftp://")))
                {
//                    paramWebView = BrowserOpenFragment.this;
//                    paramWebView.count += 1;

                    LogUtils.dLog("-------截取到视频连接开始播放准备"+str);
                    XGUtil.playXG(paramString,getSupportActivity(),0);
                    return true;
                }

                if ((!TextUtils.isEmpty(str)) && (localHitTestResult == null))
                { LogUtils.dLog("-------没有截取到视频连接"+str);
                    paramWebView.loadUrl(str);

                    return true;
                }
                paramWebView.loadUrl(paramString);
                return super.shouldOverrideUrlLoading(paramWebView, str);
//                paramWebView.loadUrl(str);
//                XGApplication.a(str);
//                return true;


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                        + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

    }
    public void loadUrl(String url)
    {
        if(mWebView != null)
        {
            mWebView.loadUrl(url);
            dialog = ProgressDialog.show(getActivity(),null,"页面加载中，请稍后..");
            mWebView.reload();
        }
    }
    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            // html 就是网页的数据 </span>
//            System.out.println("====>html=" + html);

            htmlurl=html;
        }
    }
    /**
     * 记载数据的异步任务
     *
     * @author zhy
     */
    class LoadDatasTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                newsItems = mMeiJuKanKan.MeiJuGetVideo(url);
            } catch (CommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            textView.setText(newsItems.getVideoUrl());
            LogUtils.dLog(newsItems.toString());

        }

    }



}
