package com.dff.cordova.plugin.logger.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import com.dff.cordova.plugin.dagger2.annotations.ApplicationContext;
import com.dff.cordova.plugin.dagger2.annotations.DefaultUncaughException;
import com.dff.cordova.plugin.dagger2.annotations.Private;
import com.dff.cordova.plugin.dagger2.annotations.Shared;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;
import java.io.File;

/**
 * The @Module annotation tells Dagger that the AppModule classes will provide dependencies for a part
 * of the mApp. It is normal to have multiple Dagger modules in a project, and it is typical
 * for one of them to provide app-wide dependencies.
 *
 * @author Anthony Nahas
 * @version 1.0
 * @since 26.01.18
 */
@Module
public class AppModule {

    private static final String TAG = AppModule.class.getSimpleName();

    private Application mApp;

    public AppModule(Application app) {
        this.mApp = app;
        // Initialize Realm
        Realm.init(this.mApp);
    }

    public Application getApp() {
        return mApp;
    }

    @Provides
    @ApplicationContext
    public Context provideContext() {
        return mApp;
    }

    @Provides
    public Application provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    @DefaultUncaughException
    public Thread.UncaughtExceptionHandler provideDefaultThreadUncaughtExceptionHandler() {
        return Thread.getDefaultUncaughtExceptionHandler();
    }

    @Provides
    @Singleton
    @Shared
    public SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mApp);
    }

    @Provides
    @Singleton
    @Private
    public SharedPreferences providePrivateSharedPreferences() {
        return mApp.getSharedPreferences("dff-cordova-plugin-logger", Context.MODE_PRIVATE);
    }

    // The RealmConfiguration is created using the builder pattern.
    // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
    @Provides
    public RealmConfiguration provideRealmConfiguration(@ApplicationContext Context context) {

        String externalStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String packageName = context.getApplicationContext().getPackageName();
        String path = externalStorageDir
            + File.separator
            + "Android"
            + File.separator
            + "data"
            + File.separator
            + packageName
            + File.separator
            + "realm"
            + File.separator;

        File file = new File(path);
        Log.d(TAG, "File: " + file.getAbsolutePath() + " exists: " + file.exists());
        Log.d(TAG, "File: " + file.getAbsolutePath() + " should be created: " + file.mkdirs());

        return new RealmConfiguration.Builder()
            .directory(new File(path))
            .name("logs.realm")
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()
            .build();
    }

    @Provides
    public Realm providesRealm(RealmConfiguration realmConfiguration) {
        return Realm.getInstance(realmConfiguration);
    }

}
