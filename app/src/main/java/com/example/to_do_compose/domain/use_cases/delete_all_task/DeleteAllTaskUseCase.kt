package com.example.to_do_compose.domain.use_cases.delete_all_task

import com.example.to_do_compose.data.repositories.Repository

class DeleteAllTaskUseCase(private val repository: Repository) {
    suspend operator fun invoke(): Int? {
        return repository.deleteAllTasks()
    }
}