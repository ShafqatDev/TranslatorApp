package com.example.translatorapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translatorapp.R

@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Divider(
            color = Color(0xFFB39DDB),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        DrawerItem(iconRes = Icons.Default.Share, text = "Share App") {}
        Divider(
            color = Color(0xFFB39DDB),
            thickness = 0.5.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        DrawerItem(iconRes = Icons.Default.StarRate, text = "Rate Us") {}
        Divider(
            color = Color(0xFFB39DDB),
            thickness = 0.5.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        DrawerItem(iconRes = Icons.Default.PrivacyTip, text = "Privacy Policy") {}
        Divider(
            color = Color(0xFFB39DDB),
            thickness = 0.5.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        DrawerItem(iconRes = Icons.Default.Feedback, text = "Feedback") {}

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Version 1.0",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun DrawerItem(iconRes: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = iconRes,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}