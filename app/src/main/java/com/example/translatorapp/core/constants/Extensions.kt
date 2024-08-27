package com.example.translatorapp.core.constants

import java.net.URLEncoder

fun String.toEncode() = URLEncoder.encode(this,"UTF-8")