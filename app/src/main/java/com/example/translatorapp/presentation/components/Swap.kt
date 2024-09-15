package com.example.translatorapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.translatorapp.R

@Preview(showBackground = true)
@Composable
fun Swap(
    fromLanguage: String = "English",
    fromFlag: Int = R.drawable.fblogo,
    fromLanguageClick: () -> Unit = {},
    toLanguage: String = "Spanish",
    toLanguageClick: () -> Unit = {},
    onSwapClick: () -> Unit = {},
    toFlag: Int = R.drawable.fblogo,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(35.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .wrapContentSize(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { fromLanguageClick.invoke() },
                colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color.Black)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = fromFlag),
                        contentDescription = "Flag",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = fromLanguage, fontWeight = FontWeight.SemiBold
                    )
                }
            }
            IconButton(onClick = { onSwapClick.invoke() }) {
                Icon(
                    imageVector = Icons.Default.SwapHoriz,
                    contentDescription = "Swap Language",
                    modifier = Modifier.size(28.dp),
                    tint = Color.Black
                )
            }
            Button(
                onClick = { toLanguageClick.invoke() },
                colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color.Black)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = toLanguage, fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = toFlag),
                        contentDescription = "Flag",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}
