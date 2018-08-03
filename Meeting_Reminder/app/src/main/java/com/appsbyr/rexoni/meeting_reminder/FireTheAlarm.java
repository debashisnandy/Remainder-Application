package com.appsbyr.rexoni.meeting_reminder;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FireTheAlarm {
    Context context;
    customSQLDB mySqlDB;
    public FireTheAlarm(Context context){
        this.context=context;
        mySqlDB = new customSQLDB(context);
    }

    public void FireTheAlarm() {
        Cursor cursor = mySqlDB.SelectFirstRow();
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    String date = cursor.getString(cursor.getColumnIndex(customSQLDB.Column_Date));
                    String time = cursor.getString(cursor.getColumnIndex(customSQLDB.Column_TimeRoman));
                    String[] DateStringArr = date.split("-");
                    date=DateStringArr[0];
                    String month= DateStringArr[1];
                    String Year = DateStringArr[2];

                    String[] TimeStringArr = time.split(":");
                    String HOUR = TimeStringArr[0];
                    String minute = TimeStringArr[1];
                    SettingTheAlarm settingTheAlarm = new SettingTheAlarm(context);
                    settingTheAlarm.SetAlarm(Integer.parseInt(date),Integer.parseInt(month),Integer.parseInt(Year),
                            Integer.parseInt(HOUR),Integer.parseInt(minute));

                }
            } finally {
                cursor.close();
            }
        }

    }
}
