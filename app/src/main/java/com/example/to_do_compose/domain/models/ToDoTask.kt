package com.example.to_do_compose.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.to_do_compose.utils.Constants.TODO_TABLE
import com.example.to_do_compose.utils.Priority

@Entity(tableName = TODO_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)