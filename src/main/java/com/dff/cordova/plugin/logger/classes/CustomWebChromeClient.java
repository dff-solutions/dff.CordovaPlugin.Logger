package com.dff.cordova.plugin.logger.classes;

import android.content.Context;
import android.util.Log;
import android.webkit.ConsoleMessage;

import org.apache.cordova.engine.SystemWebChromeClient;
import org.apache.cordova.engine.SystemWebViewEngine;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by anahas on 24.02.2017.
 *
 * @author Anthony Nahas
 * @version 1.0
 * @since 24.02.17
 */
public class CustomWebChromeClient extends SystemWebChromeClient {

  private static final String TAG = "CustomWebChromeClient";
  private Realm mRealm;

  @Inject
  public CustomWebChromeClient(SystemWebViewEngine parentEngine,
                               Realm mRealm) {
    super(parentEngine);
    this.mRealm = mRealm;
  }

  @Override
  public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
    Log.d(TAG, "onConsoleMessage");
    if (consoleMessage.message() != null) {


      Log.d(TAG, "LEVEL: "
        + consoleMessage.messageLevel()
        + consoleMessage.sourceId()
        + " : Line"
        + consoleMessage.lineNumber()
        + " : "
        + consoleMessage.message());

      com.dff.cordova.plugin.logger.classes.Log newLog = com.dff.cordova.plugin.logger.classes.Log
        .newInstance()
        .build(consoleMessage.messageLevel().name(),
          consoleMessage.message(),
          consoleMessage.lineNumber(),
          consoleMessage.sourceId());

      mRealm.executeTransactionAsync(realm -> realm.insert(newLog),
        () -> {
          // Transaction was a success.
          Log.d(TAG, "Transaction was a success for --> " + newLog);
        }, error -> {
          // Transaction failed and was automatically canceled.
          Log.e(TAG, "Transaction failed and was automatically canceled for --> "
            + newLog
            + " Error: "
            + error);
        });
    }
    return true;
  }
}
