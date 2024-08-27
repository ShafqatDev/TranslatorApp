package com.example.translatorapp.myapp

import android.app.Application
import com.example.translatorapp.di.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(modulesList)
            androidContext(applicationContext)
        }
    }

}