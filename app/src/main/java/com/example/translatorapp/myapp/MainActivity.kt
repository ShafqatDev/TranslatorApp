package com.example.translatorapp.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.navigation.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            CompositionLocalProvider(LocalNavController provides navController) {
                Navigation()
            }
        }
    }
}