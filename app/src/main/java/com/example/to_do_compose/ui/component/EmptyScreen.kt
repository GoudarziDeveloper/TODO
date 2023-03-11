package com.example.to_do_compose.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.to_do_compose.R
import com.example.to_do_compose.ui.theme.EMPTY_SCREEN_ICON_SIZE
import com.example.to_do_compose.ui.theme.SMALL_PADDING
import com.example.to_do_compose.utils.Constants

@Composable
fun EmptyScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(EMPTY_SCREEN_ICON_SIZE).padding(bottom = SMALL_PADDING),
            painter = painterResource(id = R.drawable.ic_sad),
            contentDescription = stringResource(R.string.sad_icon),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = Constants.HALF_ALPHA)
        )
        Text(
            text = "No Tasks Founded",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = Constants.HALF_ALPHA),
            style = MaterialTheme.typography.titleLarge
        )
    }
}