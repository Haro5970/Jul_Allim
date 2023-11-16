package com.example.jul_allim

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.jul_allim.repository.getBitmapFromString
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
                "imgs" to img_
            )
        }
        else{
            mapdata = map
        }
    }

    // 각 데이터는 mapdata에서 get() 해서 사용
    val id: String get() = mapdata.get("id") as String
    val content: String get() = mapdata.get("content") as String
    val imgs: Array<Bitmap> get() = if(mapdata.keys.contains("imgs")) Array<Bitmap>( (mapdata.get("imgs") as ArrayList<String>).size,{i-> getBitmapFromString( (mapdata.get("imgs") as ArrayList<String>).get(i) ) })
                                    else arrayOf()

    val preview: String=" ● ${content.subSequence(0,minOf(10,content.length)).filter { it!='\n' }}..."

}
