package com.example.translatorapp.presentation.translate

import android.app.Activity
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.translatorapp.core.ads.MyAdsKeys
import com.example.translatorapp.core.ads.NativeAd
import com.example.translatorapp.core.constants.LocalData
import com.example.translatorapp.core.constants.LocalData.speak
import com.example.translatorapp.data.data_source.model.getLanguageNameByShortCode
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.components.Swap
import com.example.translatorapp.core.constants.LocalData.startSpeechRecognition
import com.example.translatorapp.presentation.navigation.components.Screens
import com.example.translatorapp.presentation.translate.components.RequestCard
import com.example.translatorapp.presentation.translate.components.TranslationResult
import com.example.translatorapp.ui.theme.MainColor
import ir.kaaveh.sdpcompose.sdp
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun TranslatorScreen() {
    val viewModel: TranslatorViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value
    val navController = LocalNavController.current
    val lifecycle = LocalLifecycleOwner.current
    val context = LocalContext.current
    val activity = context as Activity
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    LaunchedEffect(context) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.getDefault()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            tts?.stop()
            tts?.shutdown()
        }
    }

    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val resultCode = it.data
                val resultArray =
                    resultCode?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                viewModel.onSpeechToText(resultArray?.get(0).toString())
            }
        }

    LaunchedEffect(key1 = lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.updateLanguages()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEF7FE))
            .padding(16.dp)
    ) {
        Swap(modifier = Modifier.align(Alignment.CenterHorizontally),
            fromLanguage = state.fromLanguage.getLanguageNameByShortCode(),
            fromLanguageClick = {
                navController.navigate(Screens.LanguageScreen.name + "/true")
            },
            toLanguage = state.toLanguage.getLanguageNameByShortCode(),
            toLanguageClick = {
                navController.navigate(Screens.LanguageScreen.name + "/false")
            },
            onSwapClick = {
                viewModel.onSwapClick()
            })
        NativeAd(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            activity = activity,
            adKey = MyAdsKeys.TestNative.name
        )

        RequestCard(
            requestContent = state.requestText,
            onRequestChange = {
                viewModel.onTranslatorTextFieldChange(it)
            },
            onListenClick = {
                speak(tts, state.requestText)
            },
            onMicClick = {
                startSpeechRecognition(startForResult)
            },
            onTranslateClick = {
                if (state.requestText.isBlank()) {
                    Toast.makeText(context, "Enter any Word", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.getTranslatorText()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TranslationResult(
            onListenClick = {
                speak(tts, state.responseText)
            },
            toLanguage = state.toLanguage.getLanguageNameByShortCode(),
            responseText = state.responseText,
            onShareClick = {
                LocalData.shareText(context, state.responseText)
            },
            onCopyClick = {
                LocalData.copyToClipboard(context, state.responseText)
            }
        )
    }
}
