package com.example.to_do_compose.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.example.to_do_compose.ui.viewmodels.SharedViewModel
import com.example.to_do_compose.utils.Action
import kotlinx.coroutines.launch
import com.example.to_do_compose.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
){
    val sortState by sharedViewModel.sortState.collectAsState()
    LaunchedEffect(key1 = sortState){
        sharedViewModel.getAllTasks(sortState)
    }
    val allTasks by sharedViewModel.allTasks.collectAsState()

    val snackBarHostState = remember{ SnackbarHostState() }

    DisplayActionsSnackBar(
        snackBarHostState = snackBarHostState,
        taskTitle = sharedViewModel.selectedTask.value?.title?:"",
        action = sharedViewModel.action.value,
        handleDataBaseActions = { sharedViewModel.handleDatabaseActions() },
        undoClicked = {
            sharedViewModel.updateAction(it)
        },
        sharedViewModel.lastActionResult.value,
        sharedViewModel.deletedItem.value
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            ListTopBar(
                sharedViewModel = sharedViewModel,
                taskListNotEmpty = allTasks.isNotEmpty()
            )
        },
        content = {
            ListContent(
                tasks = allTasks,
                deletedItem = sharedViewModel.deletedItem.value,
                isFirstShow = sharedViewModel.isFirstShow,
                navigateToTaskScreen = navigateToTaskScreen
            ) { action, task ->
                if (sharedViewModel.action.value != Action.DELETE) {
                    sharedViewModel.updateTaskFields(task)
                    sharedViewModel.updateAction(action)
                    snackBarHostState.currentSnackbarData?.dismiss()
                }
            }
        },
        floatingActionButton = { ListFab(onFabClicked = navigateToTaskScreen) }
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DisplayActionsSnackBar(
    snackBarHostState: SnackbarHostState,
    taskTitle: String,
    action: Action,
    handleDataBaseActions: () -> Unit,
    undoClicked: (Action) -> Unit,
    lastActionResult: Result,
    deletedItem: Int
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action){
        if (action != Action.NO_ACTION) {
            if (!(action != Action.DELETE_ALL && taskTitle == "")) {
                scope.launch {
                    handleDataBaseActions()
                    if (deletedItem != -1)
                        handleDataBaseActions()
                    val result = snackBarHostState.showSnackbar(
                        message = messageManager(action = action, taskTitle = taskTitle, lastActionResult = lastActionResult),
                        actionLabel = showSnackBarLabel(action)
                    )
                    undoDeleteTask(action = action, snackBarResult =  result, undoClicked = undoClicked)
                    if (action == Action.DELETE)
                        handleDataBaseActions()
                    else
                        undoClicked(Action.NO_ACTION)
                }
            }
        }
    }
}

private fun messageManager(action: Action, taskTitle: String, lastActionResult: Result): String{
    return when(action) {
        Action.ADD -> if (lastActionResult == Result.SUCCESS) "$taskTitle successfully added" else "To add $taskTitle an error occurred"
        Action.UPDATE -> if (lastActionResult == Result.SUCCESS) "$taskTitle successfully updated" else "To update $taskTitle an error occurred"
        Action.DELETE -> if (lastActionResult == Result.SUCCESS) "$taskTitle successfully removed" else "To delete $taskTitle an error occurred"
        Action.DELETE_ALL ->if (lastActionResult == Result.SUCCESS) "All tasks successfully removed" else "To removed all tasks an error occurred"
        Action.UNDO -> if (lastActionResult == Result.SUCCESS) "Successful to undo" else "To undo an error occurred"
        Action.NO_ACTION -> ""
    }
}

private fun showSnackBarLabel(action: Action): String{
    return if (action == Action.DELETE) "Undo"
    else "Ok"
}

private fun undoDeleteTask(action:Action, snackBarResult: SnackbarResult, undoClicked: (Action) -> Unit){
    if (action == Action.DELETE && snackBarResult == SnackbarResult.ActionPerformed){
        undoClicked(Action.UNDO)
    }
}

/*
@Composable
@Preview
fun ListScreenPreview(){
    ListScreen(navigateToTaskScreen = {})
}*/
