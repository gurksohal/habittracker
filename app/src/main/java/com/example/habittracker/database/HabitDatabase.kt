package com.example.habittracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

//IMPORTANNTNT:  up the version number every time you change the schema
@Database(entities = [Habit::class], version=1,exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {

    abstract val habitDao : HabitDao

    companion object{
        @Volatile
        private var INSTANCE: HabitDatabase? = null

        fun getInstance(context: Context) : HabitDatabase {
            synchronized(lock = this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HabitDatabase::class.java,
                        "list_of_habit_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}