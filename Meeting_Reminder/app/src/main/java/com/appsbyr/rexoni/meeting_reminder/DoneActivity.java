package com.appsbyr.rexoni.meeting_reminder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DoneFragment())
                .commit();
    }
}
