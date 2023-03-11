package com.example.to_do_compose.ui.screens.list

import androidx.compose.runtime.*
import com.example.to_do_compose.domain.models.ToDoTask
import com.example.to_do_compose.ui.viewmodels.SharedViewModel
import com.example.to_do_compose.utils.Action
import com.example.to_do_compose.utils.SearchTopBarState

@Composable
fun ListTopBar(
    sharedViewModel: SharedViewModel,
    taskListNotEmpty: Boolean
){
    when(sharedViewModel.searchTopBarState){
        SearchTopBarState.CLOSED -> {
            DefaultListTopBar(
                onSearchClicked = {
                    sharedViewModel.updateTopBarState( SearchTopBarState.OPENED)
                    sharedViewModel.searchTasks() },
                onSortClicked = { sharedViewModel.persistSortState(it) },
                onDeleteAllClicked = {
                    sharedViewModel.updateAction(Action.DELETE_ALL)
                    sharedViewModel.emptySelectedTask()
                },
                taskListNotEmpty = taskListNotEmpty

            )
        } else -> {
            SearchTopAppBar(
                text = sharedViewModel.searchTextState,
                onTextChange =  sharedViewModel::updateSearchText ,
                onCloseClicked = {
                    sharedViewModel.updateTopBarState(SearchTopBarState.CLOSED)
                    sharedViewModel.updateSearchText("")
                },
                onSearchClicked = { sharedViewModel.searchTasks() }
            )
        }
    }
}

/*
@Composable
@Preview
fun ListTopBarPreview(){
    ListTopBar()
}*/
