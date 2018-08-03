package com.appsbyr.rexoni.meeting_reminder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

public class Pop_upWindow extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popout,container,false);
        Button b1 = view.findViewById(R.id.button1);
        final DatePicker datePicker = view.findViewById(R.id.datePicker);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pop_upWindow.this.dismiss();
                String date = datePicker.getDayOfMonth()+"-"+(datePicker.getMonth()+1) +"-"
                        +datePicker.getYear();
                Set_reminder set_reminder =(Set_reminder) getActivity();
                set_reminder.SetDate(date);
                set_reminder.SetDate(datePicker.getDayOfMonth(),datePicker.getMonth(),datePicker.getYear());
            }
        });
        return view;

    }
}
