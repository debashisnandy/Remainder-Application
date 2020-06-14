package com.appsbyr.rexoni.meeting_reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Set_reminder extends AppCompatActivity {

    TextView textDate,textTime,textRpt;
    TextView MeetingTitle,MeetingDesc;
    String[] values;
    String date,time,month;
    int day,year,HOUR,Minute;
    customSQLDB customSqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        textDate = findViewById(R.id.Date_tv);
        textTime = findViewById(R.id.time_Tv);

        MeetingTitle = findViewById(R.id.meet_title);
        MeetingDesc = findViewById(R.id.Descript);
        customSqlDB = new customSQLDB(this);

    }

    public void SetDate(String date) {
        this.date=date;
        textDate.setText(this.date);
    }

    public void SetDate(int day,int month,int year) {
        this.day=day;

        if (month<10){
            this.month = '0'+String.valueOf(month);
        }else {
            this.month= String.valueOf(month);
        }
        this.year=year;
    }


    public void SetTime(String time){
        this.time= time;
        textTime.setText(time);
    }

    public void SetTime(int HOUR,int Minute){
        this.HOUR=HOUR;
        this.Minute=Minute;
    }



    public void dateClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Pop_upWindow pop = new Pop_upWindow();
        pop.show(fragmentManager,"date");
    }

    public void timeClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Pop_upWindowclk pop = new Pop_upWindowclk();
        pop.show(fragmentManager,"time");

    }


    public void setInActivity(View view) {

        if (MeetingTitle.getText().toString()==null ||
                MeetingDesc.getText().toString()==null ||
                time==null || date==null){
            AlertDialog.Builder al = new AlertDialog.Builder(this);
            al.setTitle("Alert")
                    .setMessage("Please fill all required field")
                    .setNeutralButton("Ok", null).show();

        }else {
            ContentValues contentValues = new ContentValues();
            try {
                long seconds= SecondsConversation();
                contentValues.put(customSQLDB.Column_second,seconds);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int myDate = Integer.parseInt(String.valueOf(year)+String.valueOf(month)+String.valueOf(day));
            contentValues.put(customSQLDB.Column_DateInfo,myDate);
            contentValues.put(customSQLDB.Column_Title,MeetingTitle.getText().toString());
            contentValues.put(customSQLDB.Column_Description,MeetingDesc.getText().toString());
            contentValues.put(customSQLDB.Column_TimeRoman,HOUR+":"+Minute);
            contentValues.put(customSQLDB.Column_Time,time);
            contentValues.put(customSQLDB.Column_Date,date);
            customSqlDB.inset(contentValues);
            Intent i = new Intent();
            setResult(RESULT_OK, i);
            finish();
        }
    }

    public long SecondsConversation() throws ParseException {
        String time = HOUR+":"+Minute+":00";
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = dateFormat.parse(time);
        long seconds = date.getTime() / 1000L;
        return seconds;
    }
}
