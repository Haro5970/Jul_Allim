package com.example.jul_allim

import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.tasks.await

data class Notion(val id: String, val content: String,val img: Array<String> = arrayOf(), val vote: String = ""){
    val preview: String=" ● ${content.subSequence(0,minOf(10,content.length))}..."
}
suspend fun getNotions(Jul_Kau: String): Array<Notion> {
    val notions = arrayListOf<Notion>()
    Firebase.database.getReference("Notion/${Jul_Kau}").get()
        .addOnSuccessListener {
            (it.value as ArrayList<HashMap<*, *>>).forEach {
                notions.add(Notion("${it.get("id")!!}", "${it.get("content")}"))
            }
        }.await()
    return notions.toTypedArray()
}