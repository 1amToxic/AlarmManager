package com.example.alarmproject

import android.content.Context
import com.example.alarmproject.model.Alarm
import com.example.alarmproject.model.AlarmDao
import com.example.alarmproject.model.AlarmDatabase
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class AlarmRespository (context: Context) {
    private val alarmDao : AlarmDao
    init {
        val db =
            AlarmDatabase.getDatabase(
                context
            )
        alarmDao = db.alarmDao()
    }
    fun getAllListAlarm() : Flowable<List<Alarm>> {
        return Flowable.create<List<Alarm>>({emitter ->
            try{
                val list = alarmDao.getAllList()
                emitter.onNext(list)
                emitter.onComplete()
            }catch (ex : Exception){
                emitter.onError(ex)
            }
        },BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
//        return alarmDao.getAllList()
    }
    fun insertAlarm(alarm : Alarm){
        alarmDao.insert(alarm)
    }
    fun updateAlarm(alarm: Alarm){
        alarmDao.update(alarm)
    }
}