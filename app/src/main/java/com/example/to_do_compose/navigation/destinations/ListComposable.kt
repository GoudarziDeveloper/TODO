package com.example.to_do_compose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.example.to_do_compose.ui.screens.list.ListScreen
import com.example.to_do_compose.ui.viewmodels.SharedViewModel
import com.example.to_do_compose.utils.Action
import com.example.to_do_compose.utils.Constants.LIST_ARGUMENT
import com.example.to_do_compose.utils.Constants.LIST_SCREEN
import com.example.to_do_compose.utils.Constants.TASK_SCREEN
import com.example.to_do_compose.utils.SearchTopBarState
import com.example.to_do_compose.utils.toAction

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT){
            type = NavType.StringType
        }),
        enterTransition = {
            if (this.initialState.destination.route == TASK_SCREEN){
                slideInHorizontally(
                    initialOffsetX = { screenWidth -> screenWidth },
                    animationSpec = tween(500)
                )
            } else null
        }
    ){ navBackStackEntry ->
        navBackStackEntry.arguments?.getString(LIST_ARGUMENT).toAction().let { action ->
            var myAction by rememberSaveable {
                mutableStateOf(Action.NO_ACTION)
            }
            LaunchedEffect(key1 = myAction){
                if (
                    sharedViewModel.action.value != Action.DELETE_ALL &&
                    sharedViewModel.action.value != action &&
                    myAction != action
                ){
                    myAction = action
                    sharedViewModel.updateAction(action)
                }
            }
        }

        ListScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel = sharedViewModel)
        if (sharedViewModel.searchTopBarState == SearchTopBarState.OPENED)
            sharedViewModel.searchTasks()
    }
}