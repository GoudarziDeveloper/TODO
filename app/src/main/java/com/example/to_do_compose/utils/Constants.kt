package com.example.to_do_compose.utils

object Constants {
    const val DATABASE_NAME = "todo_database"
    const val TODO_TABLE = "todo_table"

    const val SPLASH_NAME = "splash"
    const val LIST_NAME = "list"
    const val TASK_NAME = "task"

    const val LIST_ARGUMENT = "action"
    const val TASK_ARGUMENT = "taskId"

    const val SPLASH_SCREEN = "$SPLASH_NAME/"
    const val LIST_SCREEN = "$LIST_NAME/{$LIST_ARGUMENT}"
    const val TASK_SCREEN = "$TASK_NAME/{$TASK_ARGUMENT}"

    const val LIST_ITEM_DESCRIPTION_MAX_LINES = 3
    const val LIST_ITEM_TITLE_MAX_LINES = 3

    const val HALF_ALPHA = 0.5f

    const val MAX_TITLE_LENGTH = 20
    const val MAX_DESCRIPTION_LENGTH = 500

    const val PREFERENCE_NAME = "todo_preferences"
    const val PREFERENCE_SORT_KEY = "sort_state"

    const val SPLASH_SCREEN_DELAY = 2000L
}