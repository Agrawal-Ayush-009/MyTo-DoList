package com.example.myto_dolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("Select * from ToDo_List order by id ASC")
    fun getAllTasks() : LiveData<List<Task>>
}