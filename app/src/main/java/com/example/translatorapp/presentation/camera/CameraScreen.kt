package com.example.translatorapp.presentation.camera

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.translatorapp.core.constants.LocalData.startTextRecognition
import com.example.translatorapp.data.data_source.model.getLanguageNameByShortCode
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.presentation.components.Swap
import com.example.translatorapp.presentation.navigation.components.Screens
import org.koin.androidx.compose.koinViewModel

@Composable
fun CameraScreen() {
    val viewModel: CameraViewModel = koinViewModel()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    LaunchedEffect(key1 = lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.updateLanguages()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
            PreviewView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                setBackgroundColor(Color.Black.toArgb())
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                scaleType = PreviewView.ScaleType.FILL_START
            }.also { previewView ->

                startTextRecognition(
                    context = context,
                    cameraController = cameraController,
                    lifecycleOwner = lifecycleOwner,
                    previewView = previewView,
                    onDetectedTextUpdated = viewModel::onTextUpdated
                )
            }
        })


        Swap(modifier = Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth(0.98f),
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


        SelectionContainer {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
                    .heightIn(max = 100.dp)
                    .verticalScroll(rememberScrollState()),
                text = state.detectedText,
                color = Color.Black
            )
        }
    }
}
