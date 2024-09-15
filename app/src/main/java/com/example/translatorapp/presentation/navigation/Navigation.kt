package com.example.translatorapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.language.LanguageScreen
import com.example.translatorapp.presentation.main.MainScreen
import com.example.translatorapp.presentation.navigation.components.Screens
import com.example.translatorapp.presentation.premium.SubscriptionScreen
import com.example.translatorapp.presentation.splash.SplashScreen


@Composable
fun Navigation(
) {
    val controller = LocalNavController.current
    NavHost(navController = controller, startDestination = Screens.SplashScreen.name) {
        composable(Screens.SplashScreen.name) {
            SplashScreen()
        }
        composable(Screens.TranslatorScreen.name) {
            MainScreen()
        }
        composable(Screens.LanguageScreen.name + "/{isFrom}") {
            LanguageScreen()
        }
        composable(Screens.PremiumScreen.name) {
            SubscriptionScreen()
        }
    }
}