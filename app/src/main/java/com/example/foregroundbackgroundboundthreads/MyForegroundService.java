package com.example.foregroundbackgroundboundthreads;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyForegroundService extends Service {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Log.d("service" , "service is running...");
                    try {

                        Thread.sleep(2000);
                    }catch (InterruptedException p ){
                        p.printStackTrace();
                    }

                }
            }
        }).start();

        final String CHANNELID = "Foreground Service Id";
        NotificationChannel channel1 = new NotificationChannel(
                CHANNELID,
                CHANNELID,
                NotificationManager.IMPORTANCE_LOW
        );
        getSystemService(NotificationManager.class).createNotificationChannel(channel1);
        Notification.Builder notification = new Notification.Builder(this,CHANNELID)
                .setContentText("Service is running")
                .setContentTitle("service enabled")
                .setSmallIcon(R.drawable.ic_launcher_background);

        startForeground(1001, notification.build());
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
