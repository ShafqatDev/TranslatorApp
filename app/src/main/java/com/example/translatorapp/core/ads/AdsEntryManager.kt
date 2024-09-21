package com.example.translatorapp.core.ads

import com.example.rewadedad.AdmobRewardedAdsController
import com.example.rewadedad.AdmobRewardedAdsManager
import com.example.rewardedinterads.AdmobRewardedInterAdsController
import com.example.rewardedinterads.AdmobRewardedInterAdsManager
import com.monetization.appopen.AdmobAppOpenAdsController
import com.monetization.appopen.AdmobAppOpenAdsManager
import com.monetization.bannerads.AdmobBannerAdsController
import com.monetization.bannerads.AdmobBannerAdsManager
import com.monetization.core.ad_units.core.AdType
import com.monetization.interstitials.AdmobInterstitialAdsController
import com.monetization.interstitials.AdmobInterstitialAdsManager
import com.monetization.nativeads.AdmobNativeAdsController
import com.monetization.nativeads.AdmobNativeAdsManager

object AdsEntryManager {
    fun addController(){
        MyAdsKeys.entries.forEach{adUnit->
            when(adUnit.adType){
                AdType.NATIVE -> AdmobNativeAdsManager.addNewController(
                    AdmobNativeAdsController(
                        adKey = adUnit.name,
                        adIdsList = adUnit.adId
                    )
                )
                AdType.INTERSTITIAL -> AdmobInterstitialAdsManager.addNewController(
                    AdmobInterstitialAdsController(
                        adKey = adUnit.name,
                        adIdsList = adUnit.adId
                    )
                )
                AdType.REWARDED -> AdmobRewardedAdsManager.addNewController(
                    AdmobRewardedAdsController(
                        adKey = adUnit.name,
                        adIdsList = adUnit.adId
                    )
                )
                AdType.REWARDED_INTERSTITIAL -> AdmobRewardedInterAdsManager.addNewController(
                    AdmobRewardedInterAdsController(
                        adKey = adUnit.name,
                        adIdsList = adUnit.adId
                    )
                )
                AdType.BANNER -> AdmobBannerAdsManager.addNewController(
                    AdmobBannerAdsController(
                        adKey = adUnit.name,
                        adIdsList = adUnit.adId
                    )
                )
                AdType.AppOpen -> AdmobAppOpenAdsManager.addNewController(
                    AdmobAppOpenAdsController(
                        adKey = adUnit.name,
                        adIdsList = adUnit.adId
                    )
                )
            }
        }
    }
}