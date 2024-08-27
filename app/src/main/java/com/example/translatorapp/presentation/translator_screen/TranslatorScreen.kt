package com.example.translatorapp.presentation.translator_screen

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.translatorapp.core.constants.LocalData
import com.example.translatorapp.data.data_source.model.getLanguageNameByShortCode
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.navigation.components.Screens
import com.example.translatorapp.utils.RateUsDialogUI
import com.example.translatorapp.utils.ResultTranslationCard
import com.example.translatorapp.utils.Swap
import com.example.translatorapp.utils.TranslatorTopBar
import com.example.translatorapp.utils.TranslatorCard
import ir.kaaveh.sdpcompose.sdp
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun TranslatorScreen(
    viewModel: TranslatorViewModel = koinViewModel()
) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val state = viewModel.state.collectAsState().value
    val lifecycle = LocalLifecycleOwner.current
    val startForResult =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val resultCode = it.data
                val resultArray =
                    resultCode?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                viewModel.onSpeechToText(resultArray?.get(0).toString())
            }
        }
    RateUsDialogUI(isRateUsDialogOpen = state.isRateUsDialogOpen, onDismiss = {
        viewModel.hideShowRateUsDialog()
    }, onRateUsClicked = {
        viewModel.hideShowRateUsDialog()
    })

    LaunchedEffect(key1 = lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.updateLanguages()
        }
    }

    Scaffold(topBar = {
        TranslatorTopBar(modifier = Modifier.fillMaxWidth())
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Swap(modifier = Modifier.padding(horizontal = 15.sdp, vertical = 15.sdp),
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
                Spacer(modifier = Modifier.height(6.sdp))
                TranslatorCard(text = state.requestText, onButtonClick = {
                    if (state.requestText.isBlank()) {
                        Toast.makeText(context, "Enter any Word", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.getTranslatorText()
                    }
                }, onValueChange = { viewModel.onTranslatorTextFieldChange(it) }, onMicClick = {
                    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                    intent.putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                    )
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now")
                    startForResult.launch(intent)
                })
                Spacer(modifier = Modifier.height(6.sdp))
                ResultTranslationCard(responseText = state.responseText,
                    language = state.toLanguage.getLanguageNameByShortCode(),
                    onShareClick = {
                        LocalData.shareApp(context)
                    },
                    onRatingClick = {
                        viewModel.hideShowRateUsDialog()
                    },
                    onCopyClick = {
                        LocalData.copyToClipboard(context, state.responseText)
                        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
                    })
            }
        }
    }
}