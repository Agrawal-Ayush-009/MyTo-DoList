package com.example.myto_dolist

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),ITasksRVAdapter {

    lateinit var viewModel: TaskViewModel

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerview).layoutManager = LinearLayoutManager(this)
        val adapter = TaskRVAdapter(this, this)
        findViewById<RecyclerView>(R.id.recyclerview).adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TaskViewModel::class.java]
        viewModel.allTasks.observe(this, Observer {list ->
            list?.let{
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(task: Task) {
        viewModel.deleteTask(task)
        Toast.makeText(this, "${task.text} Deleted!", Toast.LENGTH_SHORT).show()
    }

    fun submitText(view: View) {
        Log.d(TAG,"Hello World")
        val taskText = findViewById<EditText>(R.id.input).text.toString()
        if(taskText.isNotEmpty()){
            viewModel.insertTask(Task(taskText))
            Toast.makeText(this,"$taskText Added!", Toast.LENGTH_SHORT).show()
            findViewById<EditText>(R.id.input).setText("")
        }else{
            Toast.makeText(this,"Please Enter a task",Toast.LENGTH_SHORT).show()
        }
    }

}
