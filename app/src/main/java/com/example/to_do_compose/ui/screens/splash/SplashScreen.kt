package com.example.to_do_compose.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_do_compose.R
import com.example.to_do_compose.ui.theme.AppTheme
import com.example.to_do_compose.ui.theme.LOGO_SIZE
import com.example.to_do_compose.utils.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit
){
    var isStarted by remember { mutableStateOf(false) }
    val offsetState by animateDpAsState(
        targetValue = if (isStarted) 0.dp else 100.dp,
        animationSpec = tween(durationMillis = 1000)
    )
    val alphaState by animateFloatAsState(
        targetValue = if (isStarted) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )
    LaunchedEffect(key1 = true) {
        isStarted = true
        delay(SPLASH_SCREEN_DELAY)
        navigateToListScreen()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.size(LOGO_SIZE).offset(y = offsetState).alpha(alphaState),
            painter = painterResource(id = if (isSystemInDarkTheme())
                R.drawable.logo_dark else R.drawable.logo_light),
            contentDescription = stringResource(R.string.logo_icon),
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    SplashScreen{}
}

@Composable
@Preview
fun SplashScreenDarkPreview(){
    AppTheme(darkTheme = true) {
        SplashScreen{}
    }
}