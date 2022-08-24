package com.lloyd.photos.presentation.splash

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lloyd.photos.Destination
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var animVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        animVisible = true
        delay(3000L)
        animVisible = false
        delay(500)
        navController.navigate(Destination.Photos.route) {
            popUpTo(Destination.Splash.route) {
                inclusive = true
            }
        }
    }

    AnimatedVisibility(
        visible = animVisible,
        enter = slideInHorizontally() + fadeIn(),
        exit = slideOutHorizontally() + fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Photos API",
                color = Color.Red,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp
            )
        }
    }
}