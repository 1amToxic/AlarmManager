package com.example.alarmproject.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.alarmproject.R
import java.util.*

class AlarmDialog(val listener : ((Int,Int) -> Unit)) : DialogFragment(){
    lateinit var time : TimePicker
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        time = view.findViewById(R.id.time_picker)

        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener{
            dismiss()
        }
        view.findViewById<Button>(R.id.btn_ok).setOnClickListener{
            var min : Int = 0
            var hour : Int = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                min = time.minute
                hour = time.hour
            } else {
                min = time.currentMinute
                hour = time.currentHour
            }
            listener(hour,min)
            dismiss()
        }
    }
}