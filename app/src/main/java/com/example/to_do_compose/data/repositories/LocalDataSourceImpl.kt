package com.example.to_do_compose.data.repositories

import com.example.to_do_compose.data.ToDoDao
import com.example.to_do_compose.domain.models.ToDoTask
import com.example.to_do_compose.domain.repositories.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val toDoDao: ToDoDao): LocalDataSource {
    override val getAllTasks: Flow<List<ToDoTask>> = toDoDao.getAllTasks()
    override val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    override val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    override fun getSelectedTask(taskId: Int): Flow<ToDoTask?> {
        return toDoDao.getSelectedTask(taskId = taskId)
    }

    override suspend fun addTask(task: ToDoTask): Long{
        return toDoDao.addTask(task = task)
    }

    override suspend fun updateTask(task: ToDoTask): Int{
        return toDoDao.updateTask(task = task)
    }

    override suspend fun deleteTask(task: ToDoTask): Int? {
        return toDoDao.deleteTask(task = task)
    }

    override suspend fun deleteAllTasks(): Int? {
        return toDoDao.deleteAllTasks()
    }

    override fun searchTask(taskQuery: String): Flow<List<ToDoTask>> {
        return toDoDao.searchTask(searchQuery = taskQuery)
    }
}