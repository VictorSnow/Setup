package com.victorup.setup;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BootBroadcastReceiver extends BroadcastReceiver {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private static final String TAG = "BootBroadcastReceiver";
    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent);
        Util.bootScript();

        Notification n = getNotification(context, "脚本已执行");
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, n);
    }

    private Notification getNotification (Context context, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( context, "default" ) ;
        builder.setContentTitle( "Setup Script" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_background) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        builder.setDefaults(Notification.DEFAULT_SOUND);
        return builder.build() ;
    }
}
