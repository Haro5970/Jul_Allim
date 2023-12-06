package com.example.jul_allim

data class Reservation(var day: String, var time: String, var musictitle: String, var isChecked: Boolean = false){
    val maxLength = 4
    val preview: String = if (musictitle.length > maxLength) {
        "${musictitle.subSequence(0, maxLength)}..."
    } else {
        musictitle
    }
}
