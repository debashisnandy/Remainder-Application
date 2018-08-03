package com.appsbyr.rexoni.meeting_reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class myRemainderNotification extends BroadcastReceiver {

    customSQLDB mySqlDB;
    CustomSQLDB2 customSQLDB2;
    FireTheAlarm fireTheAlarm;
    public myRemainderNotification(){

    }

    static int idNumber=1;
    @Override
    public void onReceive(Context context, Intent intent) {

        mySqlDB= new customSQLDB(context);
        ContentValues contentValues = new ContentValues();
        customSQLDB2= new CustomSQLDB2(context);
        fireTheAlarm = new FireTheAlarm(context);
        if(intent.getAction().equalsIgnoreCase("com.remanider.roni")){
            Log.v("BroadCast_Class","It's working");
            Cursor cursor = mySqlDB.SelectFirstRow();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {


                        builder.setContentTitle(cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Title)))
                                .setContentText(cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Description)))
                                .setSmallIcon(R.drawable.ic_stat_remainder)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText(cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Description)))
                                    .setBigContentTitle(cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Title)))
                                    );
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                        notificationManager.notify(idNumber,builder.build());
                        idNumber+=idNumber;
                        // Store the value to Done Table
                            contentValues.put(CustomSQLDB2.Column_Date,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Date)));
                            contentValues.put(CustomSQLDB2.Column_DateAndTime,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_TimeRoman)));
                            contentValues.put(CustomSQLDB2.Column_Description,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Description)));
                            contentValues.put(CustomSQLDB2.Column_Title,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Title)));
                            contentValues.put(CustomSQLDB2.Column_DateInfo,cursor.getInt(cursor.getColumnIndex(customSQLDB.Column_DateInfo)));
                            contentValues.put(CustomSQLDB2.Column_second,cursor.getInt(cursor.getColumnIndex(customSQLDB.Column_second)));
                            contentValues.put(CustomSQLDB2.Column_Time,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Time)));
                            customSQLDB2.inset(contentValues);
                        String[] del = {cursor.getString(cursor.getColumnIndex(customSQLDB.Column_second))};
                        mySqlDB.delete(customSQLDB.Column_second+"=?",del);
                        fireTheAlarm.FireTheAlarm();

                    }
                } finally {
                    cursor.close();
                }
            }

        }else if (intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){
            Log.v("BroadCast_Else","It's working");
            Cursor cursor = mySqlDB.SelectFirstRow();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {


                        builder.setContentTitle(cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Title)))
                                .setContentText(cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Description)))
                                .setSmallIcon(R.drawable.ic_stat_remainder)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText(cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Description)))
                                        .setBigContentTitle(cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Title)))
                                );
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                        notificationManager.notify(idNumber,builder.build());
                        idNumber+=idNumber;
                        // Store the value to Done Table
                        contentValues.put(CustomSQLDB2.Column_Date,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Date)));
                        contentValues.put(CustomSQLDB2.Column_DateAndTime,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_TimeRoman)));
                        contentValues.put(CustomSQLDB2.Column_Description,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Description)));
                        contentValues.put(CustomSQLDB2.Column_Title,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Title)));
                        contentValues.put(CustomSQLDB2.Column_DateInfo,cursor.getInt(cursor.getColumnIndex(customSQLDB.Column_DateInfo)));
                        contentValues.put(CustomSQLDB2.Column_second,cursor.getInt(cursor.getColumnIndex(customSQLDB.Column_second)));
                        contentValues.put(CustomSQLDB2.Column_Time,cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Time)));
                        customSQLDB2.inset(contentValues);
                        String[] del = {cursor.getString(cursor.getColumnIndex(customSQLDB.Column_second))};
                        mySqlDB.delete(customSQLDB.Column_second+"=?",del);
                        fireTheAlarm.FireTheAlarm();

                    }
                } finally {
                    cursor.close();
                }
            }
        }

    }
}
