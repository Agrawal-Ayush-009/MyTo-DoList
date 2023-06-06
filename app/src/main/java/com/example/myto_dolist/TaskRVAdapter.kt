package com.example.myto_dolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import java.util.EventListener

class TaskRVAdapter(private val context: Context, private val listener: MainActivity): RecyclerView.Adapter<TaskRVAdapter.TaskViewHolder>(){
    private val allTasks = ArrayList<Task>();

    inner class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textview: TextView = itemView.findViewById<TextView>(R.id.taskName)
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task,parent,false)
        val viewHolder = TaskViewHolder(view)
        viewHolder.deleteButton.setOnClickListener{

            listener.onItemClicked(allTasks[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return allTasks.size;
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = allTasks[position]
        holder.textview.text = currentTask.text
    }

    fun updateList(newList: List<Task>){
        allTasks.clear()
        allTasks.addAll(newList)

        notifyDataSetChanged()
    }

}

interface ITasksRVAdapter{
    fun onItemClicked(task: Task)
}