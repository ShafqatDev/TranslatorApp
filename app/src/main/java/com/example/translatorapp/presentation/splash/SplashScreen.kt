package com.example.translatorapp.presentation.splash

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.translatorapp.R
import com.example.translatorapp.core.ads.MyInterViewModel
import com.example.translatorapp.core.ads.showLoadingBgDialog
import com.example.translatorapp.core.ads.showLoadingDialog
import com.example.translatorapp.data.controller.SharedPreferences
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.navigation.components.Screens
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen() {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val appPreferences = SharedPreferences(context)
    val isFirstTimeLaunch = remember { appPreferences.isFirstTimeLaunch() }
    val viewModel: MyInterViewModel = koinViewModel()
    val showDialog = showLoadingDialog.collectAsState()
    val blackBgDialog = showLoadingBgDialog.collectAsState()
    if (showDialog.value) {
        Dialog(
            onDismissRequest = {}, properties = DialogProperties(
                dismissOnBackPress = false, dismissOnClickOutside = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Ad is loading")
                }
            }
        }
    }
    if (blackBgDialog.value) {
        Dialog(
            onDismissRequest = {}, properties = DialogProperties(
                dismissOnBackPress = false, dismissOnClickOutside = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Ad is loading")
                }
            }
        }
    }

    val activity = context as Activity
    LaunchedEffect(Unit) {
        if (!isFirstTimeLaunch) {
            delay(3000)
            viewModel.showInterstitialAd(activity = activity, instantAd = true, onAdDismissed = {
                navController.navigate(Screens.MainScreen.name) {
                    popUpTo(Screens.SplashScreen.name) { inclusive = true }
                }
            })
        }
    }

    var isChecked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD), Color(0xFFFFFFFF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_splash),
                    contentDescription = "Translator Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Language Translator",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )

                if (isFirstTimeLaunch) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Translate your text in any language",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                }
            }
            if (isFirstTimeLaunch) {
                Column(
                    modifier = Modifier.padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFFFFA000)
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "I accept the ", style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Privacy policy",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Blue, textDecoration = TextDecoration.Underline
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (isChecked) {
                                appPreferences.setFirstTimeLaunch(false)
                                viewModel.showInterstitialAd(activity = activity,
                                    instantAd = true,
                                    onAdDismissed = {
                                        navController.navigate(Screens.MainScreen.name) {
                                            popUpTo(Screens.SplashScreen.name) { inclusive = true }
                                        }
                                    })
                            } else {
                                Toast.makeText(
                                    navController.context,
                                    "Please accept the privacy policy",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .width(200.dp)
                            .wrapContentHeight()
                            .padding(vertical = 12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA000), contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text(
                            text = "Continue",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        )
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(40.dp))
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .height(9.dp)
                        .fillMaxWidth(0.65f)
                        .clip(CircleShape), color = Color(0xFFFFA000)
                )
            }
        }
    }
}
