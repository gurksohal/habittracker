package com.example.habittracker.viewCalendarHabit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dotzlaw.habittracker.TaskAdapter
import com.example.habittracker.R
import com.example.habittracker.database.Habit
import com.example.habittracker.database.HabitDatabase
import com.example.habittracker.databinding.ActivityMainBinding
import com.example.habittracker.newHabit.AddNewHabit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_habit.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    var habitDatabase = HabitDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        setSupportActionBar(toolbar)


        binding.fab.setOnClickListener{
                startActivity(Intent(this,
                    AddNewHabit::class.java))
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            var dateInfo = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            dateDisplayTextView.text = ("Selected Dates: "+dateInfo)
            var liveHabits : List<Habit> = emptyList()
            var context: Context = this
            uiScope.launch {
                liveHabits = getAllHabits()
                val taskAdapter = TaskAdapter(context, liveHabits)
                habitListView.adapter = taskAdapter
            }

            //if(foundRecords!=null && foundRecords.length>0&&foundRecords[0]!=null) {




        }


    }

    private suspend fun getAllHabits(): List<Habit>{
        var habitDao = habitDatabase.getInstance(this).habitDao
        var liveHabits : List<Habit> = emptyList()
        withContext(Dispatchers.IO) {
            liveHabits = habitDao.getAllHabits()
        }
        return liveHabits
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
