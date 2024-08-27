package com.example.translatorapp.utils

import android.content.res.ColorStateList
import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.translatorapp.R
import com.example.translatorapp.core.constants.LocalData
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TranslatorCard(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    onMicClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(200.sdp)
            .border(1.sdp, Color.LightGray)
            .padding(horizontal = 10.sdp, vertical = 5.sdp)
    ) {
        TransparentTextField(text = text, onValueChange)

        IconButton(modifier = Modifier.align(Alignment.TopEnd), onClick = {
            onMicClick.invoke()
        }) {
            Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic")
        }
        OutlinedButton(modifier = Modifier.align(
            Alignment.BottomEnd
        ),
            shape = RoundedCornerShape(2.sdp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange)),
            onClick = { onButtonClick.invoke() }) {
            Text(
                text = "Translate",
                color = Color.White,
                fontSize = 10.ssp,
            )
        }
    }
}

@Composable
fun ResultTranslationCard(
    language: String,
    responseText: String,
    onShareClick: () -> Unit,
    onRatingClick: () -> Unit,
    onCopyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(150.sdp)
            .background(Color.White)
            .shadow(elevation = 1.sdp, shape = RoundedCornerShape(2.sdp))
            .padding(horizontal = 10.sdp, vertical = 5.sdp)
    ) {
        Column {
            Text(
                text = language,
                fontSize = 12.ssp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(3.sdp)
            )
            SelectionContainer {
                Text(
                    text = responseText,
                    fontSize = 12.ssp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }
        }
        Row(
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        ) {
            Icon(imageVector = Icons.Default.Share,
                contentDescription = "Share",
                modifier = Modifier.clickable { onShareClick.invoke() })
            Spacer(modifier = Modifier.width(10.sdp))
            Icon(imageVector = Icons.Default.StarBorder,
                contentDescription = "Star",
                modifier = Modifier.clickable { onRatingClick.invoke() })
            Spacer(modifier = Modifier.width(10.sdp))
            Icon(imageVector = Icons.Default.ContentCopy,
                contentDescription = "ContentCopy",
                modifier = Modifier.clickable { onCopyClick.invoke() })
        }
    }
}

@Composable
fun Swap(
    fromLanguage: String,
    fromLanguageClick: () -> Unit,
    toLanguage: String,
    toLanguageClick: () -> Unit,
    onSwapClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.sdp, color = Color.LightGray, shape = RoundedCornerShape(8.sdp))
            .height(50.sdp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.white)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(modifier = Modifier
                .weight(1f)
                .clickable { }
                .background(colorResource(id = R.color.white)), onClick = {
                fromLanguageClick.invoke()
            }) {
                Text(
                    text = fromLanguage,
                    fontSize = 12.ssp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            Icon(imageVector = Icons.Default.SwapHoriz,
                contentDescription = "Swap Language",
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onSwapClick.invoke()
                    }
                    .background(colorResource(id = R.color.white)),
                tint = Color.Black)
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .clickable { }
                    .background(colorResource(id = R.color.white)),
                onClick = {
                    toLanguageClick.invoke()
                }) {
                Text(
                    text = toLanguage,
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TransparentTextField(
    text: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    TextField(value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(140.sdp)
            .background(Color.Transparent),
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
        singleLine = false,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(text = "Enter text to Translate") })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslatorTopBar(modifier: Modifier = Modifier, text: String = "Translator App") {
    TopAppBar(modifier = modifier, title = {
        Text(
            text = text,
            fontSize = 18.ssp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(10.sdp)
        )
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageTopAppBar(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    text: String,
    searchValue: String = "",
    onValueChange: (String) -> Unit = {},
    onBackPress: () -> Unit = {},
    enableSearch: Boolean,
    isSearchActive: Boolean,
    onSearchToggle: (Boolean) -> Unit
) {
    TopAppBar(
        windowInsets = TopAppBarDefaults.windowInsets,
        modifier = modifier, navigationIcon = {
            IconButton(onClick = {
                onBackPress.invoke()
            }) {
                Icon(
                    imageVector = icon, contentDescription = "Back", tint = Color.Black
                )
            }
        }, title = {
            if (isSearchActive) {
                TextField(
                    value = searchValue,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(end = 10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp, color = Color.Black
                    ),
                    singleLine = true,
                )
            } else {
                TitleText(text = text)
            }
        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White, titleContentColor = Color.Black
        ), actions = {
            if (enableSearch) {
                IconButton(onClick = {
                    onSearchToggle(!isSearchActive)
                    onValueChange("")
                    if (!isSearchActive) {
                        onValueChange("")
                    }
                }) {
                    Icon(
                        imageVector = if (isSearchActive) {
                            Icons.Default.Close
                        } else {
                            Icons.Default.Search
                        }, contentDescription = "Search", tint = Color.Black
                    )
                }
            }
        })
}

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier, text = text, style = MaterialTheme.typography.titleLarge.copy(
            color = Color.Black, fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun RateUsDialogUI(
    isRateUsDialogOpen: Boolean, onDismiss: () -> Unit, onRateUsClicked: (ratingStars: Int) -> Unit
) {
    if (isRateUsDialogOpen) {
        val ratingDone = remember { mutableStateOf(false) }
        var ratingStars = 0f
        val coroutineScope = rememberCoroutineScope()
        val context = LocalContext.current
        Dialog(
            onDismissRequest = {
                onDismiss.invoke()
            }, properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .background(
                        colorResource(id = R.color.white), shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.sdp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.white))

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rate_us_image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 18.dp)
                    )

                    androidx.compose.material3.Text(
                        text = "Enjoying our app?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .align(Alignment.CenterHorizontally)
                    )


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {
                            androidx.compose.material3.Text(
                                text = "Rate your experience with us",
                                fontSize = 13.sp,
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Row(
                                modifier = Modifier
                                    .padding(30.sdp, 0.sdp, 0.sdp, 0.sdp)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                androidx.compose.material3.Text(
                                    text = "We appreciate your feedback",
                                    color = Color(0xFF2B38BA),
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                            AndroidView(factory = { context ->
                                RatingBar(context).apply {
                                    max = 5
                                    stepSize = 1f
                                    progressTintList = ColorStateList.valueOf(0xFFf0cf13.toInt())
                                }
                            }, update = { view ->
                                view.rating = ratingStars
                                view.onRatingBarChangeListener =
                                    RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                                        ratingStars = rating
                                    }
                            })

                            Box(
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight()
                                    .align(Alignment.CenterHorizontally)
                                    .background(
                                        color = Color(0xFF388E3C), shape = RoundedCornerShape(5.sdp)
                                    )
                                    .padding(horizontal = 25.sdp, vertical = 8.sdp)
                                    .clickable {
                                        if (ratingStars >= 3) {
                                            LocalData.openApp(context, context.packageName)
                                            ratingDone.value = true
                                            onRateUsClicked(ratingStars.toInt())

                                        } else {
                                            coroutineScope.launch {
                                                ratingDone.value = true
                                                delay(1000)
                                                onRateUsClicked(ratingStars.toInt())
                                            }
                                            if (ratingStars < 1) {
                                                onRateUsClicked(ratingStars.toInt())
                                            }
                                        }

                                    },

                                ) {
                                androidx.compose.material3.Text(
                                    text = "Rate Us",
                                    color = Color.White,
                                    fontSize = 13.ssp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if (ratingDone.value) {
                        androidx.compose.material3.Text(
                            text = "Thanks",
                            color = Color.Black,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}