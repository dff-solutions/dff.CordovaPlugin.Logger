package com.dff.cordova.plugin.logger;

import android.content.Context;
import android.util.Log;
import com.dff.cordova.plugin.dagger2.annotations.ApplicationContext;
import com.dff.cordova.plugin.logger.classes.CustomWebChromeClient;
import com.dff.cordova.plugin.logger.dagger.components.DaggerLoggerPluginComponent;
import com.dff.cordova.plugin.logger.dagger.modules.AppModule;
import com.dff.cordova.plugin.logger.dagger.modules.CordovaModule;
import io.realm.Realm;
import io.realm.RealmResults;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.engine.SystemWebView;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Anthony Nahas
 * @version 1.0.0-beta
 * @since 26.01.18
 */
public class LoggerPlugin extends CordovaPlugin {

    private static final String TAG = "LoggerPlugin";

    @Inject
    @ApplicationContext
    Context mContext;

    @Inject
    SystemWebView mSystemWebView;

    @Inject
    CustomWebChromeClient mCustomWebChromeClient;

    @Inject
    Realm mRealm;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "on initialize console plugin");

        DaggerLoggerPluginComponent
            .builder()
            .appModule(new AppModule(cordova.getActivity().getApplication()))
            .cordovaModule(new CordovaModule(webView))
            .build()
            .inject(this);

        this.mSystemWebView.setWebChromeClient(mCustomWebChromeClient);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date targetDate = calendar.getTime();

        // Build the query looking at all users:
        RealmResults<com.dff.cordova.plugin.logger.classes.Log> result =
            mRealm
                .where(com.dff.cordova.plugin.logger.classes.Log.class)
                .lessThan("timestamp", targetDate)
                .findAllAsync();

        mRealm.executeTransactionAsync(realm ->
                realm
                    .where(com.dff.cordova.plugin.logger.classes.Log.class)
                    .lessThan("timestamp", targetDate)
                    .findAll()
                    .deleteAllFromRealm(),
            () -> {
                // Transaction was a success.
                Log.d(TAG, "Delete of old logs is done");
            }, error -> {
                // Transaction failed and was automatically canceled.
                Log.e(TAG, "Transaction failed and was automatically canceled for deleting logs--> "
                    + " Error: "
                    + error);
            });
    }
}
