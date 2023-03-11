package com.example.to_do_compose.domain.use_cases.add_task

import com.example.to_do_compose.data.repositories.Repository
import com.example.to_do_compose.domain.models.ToDoTask

class AddTaskUseCase(private val repository: Repository) {
    suspend operator fun invoke(task: ToDoTask): Long{
        return repository.addTask(task)
    }
}