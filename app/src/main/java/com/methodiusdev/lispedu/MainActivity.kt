package com.methodiusdev.lispedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.methodiusdev.lispedu.navigation.Screen
import com.methodiusdev.lispedu.ui.theme.LispEduTheme
import com.methodiusdev.lispedu.ui.screens.MainMenuScreen
import com.methodiusdev.lispedu.ui.screens.AboutScreen
import androidx.navigation.compose.composable
import com.methodiusdev.lispedu.ui.screens.ConfigScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LispEduTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.MainMenu.route
                ) {
                    composable(route = Screen.MainMenu.route) {
                        MainMenuScreen(
                            onNavigateToAbout = {
                                navController.navigate(Screen.About.route)
                            },
                            onNavigateToSettings = {
                                navController.navigate(Screen.Settings.route)
                            }
                        )
                    }

                    composable(route = Screen.About.route) {
                        AboutScreen(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(route = Screen.Settings.route) {
                        ConfigScreen(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}