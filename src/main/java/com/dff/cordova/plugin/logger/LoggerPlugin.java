package com.dff.cordova.plugin.logger;

import android.util.Log;
import com.dff.cordova.plugin.logger.classes.CustomWebChromeClient;
import org.apache.cordova.*;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

public class LoggerPlugin extends CordovaPlugin {

    private static final String TAG = "LoggerPlugin";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "on initialize console plugin");
//    mContext = cordova.getActivity().getApplicationContext();
//    String packageName = mContext.getPackageName();
//    Resources resources = mContext.getResources();
        CordovaActivity ca = (CordovaActivity) cordova.getActivity();
        SystemWebView view = (SystemWebView) webView.getView();
        CordovaWebViewEngine c14124 = webView.getEngine();


        if (c14124 != null) {
            Log.d(TAG, "Iam not null");
        } else {
            Log.d(TAG, "nullllllll");
        }
//        CustomWebChromeClient c = new CustomWebChromeClient((SystemWebViewEngine) webView.getEngine());
        view.setWebChromeClient(new CustomWebChromeClient((SystemWebViewEngine) webView.getEngine()));
    }
}
