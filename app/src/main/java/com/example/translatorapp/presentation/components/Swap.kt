package com.example.translatorapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Swap(
    fromLanguage: String = "English",
    fromLanguageClick: () -> Unit = {},
    toLanguage: String = "Spanish",
    toLanguageClick: () -> Unit = {},
    onSwapClick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(35.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.fillMaxWidth(0.99f),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.99f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { fromLanguageClick.invoke() },
                colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color.Black),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = fromLanguage,
                        maxLines = 2,
                        minLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = if (fromLanguage.length > 10) 10.sp else 12.sp
                    )
                }
            }
            IconButton(
                onClick = { onSwapClick.invoke() },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                Icon(
                    imageVector = Icons.Default.SwapHoriz,
                    contentDescription = "Swap Language",
                    modifier = Modifier.size(28.dp),
                    tint = Color.Black
                )
            }
            Button(
                onClick = { toLanguageClick.invoke() },
                colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color.Black),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = toLanguage,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = if (toLanguage.length > 10) 10.sp else 12.sp
                    )
                }
            }
        }
    }
}
