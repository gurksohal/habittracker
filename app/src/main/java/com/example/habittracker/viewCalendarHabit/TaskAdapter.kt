package com.dotzlaw.habittracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.habittracker.R
import com.example.habittracker.database.Habit

class TaskAdapter(c: Context, var habits: List<Habit>?) :
    BaseAdapter() {
    var mInflater: LayoutInflater
    override fun getCount(): Int {
        return habits!!.size
    }

    override fun getItem(position: Int): Any {
        return habits!![position].habit
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //this returns a view instance that represents ONE row of listView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View = mInflater.inflate(R.layout.list_view_detail, null)
        val habitTextView =
            v.findViewById<View>(R.id.habitTextView) as TextView
        //String name = list.get(position).toString();
// for(int i = 0; i < habitNames.length;i++)
        if (habits != null && habits!!.size > 0 && habits!![0] != null) habitTextView.text =
            (position + 1).toString() + ". " + habits!![position].habit else habitTextView.text =
            "No habits scheduled"
        return v
    }

    init {
        mInflater =
            c.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}