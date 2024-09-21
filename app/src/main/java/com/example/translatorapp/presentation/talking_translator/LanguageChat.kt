package com.example.translatorapp.presentation.talking_translator

import android.app.Activity
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.translatorapp.core.constants.LocalData.speak
import com.example.translatorapp.core.constants.LocalData.startSpeechRecognition
import com.example.translatorapp.data.data_source.model.getLanguageNameByShortCode
import com.example.translatorapp.data.response.NetworkResponse
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.components.Swap
import com.example.translatorapp.presentation.navigation.components.Screens
import com.example.translatorapp.presentation.talking_translator.components.TranslationCard
import com.example.translatorapp.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun LanguageChat() {
    val viewModel: LanguageChatViewModel = koinViewModel()
    val messages by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    val lifecycle = LocalLifecycleOwner.current
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    // Lifecycle Effect
    LaunchedEffect(key1 = lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.updateLanguages()
        }
    }

    // TextToSpeech Initialization
    LaunchedEffect(context) {
        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.getDefault()
            }
        })
    }
    DisposableEffect(Unit) {
        onDispose {
            tts?.stop()
            tts?.shutdown()
        }
    }
    val startForResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val speechText =
                result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0) ?: ""
            if (speechText.isNotBlank()) {
                viewModel.onMicButtonClick(spokenText = speechText)
            }
        }
    }

    Scaffold(floatingActionButton = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, bottom = 80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Left Mic Button
            FloatingActionButton(
                onClick = {
                    viewModel.onSideChange(true)
                    startSpeechRecognition(startForResult)
                }, shape = CircleShape, containerColor = Color(0xFF4FAAFF)
            ) {
                Icon(imageVector = Icons.Default.Mic, contentDescription = "Left side Mic")
            }
            FloatingActionButton(
                onClick = {
                    viewModel.onSideChange(false)
                    startSpeechRecognition(startForResult)
                }, shape = CircleShape, containerColor = Color(0xFF388E3C)
            ) {
                Icon(imageVector = Icons.Default.Mic, contentDescription = "Right side Mic")
            }
        }
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MainColor)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(messages.messages) { messageData ->
                    TranslationCard(
                        originalText = messageData.message,
                        translatedText = messageData.translation,
                        isLeft = messageData.isLeft,
                        onClickSpeaker = {
                            speak(tts, messageData.translation)
                        },
                        modifier = Modifier
                            .align(if (messageData.isLeft) Alignment.BottomStart else Alignment.BottomEnd)
                            .padding(
                                end = if (messageData.isLeft) 16.dp else 0.dp,
                                start = if (messageData.isLeft) 0.dp else 16.dp
                            )
                    )
                }
                if (messages.loading) {
                    item {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
            Swap(modifier = Modifier.align(Alignment.BottomCenter),
                fromLanguage = messages.fromLanguage.getLanguageNameByShortCode(),
                fromLanguageClick = {
                    navController.navigate(Screens.LanguageScreen.name + "/true")
                },
                toLanguage = messages.toLanguage.getLanguageNameByShortCode(),
                toLanguageClick = {
                    navController.navigate(Screens.LanguageScreen.name + "/false")
                },
                onSwapClick = {
                    viewModel.onSwapClick()
                })
        }
    }
}