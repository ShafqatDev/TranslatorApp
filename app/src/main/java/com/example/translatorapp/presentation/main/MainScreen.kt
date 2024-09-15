package com.example.translatorapp.presentation.main

import CameraScreen
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.translatorapp.R
import com.example.translatorapp.presentation.components.DrawerContent
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.components.Screen
import com.example.translatorapp.presentation.history.HistoryScreen
import com.example.translatorapp.presentation.main.components.TranslatorTabRow
import com.example.translatorapp.presentation.navigation.components.Screens
import com.example.translatorapp.presentation.talking_translator.LanguageChat
import com.example.translatorapp.presentation.talking_translator.MessageBubble
import com.example.translatorapp.presentation.translate.TranslatorScreen
import com.example.translatorapp.ui.theme.MainColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = LocalNavController.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        5
    }

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        DrawerContent()
    }, content = {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(title = {
                        Text(text = "Language Translator", color = Color.White)
                    }, navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    }, actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(Screens.PremiumScreen.name)
                            }, modifier = Modifier
                                .size(40.dp)
                                .padding(end = 10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_premium),
                                contentDescription = ""
                            )
                        }
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        MainColor
                    )
                    )
                    TranslatorTabRow(selectedTab = pagerState.currentPage) {
                        scope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    }
                }
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White.copy(alpha = 0.9f))
            ) {
                HorizontalPager(state = pagerState) {
                    when (it) {
                        0 -> {
                            TranslatorScreen()
                        }

                        1 -> {
                            LanguageChat()
                        }

                        2 -> {
                            CameraScreenWithPermission()
                        }

                        3 -> {
                            HistoryScreen()
                        }

                        4 -> {
                            Screen(text = "Star")
                        }
                    }
                }
            }
        }
    })
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreenWithPermission() {
    val cameraPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.CAMERA)
    if (cameraPermissionState.status.isGranted) {
        CameraScreen()
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text(text = "Grant Permission")
            }
        }
    }
}


@Composable
fun HistoryCard(message: String, translation: String, isLeft: Boolean= true) {
    MessageBubble(
        message = message, translation = translation, isLeft = isLeft
    )
}

