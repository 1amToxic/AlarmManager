package com.example.alarmproject.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.alarmproject.model.Alarm
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

class AlarmUtils {
    val index = 1003
    @RequiresApi(Build.VERSION_CODES.O)
    fun create(context : Context, alarm : Alarm){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context,ScheduleService::class.java)
        intent.putExtra("content_notification","Alarm")
        val pendingIntent =
            PendingIntent.getService(context,index,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val calendar = Calendar.getInstance()
        Log.d("AppLog0",calendar.timeInMillis.toString())
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("AppLog1",calendar.timeInMillis.toString())
            val tmp = alarm.time+":00"
            val time = LocalTime.parse(tmp)
            calendar.set(Calendar.HOUR_OF_DAY, time.hour)
            calendar.set(Calendar.MINUTE, time.minute)
            calendar.set(Calendar.SECOND,0)
            Log.d("AppLog2",calendar.timeInMillis.toString())
            alarmManager
                .setExact(AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis
                    , pendingIntent)
        }
        else{
            val st = StringTokenizer(alarm.time,":")
            val array = arrayListOf<Int>()
            while (st.hasMoreTokens()){
                array.add(st.nextToken().toInt())
            }
            calendar.set(Calendar.HOUR_OF_DAY,array[0])
            calendar.set(Calendar.MINUTE,array[1])
            calendar.set(Calendar.SECOND,0)
            Log.d("AppLog1",calendar.time.toString())
            alarmManager
                .setExact(AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis
                    , pendingIntent)
        }

    }
}