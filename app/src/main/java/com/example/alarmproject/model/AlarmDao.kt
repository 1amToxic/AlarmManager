package com.example.alarmproject.model

import androidx.room.*

@Dao
interface AlarmDao {
    @Query("select * from tbl_alarm")
    fun getAllList() : List<Alarm>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(alarm: Alarm)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(alarm : Alarm)
}