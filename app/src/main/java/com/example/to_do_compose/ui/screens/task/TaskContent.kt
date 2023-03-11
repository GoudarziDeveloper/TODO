package com.example.to_do_compose.ui.screens.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.R
import com.example.to_do_compose.ui.theme.EXTRA_LARGE_PADDING
import com.example.to_do_compose.ui.theme.LARGE_PADDING
import com.example.to_do_compose.ui.theme.MEDIUM_PADDING
import com.example.to_do_compose.ui.theme.NO_PADDING
import com.example.to_do_compose.utils.Priority

@Composable
fun TaskContent(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChange: (String) -> Unit,
    titleError: String?,
    description: String,
    onDescriptionChange: (String) -> Unit,
    descriptionError: String?,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
){
    Column(
        modifier = modifier
            .padding(all = LARGE_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = onTitleChange,
            label = { Text(text = stringResource(R.string.title)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            isError = titleError != null
        )
        if (titleError != null){
            Text(
                text = titleError,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        PriorityDropDown(modifier = Modifier.fillMaxWidth(),priority = priority, onPrioritySelected = onPrioritySelected)
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = if (descriptionError?.isNotEmpty() == true) 0.97f else 1f),
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text(text = stringResource(R.string.description))},
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = descriptionError != null
        )
        if (descriptionError != null){
            Text(
                text = descriptionError,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TaskContentPreview(){
    TaskContent(
        title = "",
        onTitleChange = {},
        titleError = "Field Cant Be Empty",
        description = "",
        descriptionError = "Field Cant Be Empty",
        onDescriptionChange = {},
        priority = Priority.None,
        onPrioritySelected = {}
    )
}