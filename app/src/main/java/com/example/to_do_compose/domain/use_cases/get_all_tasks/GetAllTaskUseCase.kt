package com.example.to_do_compose.domain.use_cases.get_all_tasks

import com.example.to_do_compose.data.repositories.Repository
import com.example.to_do_compose.domain.models.ToDoTask
import kotlinx.coroutines.flow.Flow

class GetAllTaskUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<List<ToDoTask>> {
        return repository.getAllTasks
    }
}