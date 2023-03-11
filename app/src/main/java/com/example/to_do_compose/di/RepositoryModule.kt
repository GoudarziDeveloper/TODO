package com.example.to_do_compose.di

import com.example.to_do_compose.data.repositories.Repository
import com.example.to_do_compose.domain.repositories.LocalDataSource
import com.example.to_do_compose.domain.use_cases.UseCases
import com.example.to_do_compose.domain.use_cases.add_task.AddTaskUseCase
import com.example.to_do_compose.domain.use_cases.delete_all_task.DeleteAllTaskUseCase
import com.example.to_do_compose.domain.use_cases.delete_task.DeleteTaskUseCase
import com.example.to_do_compose.domain.use_cases.get_all_tasks.GetAllTaskUseCase
import com.example.to_do_compose.domain.use_cases.get_selected_task.GetSelectedTaskUseCase
import com.example.to_do_compose.domain.use_cases.persist_sort_state.PersistSortState
import com.example.to_do_compose.domain.use_cases.read_sort_state.ReadSortState
import com.example.to_do_compose.domain.use_cases.search_tasks.SearchTaskUseCase
import com.example.to_do_compose.domain.use_cases.sort_by_high_priority.SortByHighPriorityUseCase
import com.example.to_do_compose.domain.use_cases.sort_by_low_priority.SortByLowPriorityUseCase
import com.example.to_do_compose.domain.use_cases.update_task.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            addTaskUseCase = AddTaskUseCase(repository),
            deleteAllTaskUseCase = DeleteAllTaskUseCase(repository),
            deleteTaskUseCase = DeleteTaskUseCase(repository),
            getAllTaskUseCase = GetAllTaskUseCase(repository),
            getSelectedTaskUseCase = GetSelectedTaskUseCase(repository),
            searchTaskUseCase = SearchTaskUseCase(repository),
            sortByHighPriorityUseCase = SortByHighPriorityUseCase(repository),
            sortByLowPriorityUseCase = SortByLowPriorityUseCase(repository),
            updateTaskUseCase = UpdateTaskUseCase(repository),
            persistSortState = PersistSortState(repository),
            readSortState = ReadSortState(repository)
        )
    }
}