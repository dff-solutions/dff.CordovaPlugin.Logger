package com.dff.cordova.plugin.logger.classes;

import android.content.Context;
import android.util.Log;
import android.webkit.ConsoleMessage;
import org.apache.cordova.engine.SystemWebChromeClient;
import org.apache.cordova.engine.SystemWebViewEngine;

/**
 * Created by anahas on 24.02.2017.
 *
 * @author Anthony Nahas
 * @version 1.0
 * @since 24.02.17
 */
public class CustomWebChromeClient extends SystemWebChromeClient {

  private static final String TAG = "CustomWebChromeClient";
  private Context mContext;

  public CustomWebChromeClient(SystemWebViewEngine parentEngine) {
    super(parentEngine);
  }

  @Override
  public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
    Log.d(TAG, "onConsoleMessage");
    if (consoleMessage.message() != null)
      Log.d(TAG, consoleMessage.sourceId() + " : Line"
        + consoleMessage.lineNumber() + " : "
        + consoleMessage.message());
    return true;
  }
}
