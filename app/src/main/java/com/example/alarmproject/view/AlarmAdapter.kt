package com.example.alarmproject.view

import android.content.Context
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmproject.R
import com.example.alarmproject.model.Alarm
import java.util.*
import kotlin.collections.ArrayList

class AlarmAdapter(context : Context,private val alarmListener: AlarmListener) : ListAdapter<Alarm,AlarmAdapter.AlarmViewHolder>(AlarmDiff){
    interface AlarmListener{
        fun onClickTime(alarm : Alarm)
        fun onClickItem(list : MutableList<String>,alarm: Alarm)
    }
    object AlarmDiff :DiffUtil.ItemCallback<Alarm>(){
        override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
            return oldItem == newItem

        }

        override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
            return oldItem.time == newItem.time
        }

    }
    inner class AlarmViewHolder(private val itemview : View) : RecyclerView.ViewHolder(itemview){
        private val tvTime : TextView = itemview.findViewById(R.id.text_time)
        private val tvContent : TextView = itemview.findViewById(R.id.alarm_content)
        private val switchActive : Switch = itemview.findViewById(R.id.switchActive)
        private val layoutExpand : FrameLayout = itemview.findViewById(R.id.layout_expand)
        private val cb2 : CheckBox = itemview.findViewById(R.id.cb2)
        private val cb3 : CheckBox = itemview.findViewById(R.id.cb3)
        private val cb4 : CheckBox = itemview.findViewById(R.id.cb4)
        private val cb5 : CheckBox = itemview.findViewById(R.id.cb5)
        private val cb6 : CheckBox = itemview.findViewById(R.id.cb6)
        private val cb7 : CheckBox = itemview.findViewById(R.id.cb7)
        private val cb8 : CheckBox = itemview.findViewById(R.id.cb8)
        private val listCheckbox = arrayListOf<CheckBox>(cb2,cb3,cb4,cb5,cb6,cb7,cb8)

        fun bind(alarm : Alarm){
            val listSum = ArrayList<String>()
            val listS = StringTokenizer(alarm.listTime,",")
            while(listS.hasMoreTokens()){
                listSum.add(listS.nextToken())
            }
            listCheckbox.forEachIndexed(){
                index,it ->
                run {
                    it.isChecked = (listSum[index] == "1")
                }
            }
            tvTime.text = alarm.time
            tvContent.text = alarm.content
            switchActive.isChecked = alarm.isActive
            tvTime.setOnClickListener{
                alarmListener.onClickTime(alarm)
            }
            itemview.setOnClickListener{
                if(layoutExpand.visibility == View.GONE) {
                    itemview.isActivated = true
                    layoutExpand.visibility = View.VISIBLE
                }
                else{
                    val listSumz =
                        arrayListOf<String>("1","1","1","1","1","1","1")
                    listCheckbox.forEachIndexed{
                            index,it ->
                        run{
                            if(it.isChecked){
                                listSumz[index] = "1"
                            }
                            else{
                                listSumz[index] = "0"
                            }
                        }
                    }
                    alarmListener.onClickItem(listSumz,alarm)
                    layoutExpand.visibility = View.GONE
                }

            }
        }


    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlarmAdapter.AlarmViewHolder {
        return AlarmViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alarm_layout,parent,false))
    }

    override fun onBindViewHolder(holder: AlarmAdapter.AlarmViewHolder, position: Int) {

            holder.bind(getItem(position))

    }

}