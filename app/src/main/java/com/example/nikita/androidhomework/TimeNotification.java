package com.example.nikita.androidhomework;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class TimeNotification extends BroadcastReceiver {

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
        nManager.notify(0, mBuilder.build());
    }
}
