package com.example.to_do_compose.domain.repositories

import com.example.to_do_compose.utils.Priority
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun persistSortState(priority: Priority)

    val readSortState: Flow<String>
}