package com.example.translatorapp.myapp

import android.app.Application
import com.example.debug.DebugListener
import com.example.debug.SdkDebugHelper
import com.example.translatorapp.di.modulesList
import com.example.translatorapp.BuildConfig
import com.example.translatorapp.core.ads.AdsEntryManager.addController
import com.monetization.adsmain.sdk.AdsSdk
import com.monetization.core.ad_units.core.AdType
import com.monetization.core.commons.SdkConfigs
import com.monetization.core.listeners.SdkListener
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TranslatorApp : Application() {
    private val adsSdk: AdsSdk by inject()
    override fun onCreate() {
        super.onCreate()
        addController()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(modulesList)
        }
        SdkDebugHelper.initDebugMode(applicationContext,object : DebugListener {
            override fun canLaunchDebugActivity(): Boolean {
                return true
            }
        })
        adsSdk.initAdsSdk(
            context = applicationContext,
            initAdsSdk = true,
            initFirebase = false,
            onInitialized = {}
        )

        SdkConfigs.setListener(object : SdkListener {
            override fun canLoadAd(adType: AdType, adKey: String): Boolean {
                return true
            }

            override fun canShowAd(adType: AdType, adKey: String): Boolean {
                return true
            }
        }, testModeEnable = BuildConfig.DEBUG)
    }

}