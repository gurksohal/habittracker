package com.example.habittracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert
    fun insert(habit: Habit)

    @Update
    fun update(habit: Habit)

    @Query("SELECT * FROM list_of_habit_table WHERE habitId = :key")
    fun get(key:Long): Habit

    @Query("SELECT * FROM list_of_habit_table")
    fun getAllHabits(): List<Habit>


}