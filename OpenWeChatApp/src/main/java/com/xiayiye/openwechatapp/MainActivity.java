package com.xiayiye.openwechatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author DELL
 * 2019年8月22日10:01:41
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openApp(View view) {
        // 填应用AppId
//        String appId = "";
        String appId = "移动应用APPID";
        IWXAPI api = WXAPIFactory.createWXAPI(this, appId);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        // 填小程序原始id
        req.userName = "小程序的原始id";
        //拉起小程序页面的可带参路径，不填默认拉起小程序首页
//        req.path = "拉起小程序页面的可带参路径";
        // 可选打开 开发版，体验版和正式版
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW;
        api.sendReq(req);
    }
}
