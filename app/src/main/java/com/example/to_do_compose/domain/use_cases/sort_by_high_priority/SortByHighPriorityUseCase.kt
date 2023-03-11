package com.example.to_do_compose.domain.use_cases.sort_by_high_priority

import com.example.to_do_compose.data.repositories.Repository
import com.example.to_do_compose.domain.models.ToDoTask
import kotlinx.coroutines.flow.Flow

class SortByHighPriorityUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<List<ToDoTask>> {
        return repository.sortByHighPriority
    }
}