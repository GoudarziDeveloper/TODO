package com.example.to_do_compose.ui.screens.task

import androidx.compose.runtime.Composable
import com.example.to_do_compose.domain.models.ToDoTask
import com.example.to_do_compose.utils.Action

@Composable
fun TaskTopBar(
    toDoTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
){
    if (toDoTask != null){
        if (toDoTask.id == -1)
            NewTaskTopBar(navigateToListScreen = navigateToListScreen)
        else
            ExistingTaskTopBar(toDoTask = toDoTask, navigateToListScreen = navigateToListScreen)
    } else
        NewTaskTopBar(navigateToListScreen = navigateToListScreen)
}