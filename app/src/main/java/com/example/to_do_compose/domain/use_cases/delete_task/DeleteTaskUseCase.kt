package com.example.to_do_compose.domain.use_cases.delete_task

import com.example.to_do_compose.data.repositories.Repository
import com.example.to_do_compose.domain.models.ToDoTask

class DeleteTaskUseCase(private val repository: Repository) {
    suspend operator fun invoke(task: ToDoTask): Int? {
        return repository.deleteTask(task)
    }
}