package com.example.translatorapp.core.ads

import android.app.Activity
import com.monetization.interstitials.extensions.InstantInterstitialAdsManager
import com.monetization.interstitials.extensions.PreloadInterstitialAdsManager
import kotlinx.coroutines.flow.MutableStateFlow
import video.downloader.remoteconfig.SdkRemoteConfigConstants.toConfigString

var showLoadingDialog = MutableStateFlow(false)
var showLoadingBgDialog = MutableStateFlow(false)


class MyFullScreenAdsManager {
    fun showInterstitialAd(
        activity: Activity,
        instantAd: Boolean,
        onAdDismissed: (Boolean) -> Unit
    ){
        if(instantAd.not()){
            PreloadInterstitialAdsManager.tryShowingInterstitialAd(
                true.toConfigString(),
                MyAdsKeys.TestInter.name,
                requestNewIfNotAvailable = false,
                requestNewIfAdShown = true,
                onAdDismiss = {onAdDismissed.invoke(it)},
                onLoadingDialogStatusChange = {showDialog->
                    showLoadingDialog.value = showDialog
                },
                activity = activity,
                normalLoadingTime = 1_000
            )
        }else{
            InstantInterstitialAdsManager.showInstantInterstitialAd(
                placementKey = true.toConfigString(),
                onAdDismiss = {onAdDismissed.invoke(it)},
                onLoadingDialogStatusChange = {showDialog->
                    showLoadingDialog.value = showDialog
                },
                activity = activity,
                normalLoadingTime = 1_000,
                instantLoadingTime = 8_000,
                key = MyAdsKeys.TestInter.name
            )
        }
    }
}