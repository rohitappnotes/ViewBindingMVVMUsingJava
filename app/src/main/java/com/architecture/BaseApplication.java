package com.architecture;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

public class BaseApplication extends Application {

    public static final String TAG = BaseApplication.class.getSimpleName();
    public static boolean isInBackground = true;

    /**
     * Called when the application is starting, before any other application objects have been created.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
    }

    /**
     * Called by the system when the device configuration changes while your component is running.
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "LANDSCAPE");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i(TAG, "PORTRAIT");
        }
    }

    /*
     * This is called when the overall system is running low on memory, and would like actively
     * running processes to tighten their belts.
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.i(TAG, "onLowMemory()");
    }

    /*
     * Only for testing, not called in production. This method is for use in emulated process
     * environments. It will never be called on a production Android device.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i(TAG, "onTerminate()");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i(TAG, "onTrimMemory(int level)");
    }

    @Override
    protected void attachBaseContext(Context context) {
        try {
            super.attachBaseContext(context);
            MultiDex.install(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "attachBaseContext(Context base) From BaseApplication");
    }

    private static final class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {

        public static final String TAG = MyActivityLifecycleCallbacks.class.getSimpleName();

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            Log.i(TAG, "onActivityCreated:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            Log.i(TAG, "onActivityStarted:" + activity.getLocalClassName());
            /* app moved to foreground */
            isInBackground = false;
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
            Log.i(TAG, "onActivityResumed:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
            Log.i(TAG, "onActivityPaused:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            Log.i(TAG, "onActivityStopped:" + activity.getLocalClassName());
            /* app moved to background */
            isInBackground = true;
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            Log.i(TAG, "onActivitySaveInstanceStat:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            Log.i(TAG, "onActivityDestroyed:" + activity.getLocalClassName());
        }
    }
}
