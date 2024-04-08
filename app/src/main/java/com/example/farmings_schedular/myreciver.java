package com.example.farmings_schedular;



import static android.content.Intent.getIntent;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

public class myreciver extends BroadcastReceiver {
    MediaPlayer mp;
    public final static String CHANNEL_ID = "100";
    public final static int Notify = 100;
    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mp.setLooping(true);
        mp.start();
        Bundle bundle = intent.getExtras();
        String text = bundle.getString("event");
        String time = "Do this work";
        displayTask(context, "You Have Do : "+text);

        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT );
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Bitmap largeIconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);

        NotificationCompat.Builder Builder = new NotificationCompat.Builder(context, "notify_001");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Builder.setLargeIcon(largeIconBitmap);
            Builder.setSmallIcon(R.drawable.m);
            Builder.setContentText(text);
            Builder.setOnlyAlertOnce(true);
            Builder.setContentIntent(pendingIntent);
            Builder.setSubText(time);
            Builder.setChannelId(CHANNEL_ID);
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Reminder",NotificationManager.IMPORTANCE_HIGH));
        }else {
            Builder.setSmallIcon(R.drawable.m);
            Builder.setContentText(text);
            Builder.setContentIntent(pendingIntent);
            Builder.setOnlyAlertOnce(true);
            Builder.setChannelId("CId");
        }
        Notification notification =Builder.build();
        notificationManager.notify(Notify,notification);

    }


    private void displayTask(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        // You can perform other tasks or actions here
    }

}
