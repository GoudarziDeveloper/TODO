package com.example.to_do_compose.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.to_do_compose.R

@Composable
fun DisplayAlertDialog(
    openDialog: Boolean,
    title: String,
    description: String,
    onCloseClicked: () -> Unit,
    onYesClicked: () -> Unit
){
    if (openDialog) {
        AlertDialog(
            onDismissRequest = onCloseClicked,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            confirmButton = {
                Button(onClick = onYesClicked) {
                    Text(text = stringResource(R.string.yes))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = onCloseClicked) {
                    Text(text = stringResource(R.string.no))
                }
            }
        )
    }
}