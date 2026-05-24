package com.methodiusdev.lispedu.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object MainMenu : Screen("main_menu_screen")
    object Lesson : Screen("lesson_screen")
    object About : Screen("about_screen")
    object Settings : Screen("settings_screen")
}