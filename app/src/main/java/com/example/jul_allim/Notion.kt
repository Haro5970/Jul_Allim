package com.example.jul_allim

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Notion(id_: String="", content_: String="",img_: Array<String> =arrayOf(), map: HashMap<String,Any>? = null){
    val mapdata: HashMap<String,Any>
    init{
        if(map==null) {
            mapdata = hashMapOf(
                "id" to id_,
                "content" to content_,
                "img" to img_
            )
        }
        else{
            mapdata = map
        }
    }

    val id: String get() = mapdata.get("id") as String
    val content: String get() = mapdata.get("content") as String

    val preview: String=" ● ${content.subSequence(0,minOf(10,content.length)).filter { it!='\n' }}..."

}
fun toNotion(notion: Notion){
    MainActivity.getInstance()?.setMainFragment(NotionViewFragment(notion),"공지사항")
}