package com.example.nikita.androidhomework;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class TimeReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intentTL = new Intent(context, WakeUpActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intentTL, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "101")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Будильник")
                .setContentText("Самое время пойти выпить!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(ringtoneUri);

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("101", "My channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            nManager.createNotificationChannel(channel);
        }

        nManager.notify(0, mBuilder.build());
    }
}
