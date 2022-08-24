package com.lloyd.photos.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lloyd.photos.Destination
import com.lloyd.photos.presentation.photos.PhotoScreen
import com.lloyd.photos.presentation.photos.PhotosViewModel
import com.lloyd.photos.presentation.splash.SplashScreen
import com.lloyd.photos.ui.theme.PhotosappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val photosViewModel by viewModels<PhotosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotosappTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destination.Splash.route
                ) {
                    composable(route = Destination.Splash.route) {
                        SplashScreen(navController)
                    }
                    composable(route = Destination.Photos.route) {
                        PhotoScreen(photosViewModel)
                    }

                }
            }
        }
    }
}
