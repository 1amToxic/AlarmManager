package com.example.alarmproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Alarm::class],version = 1)
abstract class AlarmDatabase : RoomDatabase(){
    abstract fun alarmDao() : AlarmDao
    companion object{
        private var instance : AlarmDatabase? = null
        fun getDatabase(context : Context) : AlarmDatabase{

                synchronized(AlarmDatabase::class.java){
                    instance = Room.databaseBuilder(context,
                        AlarmDatabase::class.java,
                    "alarmbbd.db").fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
            return instance!!
        }
    }
}