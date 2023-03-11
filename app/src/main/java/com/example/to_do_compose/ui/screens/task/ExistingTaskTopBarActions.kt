package com.example.to_do_compose.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.R
import com.example.to_do_compose.domain.models.ToDoTask
import com.example.to_do_compose.ui.component.DisplayAlertDialog
import com.example.to_do_compose.utils.Action
import com.example.to_do_compose.utils.Priority

@Composable
fun ExistingTaskTopBar(
    toDoTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
){
    SmallTopAppBar(
        navigationIcon = { CloseAction(onCloseClicked = navigateToListScreen)},
        title = {
            Text(
                text = toDoTask.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            ExistingTaskTopBarActions(toDoTask = toDoTask, navigateToListScreen = navigateToListScreen)
        }
    )
}

@Composable
fun ExistingTaskTopBarActions(toDoTask: ToDoTask, navigateToListScreen: (Action) -> Unit){
    var openDialog by remember { mutableStateOf(false) }
    DisplayAlertDialog(
        openDialog = openDialog,
        title = stringResource(id = R.string.delete_task, toDoTask.title),
        description = stringResource(id = R.string.delete_task_confirmation, toDoTask.title),
        onCloseClicked = { openDialog = false},
        onYesClicked = {
            openDialog = false
            navigateToListScreen(Action.DELETE)
        }
    )
    DeleteAction(onDeleteClicked = { openDialog = true })
    UpdateAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit){
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.back_icon),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun DeleteAction(onDeleteClicked: (Action) -> Unit){
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_icon),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit){
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(R.string.update_icon),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview
fun ExistingTaskTopBarPreview(){
    ExistingTaskTopBar(
        toDoTask = ToDoTask(
            id = 0,
            title = "Test Task Title",
            description = "Test Task Description",
            priority = Priority.High
        ), navigateToListScreen = {}
    )
}