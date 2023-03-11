package com.example.to_do_compose.domain.use_cases.persist_sort_state

import com.example.to_do_compose.data.repositories.Repository
import com.example.to_do_compose.utils.Priority

class PersistSortState(private val repository: Repository) {
    suspend operator fun invoke(priority: Priority){
        repository.persistSortState(priority = priority)
    }
}