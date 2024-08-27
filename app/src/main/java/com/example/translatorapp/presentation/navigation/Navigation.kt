package com.example.translatorapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.navigation.components.Screens
import com.example.translatorapp.presentation.language_screen.LanguageScreen
import com.example.translatorapp.presentation.translator_screen.TranslatorScreen


@Composable
fun Navigation(
) {
    val controller = LocalNavController.current
    NavHost(navController = controller, startDestination = Screens.TranslatorScreen.name) {
        composable(Screens.TranslatorScreen.name) {
            TranslatorScreen()
        }
        composable(Screens.LanguageScreen.name + "/{isFrom}") {
            LanguageScreen()
        }
    }
}