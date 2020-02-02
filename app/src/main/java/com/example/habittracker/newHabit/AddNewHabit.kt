package com.example.habittracker.newHabit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habittracker.R
import com.example.habittracker.database.Habit
import com.example.habittracker.database.HabitDatabase
import com.example.habittracker.databinding.ActivityAddNewHabitBinding
import kotlinx.android.synthetic.main.fragment_new_habit_title.*
import kotlinx.coroutines.*

class AddNewHabit : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewHabitBinding
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    var habitDatabase = HabitDatabase



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_habit)
          //  habitDao.insert(Habit(0,"TEST",0))


        button3.setOnClickListener(){

            var habit = Habit()
            habit.habit = editText.text.toString()
            habit.totalCompeted = 2

           uiScope.launch {
                insert(habit)
            }

        }

    }

    private suspend fun insert(habit: Habit){
        var habitDao = habitDatabase.getInstance(this).habitDao
        withContext(Dispatchers.IO) {
            habitDao.insert(habit)
        }
    }


}
