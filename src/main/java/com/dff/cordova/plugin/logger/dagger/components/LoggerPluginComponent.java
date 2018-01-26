package com.dff.cordova.plugin.logger.dagger.components;

import com.dff.cordova.plugin.logger.LoggerPlugin;
import com.dff.cordova.plugin.logger.dagger.modules.AppModule;
import com.dff.cordova.plugin.logger.dagger.modules.CordovaModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component to enable DI for the LoggerPlugin to inject other classes
 *
 * @author Anthony Nahas
 * @version 1.0
 * @since 26.01.18
 */

@Singleton
@Component(modules = {AppModule.class, CordovaModule.class})
public interface LoggerPluginComponent {

  void inject(LoggerPlugin loggerPlugin);
}
