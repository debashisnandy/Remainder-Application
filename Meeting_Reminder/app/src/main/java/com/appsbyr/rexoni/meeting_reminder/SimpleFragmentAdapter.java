package com.appsbyr.rexoni.meeting_reminder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {

    private Context mcontext;

    public SimpleFragmentAdapter(Context context,FragmentManager fm){
        super(fm);
        mcontext=context;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return mcontext.getString(R.string.catagory_upcoming);
        }else if (position==1){
            return mcontext.getString(R.string.catagory_done);
        }else {
            return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Upcoming_frag();
            case 1:
                return new DoneFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
