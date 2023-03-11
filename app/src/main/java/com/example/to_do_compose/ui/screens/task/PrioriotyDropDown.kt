package com.example.to_do_compose.ui.screens.task

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import com.example.to_do_compose.R
import com.example.to_do_compose.ui.component.PriorityItem
import com.example.to_do_compose.ui.theme.*
import com.example.to_do_compose.utils.Constants.HALF_ALPHA
import com.example.to_do_compose.utils.Priority

@Composable
fun PriorityDropDown(
    modifier: Modifier = Modifier,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
){
    var expended by remember {
        mutableStateOf(false)
    }

    val rotateAnim by animateFloatAsState(targetValue = if (expended) 180f else 0f)

    var parentSize by remember{ mutableStateOf(IntSize.Zero) }

    Box(modifier = modifier){
        Row(
            modifier = Modifier
                .clickable { expended = true }
                .onGloballyPositioned { parentSize = it.size }
                .border(
                    width = PRIORITY_DROP_DOWN_BORDER_WIDTH,
                    color = BorderColor,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .height(PRIORITY_DROP_DOWN_HEIGHT),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier.size(PRIORITY_INDICATOR_SIZE).weight(0.1f)
            ){
                drawCircle(color = priority.color)
            }
            Text(
                modifier = Modifier.weight(0.8f),
                text = priority.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconButton(modifier = Modifier.weight(0.1f),onClick = { expended = true }) {
                Icon(
                    modifier = Modifier
                        .rotate(rotateAnim)
                        .alpha(HALF_ALPHA),
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(R.string.drop_down_icon)
                )
            }
            DropdownMenu(
                modifier = Modifier.width( with(LocalDensity.current){ parentSize.width.toDp() }),
                expanded = expended,
                onDismissRequest = { expended = false }
            ) {
                Priority.values().forEach {priority ->
                    DropdownMenuItem(
                        text = { PriorityItem(priority = priority)},
                        onClick = {
                            onPrioritySelected(priority)
                            expended = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PriorityDropDownPreview(){
    PriorityDropDown(priority = Priority.Medium, onPrioritySelected = {})
}