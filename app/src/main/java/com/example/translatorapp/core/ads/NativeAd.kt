package com.example.translatorapp.core.ads

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.monetization.composeviews.SdkNativeAd
import com.monetization.core.commons.NativeTemplates
import com.monetization.core.ui.AdsWidgetData
import video.downloader.remoteconfig.SdkRemoteConfigConstants.toConfigString

@Composable
fun NativeAd(
    modifier: Modifier=Modifier,
    activity: Activity,
    adLayout: String = NativeTemplates.SmallNative,
    adKey: String="",
) {
    SdkNativeAd(
        modifier = modifier,
        activity = activity,
        adLayout = adLayout,
        adKey = adKey,
        placementKey = true.toConfigString(),
        adsWidgetData = AdsWidgetData(enabled = 1, adHeadLineTextColor = "#000000", adBodyTextColor = "#000000"),
    )
}