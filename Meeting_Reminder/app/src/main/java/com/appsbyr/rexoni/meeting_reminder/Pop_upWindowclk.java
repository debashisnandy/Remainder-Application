package com.appsbyr.rexoni.meeting_reminder;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;

public class Pop_upWindowclk extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pop_clock,container,false);
        Button b1 = view.findViewById(R.id.clocki);
        final TimePicker timePicker = view.findViewById(R.id.clk_inp);
        b1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Pop_upWindowclk.this.dismiss();
                String time = getTime(timePicker.getHour(),timePicker.getMinute());
                Set_reminder set_reminder =(Set_reminder) getActivity();
                set_reminder.SetTime(time);
                set_reminder.SetTime(timePicker.getHour(),timePicker.getMinute());
            }
        });
        return view;

    }

    private String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(tme);
    }

}
