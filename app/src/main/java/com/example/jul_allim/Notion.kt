package com.example.jul_allim

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class Notion(val id: String, val content: String,val img: Array<String> = arrayOf(), val vote: String = ""){
    val preview: String=" ● ${content.subSequence(0,minOf(10,content.length)).filter { it!='\n' }}..."

}
fun toNotion(notion: Notion){
    MainActivity.getInstance()?.setMainFragment(NotionViewFragment(notion),"공지사항")
}