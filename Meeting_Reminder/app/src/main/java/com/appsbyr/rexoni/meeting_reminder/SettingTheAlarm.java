package com.appsbyr.rexoni.meeting_reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class SettingTheAlarm {

    Context context;
    public SettingTheAlarm(Context context){
        this.context=context;
    }

    public void SetAlarm(int day,int month,int year,int HOUR, int minute){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, HOUR);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.MONTH,(month-1));
        calendar.set(Calendar.YEAR,year);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, myRemainderNotification.class);
        intent.setAction("com.remanider.roni");

        intent.putExtra("MyMessage", "The time is "+String.valueOf(HOUR)+":"+String.valueOf(minute));
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }
}
