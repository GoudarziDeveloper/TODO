package com.example.to_do_compose.domain.use_cases.read_sort_state

import com.example.to_do_compose.data.repositories.Repository
import kotlinx.coroutines.flow.Flow

class ReadSortState(private val repository: Repository) {
    operator fun invoke(): Flow<String>{
        return repository.readSortState
    }
}