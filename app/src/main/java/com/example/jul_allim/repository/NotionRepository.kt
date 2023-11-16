package com.example.jul_allim.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jul_allim.Notion
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class NotionRepository {
    val database = Firebase.database
    val NotionRef = database.getReference("Notion")

    fun observeNotion(Jnotions: MutableLiveData<Array<Notion>>, Knotions: MutableLiveData<Array<Notion>>){
        NotionRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val arrJ: ArrayList<Notion>  = arrayListOf()
                val arrK: ArrayList<Notion>  = arrayListOf()
                (snapshot.value as ArrayList<HashMap<String,Any>>).forEach {
                    if(it.get("id")!!.toString().get(0)=='J'){
                        arrJ.add(Notion(map=it)) }
                    else{
                        arrK.add(Notion(map=it)) }
                    }
                arrJ.sortBy{it.id}; arrJ.reverse()
                arrK.sortBy{it.id}; arrK.reverse()
                Jnotions.postValue(arrJ.toTypedArray())
                Knotions.postValue(arrK.toTypedArray())
            }
            override fun onCancelled(error: DatabaseError){}
        })
    }

    fun removeNotion(targetNotionID: String){
        NotionRef.get().addOnSuccessListener {
            val data = it.value as ArrayList<HashMap<String,Any>>
            data.removeIf{
                it.get("id")==targetNotionID
            }
            NotionRef.setValue(data)
        }
    }

    fun addNotion(id: String, content: String, imgs: Array<String>){
        NotionRef.get().addOnSuccessListener {
            val cnt = (it.value as ArrayList<*>).size
            NotionRef.child(cnt.toString()).setValue(
                hashMapOf("id" to id,"content" to content,"imgs" to imgs.toList())
            )
        }
    }
}

fun getBitmapFromString(string: String): Bitmap {
    val imageBytes = Base64.decode(string, 0)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}