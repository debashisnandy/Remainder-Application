package com.appsbyr.rexoni.meeting_reminder.AutoStartUtils

import android.app.Application

class MyApplication: Application() {

    companion object{
        lateinit var instance:Application
    }

    init {
        instance= this
    }
}