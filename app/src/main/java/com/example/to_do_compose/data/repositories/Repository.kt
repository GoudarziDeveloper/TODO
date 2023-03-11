package com.example.to_do_compose.data.repositories

import com.example.to_do_compose.domain.models.ToDoTask
import com.example.to_do_compose.domain.repositories.DataStoreRepository
import com.example.to_do_compose.domain.repositories.LocalDataSource
import com.example.to_do_compose.utils.Priority
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSourceImpl: LocalDataSource,
    private val dataStoreImpl: DataStoreRepository
    ) {
    val readSortState: Flow<String> = dataStoreImpl.readSortState

    val getAllTasks: Flow<List<ToDoTask>> = localDataSourceImpl.getAllTasks
    val sortByLowPriority: Flow<List<ToDoTask>> = localDataSourceImpl.sortByLowPriority
    val sortByHighPriority: Flow<List<ToDoTask>> = localDataSourceImpl.sortByHighPriority

    fun getSelectedTask(taskId: Int): Flow<ToDoTask?>{
        return localDataSourceImpl.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(task: ToDoTask): Long{
        return localDataSourceImpl.addTask(task = task)
    }

    suspend fun updateTask(task: ToDoTask): Int{
        return localDataSourceImpl.updateTask(task = task)
    }

    suspend fun deleteTask(task: ToDoTask): Int? {
        return localDataSourceImpl.deleteTask(task = task)
    }

    suspend fun deleteAllTasks(): Int? {
        return localDataSourceImpl.deleteAllTasks()
    }

    fun searchTask(taskQuery: String): Flow<List<ToDoTask>> {
        return localDataSourceImpl.searchTask(taskQuery = taskQuery)
    }

    suspend fun persistSortState(priority: Priority){
        dataStoreImpl.persistSortState(priority)
    }
}