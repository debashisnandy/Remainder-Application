package com.appsbyr.rexoni.meeting_reminder;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DoneFragment extends Fragment {

    View rootView;
    ListView lsNews;
    MyCustomAdapter myCustomAdapter;
    CustomSQLDB2 customSqlDB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        customSqlDB= new CustomSQLDB2(getContext());

        rootView= inflater.inflate(R.layout.main_list_view, container, false);
        lsNews=rootView.findViewById(R.id.list);
        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.addrem);
        floatingActionButton.setVisibility(View.GONE);
        setMyList();
        lsNews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final ReminderList s = remind_Data.get(position);
                AlertDialog.Builder b1 = new AlertDialog.Builder(getContext());

                b1.setTitle("Alert")
                        .setMessage("Are you sure").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        customSqlDB.delete(CustomSQLDB2.Column_Date+"=? and "+CustomSQLDB2.Column_Time+"=?",new String[]{s.Date,s.Time});
                        setMyList();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getContext(),"No",Toast.LENGTH_LONG).show();

                    }
                }).show();

                return true;
            }
        });

        return rootView;
    }

    ArrayList<ReminderList> remind_Data= new ArrayList<>();


    public void setMyList(){
        remind_Data.clear();
        Cursor cursor = customSqlDB.query(null,
                null,null,
                CustomSQLDB2.Column_DateInfo+" DESC, "+CustomSQLDB2.Column_second+" DESC");

        if (cursor.moveToFirst()){
            do {

                remind_Data.add(new ReminderList(
                        cursor.getString(cursor.getColumnIndex(CustomSQLDB2.Column_Title)),
                        cursor.getString(cursor.getColumnIndex(CustomSQLDB2.Column_Description)),
                        cursor.getString(cursor.getColumnIndex(CustomSQLDB2.Column_Date)),
                        cursor.getString(cursor.getColumnIndex(CustomSQLDB2.Column_Time))));

            } while (cursor.moveToNext());
        }

        myCustomAdapter = new MyCustomAdapter(remind_Data);

        lsNews.setAdapter(myCustomAdapter);
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<ReminderList> listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<ReminderList>  listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.reminder_list, null);
            final ReminderList s = listnewsDataAdpater.get(position);
            TextView meetTitle= myView.findViewById(R.id.MEET_title);
            meetTitle.setText(s.Meetingtitle);

            TextView meetDescription= myView.findViewById(R.id.Desc);
            meetDescription.setText(s.MeetingDesc);

            TextView meetDate= myView.findViewById(R.id.rem_date);
            meetDate.setText(s.Date);

            TextView meetTime= myView.findViewById(R.id.rem_time);
            meetTime.setText(s.Time);

            return myView;
        }

    }


}
