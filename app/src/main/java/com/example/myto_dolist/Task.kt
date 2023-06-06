package com.example.myto_dolist

import android.widget.EditText
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDo_List")
data class Task(@ColumnInfo(name = "text")val text: String){
    @PrimaryKey(autoGenerate = true) var id = 0
}