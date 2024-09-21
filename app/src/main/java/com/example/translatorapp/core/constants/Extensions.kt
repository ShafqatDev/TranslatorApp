package com.example.translatorapp.core.constants

import java.net.URLEncoder

fun String.toEncode(): String = URLEncoder.encode(this,"UTF-8")