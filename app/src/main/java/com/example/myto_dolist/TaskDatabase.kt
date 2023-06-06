package com.example.myto_dolist

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object{

        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            // if INSTANCE is not Null, then return it,
            //if it is null, then CREATE the DATABASE

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }

    }
}