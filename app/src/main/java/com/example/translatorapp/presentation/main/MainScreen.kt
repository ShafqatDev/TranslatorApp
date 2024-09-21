package com.example.translatorapp.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.translatorapp.R
import com.example.translatorapp.core.constants.LocalData
import com.example.translatorapp.presentation.camera.components.CameraScreenWithPermission
import com.example.translatorapp.presentation.components.DrawerContent
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.components.RateUsDialogUI
import com.example.translatorapp.presentation.favourite.FavouriteScreen
import com.example.translatorapp.presentation.history.HistoryScreen
import com.example.translatorapp.presentation.main.components.TranslatorTabRow
import com.example.translatorapp.presentation.navigation.components.Screens
import com.example.translatorapp.presentation.talking_translator.LanguageChat
import com.example.translatorapp.presentation.translate.TranslatorScreen
import com.example.translatorapp.ui.theme.BlueColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = LocalNavController.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val showRateDialog = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState { 5 }

    if (showRateDialog.value) {
        RateUsDialogUI(onDismiss = {
            showRateDialog.value = false
        }, onRateUsClicked = {
            showRateDialog.value = false
        })
    }

    val context = LocalContext.current

    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        DrawerContent(onShareClick = {
            scope.launch {
                LocalData.shareApp(context)
                drawerState.close()
            }
        }, onPrivacyPolicyClick = {}, onRateClick = {
            scope.launch {
                showRateDialog.value = !showRateDialog.value
                drawerState.close()
            }
        }, onFeedbackClick = {})
    }, content = {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        title = { Text(text = "Language Translator", color = Color.White) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White
                                )
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = { navController.navigate(Screens.PremiumScreen.name) },
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(end = 10.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_premium),
                                    contentDescription = ""
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(BlueColor)
                    )
                    TranslatorTabRow(selectedTab = pagerState.currentPage) {
                        scope.launch { pagerState.animateScrollToPage(it) }
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
                        0 -> TranslatorScreen()
                        1 -> LanguageChat()
                        2 -> CameraScreenWithPermission()
                        3 -> HistoryScreen()
                        4 -> FavouriteScreen()
                    }
                }
            }
        }
    })
}