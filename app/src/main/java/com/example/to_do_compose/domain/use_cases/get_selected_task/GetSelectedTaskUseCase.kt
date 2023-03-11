package com.example.to_do_compose.domain.use_cases.get_selected_task

import com.example.to_do_compose.data.repositories.Repository
import com.example.to_do_compose.domain.models.ToDoTask
import kotlinx.coroutines.flow.Flow

class GetSelectedTaskUseCase(private val repository: Repository) {
    operator fun invoke(taskId: Int): Flow<ToDoTask?>{
        return repository.getSelectedTask(taskId)
    }
}