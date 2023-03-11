package com.example.to_do_compose.ui.screens.list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.to_do_compose.R
import com.example.to_do_compose.utils.Priority
import com.example.to_do_compose.domain.models.ToDoTask
import com.example.to_do_compose.ui.component.EmptyScreen
import com.example.to_do_compose.ui.theme.*
import com.example.to_do_compose.utils.Action
import com.example.to_do_compose.utils.Constants.HALF_ALPHA
import com.example.to_do_compose.utils.Constants.LIST_ITEM_DESCRIPTION_MAX_LINES
import com.example.to_do_compose.utils.Constants.LIST_ITEM_TITLE_MAX_LINES
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    tasks: List<ToDoTask>,
    deletedItem: Int,
    isFirstShow: Boolean,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    swipeToDelete: (Action, ToDoTask) -> Unit
){
    if (!isFirstShow){
        if (tasks.isEmpty())
            EmptyScreen()
        else
            DisplayList(
                tasks = tasks,
                deletedItem = deletedItem,
                swipeToDelete = swipeToDelete,
                navigateToTaskScreen = navigateToTaskScreen
            )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayList(
    tasks: List<ToDoTask>,
    deletedItem: Int,
    swipeToDelete: (Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
){
    LazyColumn(modifier = Modifier.padding(top = TOP_BAR_HEIGHT)) {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ){ task ->
            var dismissState = if (deletedItem != task.id) rememberDismissState() else null
            if (dismissState != null){
                val rotate = animateFloatAsState(
                    targetValue = if (dismissState.targetValue == DismissValue.Default) 0f else -45f)
                val dismissDirection = dismissState.dismissDirection
                var isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
                var itemAppeared by remember{ mutableStateOf(false) }
                if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                    val scope = rememberCoroutineScope()
                    scope.launch{
                        delay(300)
                        swipeToDelete(Action.DELETE, task)
                        dismissState = null
                    }
                }

                LaunchedEffect(key1 = true){
                    itemAppeared = true
                    isDismissed = dismissState?.isDismissed(DismissDirection.EndToStart)?: true
                }
                AnimatedVisibility(
                    visible = itemAppeared && !isDismissed,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
                ) {
                    SwipeToDismiss(
                        state = dismissState?: rememberDismissState(),
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { androidx.compose.material.FractionalThreshold(fraction = 0.3f) } ,
                        background = { DeleteBackground(rotate = rotate.value)},
                        dismissContent = { ListItem(
                            toDoTask = task,
                            navigateToTaskScreen = navigateToTaskScreen
                        )}
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(SMALL_PADDING))
        }
    }
}

@Composable
fun DeleteBackground(rotate: Float){
    Box(
        modifier = Modifier
            .padding(all = SMALL_PADDING)
            .fillMaxSize()
            .background(HighPriorityColor)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd
    ){
        Icon(
            modifier = Modifier.rotate(rotate),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SMALL_PADDING),
        shadowElevation = LIST_ITEM_ELEVATION,
        onClick = { navigateToTaskScreen(toDoTask.id) },
        color = MaterialTheme.colorScheme.surface,
        shape = RectangleShape
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(EXTRA_LARGE_PADDING)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = SMALL_PADDING)) {
                Text(
                    modifier = Modifier.weight(0.9f),
                    text = toDoTask.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = LIST_ITEM_TITLE_MAX_LINES,
                    overflow = TextOverflow.Ellipsis
                )
                Box(modifier = Modifier.weight(0.1f)){
                    Canvas(modifier = Modifier
                        .size(PRIORITY_INDICATOR_SIZE)
                        .align(Alignment.CenterEnd)){
                        drawCircle(color = toDoTask.priority.color)
                    }
                }
            }
            Text(
                modifier = Modifier.alpha(HALF_ALPHA),
                text = toDoTask.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = LIST_ITEM_DESCRIPTION_MAX_LINES,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun ListItemPreview(){
    ListItem(
        ToDoTask(
            title = "title",
            description = "This is description",
            priority = Priority.Medium
        )
    ){}
}