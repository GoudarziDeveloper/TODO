package com.example.to_do_compose.ui.screens.task

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.to_do_compose.R
import com.example.to_do_compose.ui.theme.TOP_BAR_HEIGHT
import com.example.to_do_compose.ui.viewmodels.SharedViewModel
import com.example.to_do_compose.utils.Action
import com.example.to_do_compose.utils.Priority

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
){
    val toDoTask by sharedViewModel.selectedTask
    Scaffold(
        topBar = {
            TaskTopBar(
                toDoTask = toDoTask,
                navigateToListScreen = { action ->
                    if (sharedViewModel.validateExeAction(action)) navigateToListScreen(action)
                }
            )
        },
        content = {
            TaskContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = TOP_BAR_HEIGHT),
                title = toDoTask?.title?: "",
                titleError = sharedViewModel.titleErrorManager(stringResource(id = R.string.title_cant_be_empty)),
                onTitleChange = { sharedViewModel.updateTaskFields(toDoTask = toDoTask?.copy(title = it)) },
                description = toDoTask?.description?: "",
                descriptionError = sharedViewModel.descriptionErrorManager(stringResource(id = R.string.description_cant_be_empty)),
                onDescriptionChange = { sharedViewModel.updateTaskFields(toDoTask = toDoTask?.copy(description = it)) },
                priority = toDoTask?.priority?: Priority.None,
                onPrioritySelected = { sharedViewModel.updateTaskFields(toDoTask = toDoTask?.copy(priority = it)) }
            )
        }
    )
}