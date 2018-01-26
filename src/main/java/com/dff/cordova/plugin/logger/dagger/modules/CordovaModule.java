package com.dff.cordova.plugin.logger.dagger.modules;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anahas on 26.01.2018.
 *
 * @author Anthony Nahas
 * @version 1.0
 * @since 26.01.2018
 */
@Module
public class CordovaModule {

  CordovaWebView mWebView;

  public CordovaModule(CordovaWebView mWebView) {
    this.mWebView = mWebView;
  }

  @Provides
  public SystemWebView provideSystemWebView() {
    return (SystemWebView) this.mWebView.getView();
  }

  @Provides
  public SystemWebViewEngine provideSystemWebViewEngine() {
    return (SystemWebViewEngine) this.mWebView.getEngine();
  }

}
