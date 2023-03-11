package com.example.to_do_compose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.to_do_compose.R
import com.example.to_do_compose.ui.component.DisplayAlertDialog
import com.example.to_do_compose.utils.Priority
import com.example.to_do_compose.ui.component.PriorityItem

@Composable
fun DefaultListTopBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit,
    taskListNotEmpty: Boolean
){
    SmallTopAppBar(
        title = {
            Text(text = "Tasks")
        },
        actions = {
            TobBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteAllClicked = onDeleteAllClicked,
                taskListNotEmpty = taskListNotEmpty
            )
        }
    )
}

@Composable
fun TobBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit,
    taskListNotEmpty: Boolean
){
    if (taskListNotEmpty){
        var openDialog by remember { mutableStateOf(false) }
        DisplayAlertDialog(
            openDialog = openDialog,
            title = stringResource(id = R.string.delete_all_tasks),
            description = stringResource(id = R.string.delete_all_tasks_confirmation),
            onCloseClicked = { openDialog = false },
            onYesClicked = {
                openDialog = false
                onDeleteAllClicked()
            }
        )
        SearchAction(onSearchClicked = onSearchClicked)
        SortAction(onSortClicked = onSortClicked)
        MoreAction(onDeleteAllClicked = { openDialog = true})
    }
}


@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick =  onSearchClicked) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_icon)
        )
    }
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit){
    var expended by remember { mutableStateOf(false) }

    IconButton(onClick = { expended = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_action_sort),
            contentDescription = stringResource(R.string.sort_icon)
        )
        DropdownMenu(expanded = expended, onDismissRequest = { expended = false }) {
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onSortClicked(Priority.Low)
                },
                text = {
                    PriorityItem(priority = Priority.Low)
                }
            )
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onSortClicked(Priority.High)
                },
                text = {
                    PriorityItem(priority = Priority.High)
                }
            )
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onSortClicked(Priority.None)
                },
                text = {
                    PriorityItem(priority = Priority.None)
                }
            )
        }
    }
}

@Composable
fun MoreAction(onDeleteAllClicked: () -> Unit){
    var expended by remember { mutableStateOf(false) }

    IconButton(onClick = { expended = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_more),
            contentDescription = stringResource(R.string.vertical_more_icon)
        )
        DropdownMenu(expanded = expended, onDismissRequest = { expended = false }) {
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onDeleteAllClicked()
                },
                text = {
                    DeleteAllAction()
                }
            )
        }
    }
}

@Composable
fun DeleteAllAction(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.delete_all),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center
    )
}