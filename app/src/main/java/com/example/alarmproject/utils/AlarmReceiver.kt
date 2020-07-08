package com.example.alarmproject.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.action == "FOO_ACTION"){
            val fooString  = intent.getStringExtra("content_notification")
            Log.d("xxxxxxxx1",fooString)
        }
    }

}