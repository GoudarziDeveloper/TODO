package com.example.to_do_compose.domain.repositories

import com.example.to_do_compose.domain.models.ToDoTask
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    val getAllTasks: Flow<List<ToDoTask>>
    val sortByLowPriority: Flow<List<ToDoTask>>
    val sortByHighPriority: Flow<List<ToDoTask>>

    fun getSelectedTask(taskId: Int): Flow<ToDoTask?>

    suspend fun addTask(task: ToDoTask): Long

    suspend fun updateTask(task: ToDoTask): Int

    suspend fun deleteTask(task: ToDoTask): Int?

    suspend fun deleteAllTasks(): Int?

    fun searchTask(taskQuery: String): Flow<List<ToDoTask>>
}