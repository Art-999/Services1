package com.example.arturmusayelyan.services1;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by artur.musayelyan on 12/01/2018.
 */

@SuppressLint("Registered")
public class MyService extends Service {
    private final String LOG_TAG = "Art";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");

        someTask();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    public void someTask() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 7; i++) {
                    SystemClock.sleep(1000);
                    Log.d(LOG_TAG, "i= " + i);
                }
                stopSelf();
            }
        });
        thread.start();
    }
}
