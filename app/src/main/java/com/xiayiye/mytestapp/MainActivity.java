package com.xiayiye.mytestapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends Activity {

    private WebView wvInternetPage;
    private TextView tvTittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wvInternetPage = findViewById(R.id.wv_internet_page2);
        tvTittle = findViewById(R.id.tv_tittle2);
        WebViewOption();
        tvTittle.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                //获取默认桌面的activity
                Intent intent2 = new Intent(Intent.ACTION_MAIN);
                intent2.addCategory(Intent.CATEGORY_HOME);
                final ResolveInfo res = getPackageManager().resolveActivity(intent2, 0);
                if (res.activityInfo == null) {
                    Log.d("打印", "resolveActivity--->activityInfo null");
                    // should not happen. A home is always installed, isn't it?
                } else if (res.activityInfo.packageName.equals("android")) {
                    // No default selected
                    Log.d("打印", "resolveActivity--->无默认设置");
                } else {
                    // res.activityInfo.packageName and res.activityInfo.name gives
                    // you the default app
                    Log.d("默认桌面为：", res.activityInfo.packageName + "="
                            + res.activityInfo.name);
                }
                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                Log.d("包名", "pkg:" + cn.getPackageName());//包名
                Log.d("打开activity的全路径", "cls:" + cn.getClassName());//包名加类名


                //跳转到android默认桌面,第一个参数是包名，第二个是打开的页面所的全路径
//                ComponentName cName = new ComponentName(res.activityInfo.packageName, res.activityInfo.name);
//                ComponentName cName = new ComponentName("com.sec.android.app.launcher", "com.android.launcher2.Launcher");
                ComponentName cName = new ComponentName("phonemsg.yhsh.cn.gaosimohu", "phonemsg.yhsh.cn.gaosimohu.ShowImageActivity");
                Intent intent = new Intent();
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cName);
                startActivity(intent);

            }
        });
    }

    private void WebViewOption() {
        WebSettings settings = wvInternetPage.getSettings();
        settings.setJavaScriptEnabled(true);
        //允许打开js新窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        wvInternetPage.setWebViewClient(new MyWebViewClient());
        wvInternetPage.loadUrl("https://www.yunzhijia.com/pubacc/public/data/19/06/26/ewsqZbAD.html");
//        wvInternetPage.loadUrl("https://www.baidu.com");
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            tvTittle.setText(view.getTitle());
            super.onPageFinished(view, url);
        }
    }

    @Override
    public void onBackPressed() {
        if (wvInternetPage.canGoBack()) {
            wvInternetPage.goBack();
            return;
        }
        super.onBackPressed();
    }
}
