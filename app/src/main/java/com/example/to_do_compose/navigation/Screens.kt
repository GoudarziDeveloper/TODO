package com.example.to_do_compose.navigation

import androidx.navigation.NavHostController
import com.example.to_do_compose.utils.Action
import com.example.to_do_compose.utils.Constants.LIST_NAME
import com.example.to_do_compose.utils.Constants.LIST_SCREEN
import com.example.to_do_compose.utils.Constants.SPLASH_NAME
import com.example.to_do_compose.utils.Constants.SPLASH_SCREEN
import com.example.to_do_compose.utils.Constants.TASK_NAME
import com.example.to_do_compose.utils.Constants.TASK_SCREEN

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(route = "$LIST_NAME/${Action.NO_ACTION}") {
            popUpTo(SPLASH_SCREEN){ inclusive = true }
        }
    }

    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "$LIST_NAME/${action.name}") {
            popUpTo(LIST_SCREEN){ inclusive = true }
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "$TASK_NAME/$taskId") {
            popUpTo(TASK_SCREEN)
        }
    }
}
