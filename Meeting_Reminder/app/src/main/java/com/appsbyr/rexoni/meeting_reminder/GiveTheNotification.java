package com.appsbyr.rexoni.meeting_reminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class GiveTheNotification {

    int notifyID;
    String CHANNEL_ID;
    NotificationCompat.Builder builder;
    Context context;
    String nameChannel;
    NotificationChannel mChannel;
    public GiveTheNotification(Context context){
        this.context=context;
        nameChannel= "Remainder Notification";
        CHANNEL_ID = "Remainder_Channel_01";
        builder = new NotificationCompat.Builder(context,CHANNEL_ID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID,
                    nameChannel, NotificationManager.IMPORTANCE_HIGH);
        }
    }

    public void notiFy(String title,String Description){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setContentTitle(title)
                    .setContentText(Description)
                    .setSmallIcon(R.drawable.ic_stat_remainder)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(Description)
                            .setBigContentTitle(title)
                    );
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(notifyID, builder.build());
        }else {
            builder.setContentTitle(title)
                    .setContentText(Description)
                    .setSmallIcon(R.drawable.ic_stat_remainder)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(Description)
                            .setBigContentTitle(title)
                    );
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
            notificationManager.notify(notifyID, builder.build());
        }
    }


}
