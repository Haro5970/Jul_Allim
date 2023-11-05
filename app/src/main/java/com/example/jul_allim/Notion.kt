package com.example.jul_allim

data class Notion(val id: String, val content: String,val img: Array<String> = arrayOf(), val vote: String = ""){
    val preview: String=" ● ${content.subSequence(0,minOf(10,content.length))}..."
}
