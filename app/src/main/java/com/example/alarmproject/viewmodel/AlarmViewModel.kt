package com.example.alarmproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmproject.model.Alarm
import com.example.alarmproject.AlarmRespository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class AlarmViewModel(private val respository: AlarmRespository): ViewModel(){
    val listAlarm = MutableLiveData<List<Alarm>>()
    init {
        respository.getAllListAlarm().observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listAlarm.value = it
            },{},{})
    }
    fun addItem(alarm: Alarm){
        val list : MutableList<Alarm> = listAlarm.value!! as MutableList<Alarm>
        list.add(alarm)
        listAlarm.value = list
        respository.insertAlarm(alarm)
    }
    fun updateItem(alarm: Alarm){
        val list : MutableList<Alarm> = listAlarm.value!! as MutableList<Alarm>
        list.forEachIndexed() {
            index,it ->
            run {
                if (it.id == alarm.id) {
                    list[index] = alarm
                }
            }
        }
        listAlarm.value = list
        respository.updateAlarm(alarm)
    }
}