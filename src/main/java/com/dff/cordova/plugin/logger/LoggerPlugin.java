package com.dff.cordova.plugin.logger;

import android.content.Context;
import android.util.Log;
import com.dff.cordova.plugin.dagger2.annotations.ApplicationContext;
import com.dff.cordova.plugin.logger.classes.CustomWebChromeClient;
import com.dff.cordova.plugin.logger.dagger.components.DaggerLoggerPluginComponent;
import com.dff.cordova.plugin.logger.dagger.modules.AppModule;
import com.dff.cordova.plugin.logger.dagger.modules.CordovaModule;
import io.realm.Realm;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.engine.SystemWebView;

import javax.inject.Inject;


/**
 * @author Anthony Nahas
 * @version 0.1.2
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
  }
}
