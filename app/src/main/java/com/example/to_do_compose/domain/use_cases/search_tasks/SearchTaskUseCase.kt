package com.example.to_do_compose.domain.use_cases.search_tasks

import com.example.to_do_compose.data.repositories.Repository
import com.example.to_do_compose.domain.models.ToDoTask
import kotlinx.coroutines.flow.Flow

class SearchTaskUseCase(private val repository: Repository) {
    operator fun invoke(searchQuery: String): Flow<List<ToDoTask>> {
        return repository.searchTask(searchQuery)
    }
}