package com.example.myto_dolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application){
    val allTasks: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val dao = TaskDatabase.getDatabase(application).getTaskDao()
        repository = TaskRepository(dao)
        allTasks = repository.allTasks
    }

    fun deleteTask(task: Task) = viewModelScope.launch (Dispatchers.IO) {
        repository.delete(task)
    }

    fun insertTask(task: Task) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(task)
    }
}