package com.example.translatorapp.core.ads

import android.app.Activity
import androidx.lifecycle.ViewModel

class MyInterViewModel(
    private val fullScreenAdsManager: MyFullScreenAdsManager
): ViewModel() {
    fun showInterstitialAd(activity: Activity, instantAd:Boolean, onAdDismissed:(Boolean)->Unit){
        fullScreenAdsManager.showInterstitialAd(activity,instantAd,onAdDismissed)
    }
}