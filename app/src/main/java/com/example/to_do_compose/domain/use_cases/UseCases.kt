package com.example.to_do_compose.domain.use_cases

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

class UseCases(
    val addTaskUseCase: AddTaskUseCase,
    val deleteAllTaskUseCase: DeleteAllTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val getAllTaskUseCase: GetAllTaskUseCase,
    val getSelectedTaskUseCase: GetSelectedTaskUseCase,
    val searchTaskUseCase: SearchTaskUseCase,
    val sortByHighPriorityUseCase: SortByHighPriorityUseCase,
    val sortByLowPriorityUseCase: SortByLowPriorityUseCase,
    val updateTaskUseCase: UpdateTaskUseCase,
    val persistSortState: PersistSortState,
    val readSortState: ReadSortState
)