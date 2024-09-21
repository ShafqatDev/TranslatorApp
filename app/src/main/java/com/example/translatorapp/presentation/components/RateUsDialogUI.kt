package com.example.translatorapp.presentation.components
import android.content.res.ColorStateList
import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.translatorapp.R
import com.example.translatorapp.core.constants.LocalData.openApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RateUsDialogUI(
    onDismiss: () -> Unit, onRateUsClicked: (ratingStars: Int) -> Unit
) {
    var ratingStars by remember { mutableStateOf(0f) }
    val ratingDone = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rate_us_image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 8.dp)
                )

                Text(
                    text = "Enjoy Our App",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Text(
                    text = "We would love to hear your feedback!",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 8.dp)
                )

                AndroidView(factory = { context ->
                    RatingBar(context).apply {
                        max = 5
                        stepSize = 1f
                        progressTintList = ColorStateList.valueOf(0xFFF0CF13.toInt())
                    }
                }, update = { view ->
                    view.rating = ratingStars
                    view.setOnRatingBarChangeListener { _, rating, _ ->
                        ratingStars = rating
                    }
                }, modifier = Modifier
                    .padding(top = 16.dp)
                    .wrapContentSize()
                )

                Button(
                    onClick = {
                        if (ratingStars >= 3) {
                            openApp(context, context.packageName)
                            ratingDone.value = true
                            onRateUsClicked(ratingStars.toInt())
                        } else {
                            coroutineScope.launch {
                                ratingDone.value = true
                                delay(1000)
                                onRateUsClicked(ratingStars.toInt())
                            }
                        }
                    }, shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2B38BA)
                    ), modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "Rate Us",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (ratingDone.value) {
                    Text(
                        text = "Thanks for your feedback!",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Not Now",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

