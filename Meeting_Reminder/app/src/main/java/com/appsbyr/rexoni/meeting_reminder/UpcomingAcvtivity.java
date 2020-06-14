package com.appsbyr.rexoni.meeting_reminder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class UpcomingAcvtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Upcoming_frag())
                .commit();
    }
}
