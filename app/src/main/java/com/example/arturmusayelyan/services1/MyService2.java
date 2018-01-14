package com.example.arturmusayelyan.services1;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by USER on 14.01.2018.
 */

public class MyService2 extends Service {
    private ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int time = intent.getIntExtra(Main2Activity.EXECUTION_TIME, 1);
        PendingIntent pendingIntent = intent.getParcelableExtra(Main2Activity.PENDING_INTENT);

        MyThread thread = new MyThread(startId, time, pendingIntent);
        executorService.execute(thread);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MyThread implements Runnable {
        int time;
        PendingIntent pendingIntent;
        int startId;

        public MyThread(int startId, int time, PendingIntent pendingIntent) {
            this.startId = startId;
            this.time = time;
            this.pendingIntent = pendingIntent;
        }

        @Override
        public void run() {
            try {
                pendingIntent.send(Main2Activity.STATUS_START);
                SystemClock.sleep(time);
                Intent intent = new Intent();
                intent.putExtra(Main2Activity.TASK_RESULTCODE, time * 1000);
                pendingIntent.send(MyService2.this, Main2Activity.STATUS_FINISH, intent);
                stopSelf(startId);

            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }

}
