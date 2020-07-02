package com.example.alarmproject.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.AlarmRespository

class AlarmViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val respository = AlarmRespository(context)
        return AlarmViewModel(respository) as T
    }

}