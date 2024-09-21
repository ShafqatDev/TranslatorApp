package com.example.translatorapp.core.ads

import com.monetization.core.ad_units.core.AdType

enum class MyAdsKeys(
    val adType: AdType,
    val adId: List<String> = listOf(""),
) {
    TestNative(AdType.NATIVE, listOf("")),
    TestBanner(AdType.BANNER, listOf("")),
    TestInter(AdType.INTERSTITIAL, listOf("")),
}