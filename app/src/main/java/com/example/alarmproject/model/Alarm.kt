package com.example.alarmproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList
@Entity(tableName = "tbl_alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    var time : String,
    val isActive : Boolean,
    val content : String,
    var listTime : String
) {
    override fun toString(): String {
        return "Alarm(id=$id, time='$time', isActive=$isActive, content='$content', listTime='$listTime')"
    }
}