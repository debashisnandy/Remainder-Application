package com.appsbyr.rexoni.meeting_reminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
