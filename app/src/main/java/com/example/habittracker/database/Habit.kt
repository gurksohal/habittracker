package com.example.habittracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="list_of_habit_table")
data class Habit (

    @PrimaryKey(autoGenerate = true)
    var habitId: Long = 0L,

    @ColumnInfo(name = "habit_title")
    var habit: String = "",

    @ColumnInfo(name = "total_completed_count")
    var totalCompeted : Long = 0L


    )