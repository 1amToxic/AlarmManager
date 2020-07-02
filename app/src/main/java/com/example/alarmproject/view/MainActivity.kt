package com.example.alarmproject.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmproject.R
import com.example.alarmproject.model.Alarm
import com.example.alarmproject.utils.AlarmUtils
import com.example.alarmproject.viewmodel.AlarmViewModel
import com.example.alarmproject.viewmodel.AlarmViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AlarmAdapter.AlarmListener{
    private lateinit var recyclerView : RecyclerView
    private lateinit var fabAdd : FloatingActionButton
    private lateinit var adapter : AlarmAdapter
    private lateinit var frameTimePicker : FrameLayout
    private val alarmUtils = AlarmUtils()
    private val viewModel : AlarmViewModel by viewModels (factoryProducer = {
            AlarmViewModelFactory(
                applicationContext
            )
        })
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = AlarmAdapter(this,this)

        viewModel.listAlarm.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
        recyclerView = findViewById(R.id.recycler_alarm)
        fabAdd = findViewById(R.id.fab_add)
        frameTimePicker = findViewById(R.id.frame_time_picker)
        recyclerView.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
        fabAdd.setOnClickListener{
            val dialogFragment = AlarmDialog(listener = { a,b -> doAfterClick(a,b)})
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.frame_time_picker,dialogFragment)
            ft.commit()
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun doAfterClick(a : Int, b : Int){
        val alarm = Alarm(id = viewModel.listAlarm.value!!.size+1, time = "${a}:${b}",content = "Alarm $a $b" ,
            isActive = true,listTime = "1,1,1,1,1,1,1")
        Log.d("AppLog",alarm.toString())
        viewModel.addItem(alarm)
        alarmUtils.create(this,alarm)
    }
    private fun updateTime(a : Int, b : Int, alarm: Alarm){
        alarm.time = "${a}:${b}"
        viewModel.updateItem(alarm)
        adapter.notifyDataSetChanged()
    }
    private fun updateList(alarm: Alarm){
        viewModel.updateItem(alarm)
        adapter.notifyDataSetChanged()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickTime(alarm : Alarm) {
        val dialogFragment = AlarmDialog(listener = { a,b -> updateTime(a,b,alarm)})
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame_time_picker,dialogFragment)
        ft.commit()
    }

    override fun onClickItem(listSum : MutableList<String>, alarm: Alarm) {
        var stringTime = ""
        listSum.forEach {
            stringTime+= "$it,"
        }
        alarm.listTime = stringTime
        Log.d("AppLog",listSum.toString())
        updateList(alarm)
//        TransitionManager.beginDelayedTransition(recyclerView)
    }

}