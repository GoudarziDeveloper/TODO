package com.example.to_do_compose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.ui.theme.SEARCH_TOP_BAR_OFFSET
import com.example.to_do_compose.utils.Constants.HALF_ALPHA

@Composable
fun SearchTopAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    SmallTopAppBar(
        navigationIcon = {
            IconButton(onClick = { onSearchClicked(text) }, enabled = text.isNotEmpty()) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
            }
        },
        title = {
            Surface(modifier = Modifier
                .offset(x = SEARCH_TOP_BAR_OFFSET)
                .fillMaxWidth()
                .fillMaxHeight()
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    value = text,
                    onValueChange = onTextChange,
                    placeholder = {
                        Text(modifier = Modifier.alpha(HALF_ALPHA),text = "Search...")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    if (text.isNotEmpty()) onTextChange("") else onCloseClicked()
                }
            ) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Close Icon")
            }
        }
    )
}

@Composable
@Preview
fun SearchTopBarPreview(){
    SearchTopAppBar(
        text = "",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}