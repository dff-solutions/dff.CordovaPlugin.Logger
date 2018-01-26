package com.dff.cordova.plugin.logger;

import android.content.Context;
import android.util.Log;
import com.dff.cordova.plugin.logger.classes.CustomWebChromeClient;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import org.apache.cordova.*;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

/**
 * @author Anthony Nahas
 * @version 0.1.2
 * @since 26.01.18
 */
public class LoggerPlugin extends CordovaPlugin {

    private static final String TAG = "LoggerPlugin";

    private Context mContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "on initialize console plugin");

//    mContext = cordova.getActivity().getApplicationContext();
//    String packageName = mContext.getPackageName();
//    Resources resources = mContext.getResources();
        CordovaActivity ca = (CordovaActivity) cordova.getActivity();
        SystemWebView view = (SystemWebView) webView.getView();
        CordovaWebViewEngine webViewEngine = webView.getEngine();

        mContext = cordova.getActivity().getApplicationContext();

        // Initialize Realm
        Realm.init(mContext);

        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
            .name("logs.realm")
//            .encryptionKey(getKey())
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()
            .build();

        // Get a Realm instance for this thread
        Realm realm = Realm.getInstance(config);

        if (webViewEngine != null) {
            Log.d(TAG, "Web view engine is not noll");
        } else {
            Log.e(TAG, "Web view engine is noll");
        }
//        CustomWebChromeClient c = new CustomWebChromeClient((SystemWebViewEngine) webView.getEngine());
        view.setWebChromeClient(new CustomWebChromeClient((SystemWebViewEngine) webView.getEngine()));
    }
}
