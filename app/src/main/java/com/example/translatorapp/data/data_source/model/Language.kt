package com.example.translatorapp.data.data_source.model

import com.example.translatorapp.R

data class LanguageModel(
    val name: String,
    val shortCode: String,
    val flag: Int = 0
)

fun String.getLanguageNameByShortCode() = languageList.firstOrNull {
    it.shortCode == this
}?.name ?: languageList[0].name

val languageList: List<LanguageModel> = listOf(
    LanguageModel(flag = R.drawable.fblogo, name = "Afrikaans", shortCode = "af"),
    LanguageModel(flag = R.drawable.fblogo, name = "Albanian", shortCode = "sq"),
    LanguageModel(flag = R.drawable.fblogo, name = "Amharic", shortCode = "am"),
    LanguageModel(flag = R.drawable.fblogo, name = "Arabic", shortCode = "ar"),
    LanguageModel(flag = R.drawable.fblogo, name = "Armenian", shortCode = "hy"),
    LanguageModel(flag = R.drawable.fblogo, name = "Azerbaijani", shortCode = "az"),
    LanguageModel(flag = R.drawable.fblogo, name = "Basque", shortCode = "eu"),
    LanguageModel(flag = R.drawable.fblogo, name = "Belarusian", shortCode = "be"),
    LanguageModel(flag = R.drawable.fblogo, name = "Bengali", shortCode = "bn"),
    LanguageModel(flag = R.drawable.fblogo, name = "Bosnian", shortCode = "bs"),
    LanguageModel(flag = R.drawable.fblogo, name = "Bulgarian", shortCode = "bg"),
    LanguageModel(flag = R.drawable.fblogo, name = "Catalan", shortCode = "ca"),
    LanguageModel(flag = R.drawable.fblogo, name = "Cebuano", shortCode = "ceb"),
    LanguageModel(flag = R.drawable.fblogo, name = "Chinese (Simplified)", shortCode = "zh-CN"),
    LanguageModel(flag = R.drawable.fblogo, name = "Chinese (Traditional)", shortCode = "zh-TW"),
    LanguageModel(flag = R.drawable.fblogo, name = "Corsican", shortCode = "co"),
    LanguageModel(flag = R.drawable.fblogo, name = "Croatian", shortCode = "hr"),
    LanguageModel(flag = R.drawable.fblogo, name = "Czech", shortCode = "cs"),
    LanguageModel(flag = R.drawable.fblogo, name = "Danish", shortCode = "da"),
    LanguageModel(flag = R.drawable.fblogo, name = "Dutch", shortCode = "nl"),
    LanguageModel(flag = R.drawable.fblogo, name = "English", shortCode = "en"),
    LanguageModel(flag = R.drawable.fblogo, name = "Esperanto", shortCode = "eo"),
    LanguageModel(flag = R.drawable.fblogo, name = "Estonian", shortCode = "et"),
    LanguageModel(flag = R.drawable.fblogo, name = "Filipino", shortCode = "tl"),
    LanguageModel(flag = R.drawable.fblogo, name = "Finnish", shortCode = "fi"),
    LanguageModel(flag = R.drawable.fblogo, name = "French", shortCode = "fr"),
    LanguageModel(flag = R.drawable.fblogo, name = "Galician", shortCode = "gl"),
    LanguageModel(flag = R.drawable.fblogo, name = "Georgian", shortCode = "ka"),
    LanguageModel(flag = R.drawable.fblogo, name = "German", shortCode = "de"),
    LanguageModel(flag = R.drawable.fblogo, name = "Greek", shortCode = "el"),
    LanguageModel(flag = R.drawable.fblogo, name = "Gujarati", shortCode = "gu"),
    LanguageModel(flag = R.drawable.fblogo, name = "Haitian Creole", shortCode = "ht"),
    LanguageModel(flag = R.drawable.fblogo, name = "Hausa", shortCode = "ha"),
    LanguageModel(flag = R.drawable.fblogo, name = "Hawaiian", shortCode = "haw"),
    LanguageModel(flag = R.drawable.fblogo, name = "Hebrew", shortCode = "he"),
    LanguageModel(flag = R.drawable.fblogo, name = "Hindi", shortCode = "hi"),
    LanguageModel(flag = R.drawable.fblogo, name = "Hmong", shortCode = "hmn"),
    LanguageModel(flag = R.drawable.fblogo, name = "Hungarian", shortCode = "hu"),
    LanguageModel(flag = R.drawable.fblogo, name = "Icelandic", shortCode = "is"),
    LanguageModel(flag = R.drawable.fblogo, name = "Igbo", shortCode = "ig"),
    LanguageModel(flag = R.drawable.fblogo, name = "Indonesian", shortCode = "id"),
    LanguageModel(flag = R.drawable.fblogo, name = "Irish", shortCode = "ga"),
    LanguageModel(flag = R.drawable.fblogo, name = "Italian", shortCode = "it"),
    LanguageModel(flag = R.drawable.fblogo, name = "Japanese", shortCode = "ja"),
    LanguageModel(flag = R.drawable.fblogo, name = "Javanese", shortCode = "jv"),
    LanguageModel(flag = R.drawable.fblogo, name = "Kannada", shortCode = "kn"),
    LanguageModel(flag = R.drawable.fblogo, name = "Kazakh", shortCode = "kk"),
    LanguageModel(flag = R.drawable.fblogo, name = "Khmer", shortCode = "km"),
    LanguageModel(flag = R.drawable.fblogo, name = "Kinyarwanda", shortCode = "rw"),
    LanguageModel(flag = R.drawable.fblogo, name = "Korean", shortCode = "ko"),
    LanguageModel(flag = R.drawable.fblogo, name = "Kurdish (Kurmanji)", shortCode = "ku"),
    LanguageModel(flag = R.drawable.fblogo, name = "Kyrgyz", shortCode = "ky"),
    LanguageModel(flag = R.drawable.fblogo, name = "Lao", shortCode = "lo"),
    LanguageModel(flag = R.drawable.fblogo, name = "Latin", shortCode = "la"),
    LanguageModel(flag = R.drawable.fblogo, name = "Latvian", shortCode = "lv"),
    LanguageModel(flag = R.drawable.fblogo, name = "Lithuanian", shortCode = "lt"),
    LanguageModel(flag = R.drawable.fblogo, name = "Luxembourgish", shortCode = "lb"),
    LanguageModel(flag = R.drawable.fblogo, name = "Macedonian", shortCode = "mk"),
    LanguageModel(flag = R.drawable.fblogo, name = "Malagasy", shortCode = "mg"),
    LanguageModel(flag = R.drawable.fblogo, name = "Malay", shortCode = "ms"),
    LanguageModel(flag = R.drawable.fblogo, name = "Malayalam", shortCode = "ml"),
    LanguageModel(flag = R.drawable.fblogo, name = "Maltese", shortCode = "mt"),
    LanguageModel(flag = R.drawable.fblogo, name = "Maori", shortCode = "mi"),
    LanguageModel(flag = R.drawable.fblogo, name = "Marathi", shortCode = "mr"),
    LanguageModel(flag = R.drawable.fblogo, name = "Mongolian", shortCode = "mn"),
    LanguageModel(flag = R.drawable.fblogo, name = "Myanmar (Burmese)", shortCode = "my"),
    LanguageModel(flag = R.drawable.fblogo, name = "Nepali", shortCode = "ne"),
    LanguageModel(flag = R.drawable.fblogo, name = "Norwegian", shortCode = "no"),
    LanguageModel(flag = R.drawable.fblogo, name = "Nyanja", shortCode = "ny"),
    LanguageModel(flag = R.drawable.fblogo, name = "Odia", shortCode = "or"),
    LanguageModel(flag = R.drawable.fblogo, name = "Pashto", shortCode = "ps"),
    LanguageModel(flag = R.drawable.fblogo, name = "Persian", shortCode = "fa"),
    LanguageModel(flag = R.drawable.fblogo, name = "Polish", shortCode = "pl"),
    LanguageModel(flag = R.drawable.fblogo, name = "Portuguese", shortCode = "pt"),
    LanguageModel(flag = R.drawable.fblogo, name = "Punjabi", shortCode = "pa"),
    LanguageModel(flag = R.drawable.fblogo, name = "Romanian", shortCode = "ro"),
    LanguageModel(flag = R.drawable.fblogo, name = "Russian", shortCode = "ru"),
    LanguageModel(flag = R.drawable.fblogo, name = "Samoan", shortCode = "sm"),
    LanguageModel(flag = R.drawable.fblogo, name = "Scots Gaelic", shortCode = "gd"),
    LanguageModel(flag = R.drawable.fblogo, name = "Serbian", shortCode = "sr"),
    LanguageModel(flag = R.drawable.fblogo, name = "Sesotho", shortCode = "st"),
    LanguageModel(flag = R.drawable.fblogo, name = "Shona", shortCode = "sn"),
    LanguageModel(flag = R.drawable.fblogo, name = "Sindhi", shortCode = "sd"),
    LanguageModel(flag = R.drawable.fblogo, name = "Sinhala", shortCode = "si"),
    LanguageModel(flag = R.drawable.fblogo, name = "Slovak", shortCode = "sk"),
    LanguageModel(flag = R.drawable.fblogo, name = "Slovenian", shortCode = "sl"),
    LanguageModel(flag = R.drawable.fblogo, name = "Somali", shortCode = "so"),
    LanguageModel(flag = R.drawable.fblogo, name = "Spanish", shortCode = "es"),
    LanguageModel(flag = R.drawable.fblogo, name = "Sundanese", shortCode = "su"),
    LanguageModel(flag = R.drawable.fblogo, name = "Swahili", shortCode = "sw"),
    LanguageModel(flag = R.drawable.fblogo, name = "Swedish", shortCode = "sv"),
    LanguageModel(flag = R.drawable.fblogo, name = "Tagalog", shortCode = "tl"),
    LanguageModel(flag = R.drawable.fblogo, name = "Tajik", shortCode = "tg"),
    LanguageModel(flag = R.drawable.fblogo, name = "Tamil", shortCode = "ta"),
    LanguageModel(flag = R.drawable.fblogo, name = "Telugu", shortCode = "te"),
    LanguageModel(flag = R.drawable.fblogo, name = "Thai", shortCode = "th"),
    LanguageModel(flag = R.drawable.fblogo, name = "Turkish", shortCode = "tr"),
    LanguageModel(flag = R.drawable.fblogo, name = "Ukrainian", shortCode = "uk"),
    LanguageModel(flag = R.drawable.fblogo, name = "Urdu", shortCode = "ur"),
    LanguageModel(flag = R.drawable.fblogo, name = "Uzbek", shortCode = "uz"),
    LanguageModel(flag = R.drawable.fblogo, name = "Vietnamese", shortCode = "vi"),
    LanguageModel(flag = R.drawable.fblogo, name = "Welsh", shortCode = "cy"),
    LanguageModel(flag = R.drawable.fblogo, name = "Xhosa", shortCode = "xh"),
    LanguageModel(flag = R.drawable.fblogo, name = "Yiddish", shortCode = "yi"),
    LanguageModel(flag = R.drawable.fblogo, name = "Yoruba", shortCode = "yo"),
    LanguageModel(flag = R.drawable.fblogo, name = "Zulu", shortCode = "zu"),
    LanguageModel(flag = R.drawable.fblogo, name = "Tatar", shortCode = "tt"),
    LanguageModel(flag = R.drawable.fblogo, name = "Bashkir", shortCode = "ba"),
    LanguageModel(flag = R.drawable.fblogo, name = "Avaric", shortCode = "av"),
    LanguageModel(flag = R.drawable.fblogo, name = "Twi", shortCode = "tw"),
    LanguageModel(flag = R.drawable.fblogo, name = "Bemba", shortCode = "bem"),
    LanguageModel(flag = R.drawable.fblogo, name = "Akan", shortCode = "ak"),
    LanguageModel(flag = R.drawable.fblogo, name = "Cherokee", shortCode = "chr"),
    LanguageModel(flag = R.drawable.fblogo, name = "Chichewa", shortCode = "ny"),
    LanguageModel(flag = R.drawable.fblogo, name = "Kongo", shortCode = "kg"),
    LanguageModel(flag = R.drawable.fblogo, name = "Ganda", shortCode = "lg"),
    LanguageModel(flag = R.drawable.fblogo, name = "Mongolian (Traditional)", shortCode = "mn"),
    LanguageModel(flag = R.drawable.fblogo, name = "Norwegian Bokmål", shortCode = "nb"),
    LanguageModel(flag = R.drawable.fblogo, name = "Norwegian Nynorsk", shortCode = "nn"),
    LanguageModel(flag = R.drawable.fblogo, name = "Oromo", shortCode = "om"),
    LanguageModel(flag = R.drawable.fblogo, name = "Quechua", shortCode = "qu"),
    LanguageModel(flag = R.drawable.fblogo, name = "Sanskrit", shortCode = "sa"),
    LanguageModel(flag = R.drawable.fblogo, name = "Swati", shortCode = "ss"),
    LanguageModel(flag = R.drawable.fblogo, name = "Tigrinya", shortCode = "ti"),
    LanguageModel(flag = R.drawable.fblogo, name = "Tongan", shortCode = "to"),
    LanguageModel(flag = R.drawable.fblogo, name = "Tswana", shortCode = "tn"),
    LanguageModel(flag = R.drawable.fblogo, name = "Turkmen", shortCode = "tk"),
    LanguageModel(flag = R.drawable.fblogo, name = "Uighur", shortCode = "ug"),
    LanguageModel(flag = R.drawable.fblogo, name = "Wolof", shortCode = "wo"),
    LanguageModel(flag = R.drawable.fblogo, name = "Xhosa", shortCode = "xh")
)
