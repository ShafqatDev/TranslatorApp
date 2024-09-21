package com.example.translatorapp.core.constants

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.camera.core.AspectRatio
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.translatorapp.data.controller.TextRecognitionAnalyzer
import org.json.JSONArray
import java.util.Locale

object LocalData {
    fun shareApp(context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this app!")
            putExtra(
                Intent.EXTRA_TEXT,
                "Hey, check out this amazing app: https://play.google.com/store/apps/details?id=com.example.translatorapp"
            )
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    fun shareText(context: Context, text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT, text
            )
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    fun copyToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
    }

    fun openApp(context: Context, packageName: String) {
        try {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
            )

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        } catch (_: Exception) {
        }
    }

    fun speak(tts: TextToSpeech?, text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun startTextRecognition(
        context: Context,
        cameraController: LifecycleCameraController,
        lifecycleOwner: LifecycleOwner,
        previewView: PreviewView,
        onDetectedTextUpdated: (String) -> Unit
    ) {
        cameraController.imageAnalysisTargetSize =
            CameraController.OutputSize(AspectRatio.RATIO_16_9)
        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(context),
            TextRecognitionAnalyzer(onDetectedTextUpdated = onDetectedTextUpdated)
        )

        cameraController.bindToLifecycle(lifecycleOwner)
        previewView.controller = cameraController
    }

    fun extractFromString(rawText: String): String {
        val jsonArray = JSONArray(rawText)
        val stringBuilder = StringBuilder()
        val thirdList = jsonArray.getJSONArray(0)
        for (innerListIndex in 0 until thirdList.length()) {
            val innerList = thirdList.getJSONArray(innerListIndex)
            val text = innerList.getString(0)
            stringBuilder.append(text)
        }

        return stringBuilder.toString()
    }


    fun startSpeechRecognition(
        startForResult: ManagedActivityResultLauncher<Intent, ActivityResult>,
        isLeft: (Boolean) -> Unit = {}
    ) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now")
        }
        startForResult.launch(intent)
    }
}