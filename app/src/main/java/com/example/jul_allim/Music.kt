package com.example.jul_allim

data class Music(val title : String = "",
                 val vocal : String = "",
                 val piano : String = "",
                 val guitar : String = "",
                 val bass : String = "",
                 val drum : String = "",
                 val other : String = ""
){
    val maxLength = 5
    val title_: String get() = if (title.length > maxLength) {
        "${title.subSequence(0, maxLength)}.."
    } else {
        title
    }
}