package com.example.translatorapp.presentation.translate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun RequestCard(
    requestContent: String = "Enter text to translate",
    onRequestChange: (String) -> Unit = {},
    onListenClick: () -> Unit = {},
    onMicClick: () -> Unit = {},
    onTranslateClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.White)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = requestContent,
            onValueChange = { onRequestChange.invoke(it) },
            textStyle = TextStyle(
                fontSize = 12.sp, color = Color.Black
            ),
            placeholder = {
                Text(text = "Enter text to translate")
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(5.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onListenClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Listen"
                    )
                }
                Divider(
                    color = Color.Gray, modifier = Modifier
                        .height(24.dp)
                        .width(1.dp)
                )
                IconButton(onClick = onMicClick) {
                    Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic")
                }
            }
            Button(
                onClick = onTranslateClick,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA000)
                ),
            ) {
                Text(text = "Translate")
            }
        }
    }
}