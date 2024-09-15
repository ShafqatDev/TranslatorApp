package com.example.translatorapp.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.translatorapp.R
import com.example.translatorapp.ui.theme.MainColor


@Composable
fun TranslatorTabRow(selectedTab: Int, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MainColor)
    ) {
        TabRow(
            selectedTab = selectedTab,
            onClick = onClick,
            icon = Icons.Default.Translate,
            tabIndex = 0
        )

        TabRow(
            selectedTab = selectedTab, onClick = onClick, icon = Icons.Default.Mic, tabIndex = 1
        )
        TabRow(
            selectedTab = selectedTab,
            onClick = onClick,
            icon = Icons.Default.CameraAlt,
            tabIndex = 2
        )
        TabRow(
            selectedTab = selectedTab, onClick = onClick, icon = Icons.Default.History, tabIndex = 3
        )
        TabRow(
            selectedTab = selectedTab, onClick = onClick, icon = Icons.Default.Star, tabIndex = 4
        )
    }
}

@Composable
fun RowScope.TabRow(
    selectedTab: Int,
    onClick: (Int) -> Unit,
    icon: ImageVector,
    tabIndex: Int,
    selectedColor: Color = colorResource(id = R.color.white),
    unselectedColor: Color = MainColor
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .clickable {
                onClick.invoke(tabIndex)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon, contentDescription = null, tint = selectedColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(
                    color = if (selectedTab == tabIndex) {
                        selectedColor
                    } else {
                        unselectedColor
                    }
                )
        )
    }
}
