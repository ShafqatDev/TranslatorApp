package com.example.translatorapp.utils

import android.content.ClipboardManager
import android.content.Context

class CopyPasteManager(
    private val context: Context,

    ) {
    fun getCopiedText(): String? {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        return if (clipboardManager.hasPrimaryClip()) {
            val clipData = clipboardManager.primaryClip
            val clipItem = clipData?.getItemAt(0)
            val copiedText = clipItem?.text
            copiedText?.toString()
        } else {
            null
        }
    }
}