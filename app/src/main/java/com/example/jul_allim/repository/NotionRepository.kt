package com.example.jul_allim.repository

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
                Log.d("getSnapshot",snapshot.value.toString())
                (snapshot.value as ArrayList<HashMap<String,String>>).forEach {
                    if(it.get("id")!!.toString().get(0)=='J'){
                        arrJ.add(Notion(it.get("id")!!.toString(),it.get("content")!!.toString()))}
                    else{
                        arrK.add(Notion(it.get("id")!!.toString(),it.get("content")!!.toString()))}
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
            val data = it.value as ArrayList<HashMap<String,String>>
            data.removeIf{
                it.get("id")==targetNotionID
            }
            NotionRef.setValue(data)
        }
    }

    fun addNotion(id: String, content: String){
        NotionRef.get().addOnSuccessListener {
            val cnt = (it.value as ArrayList<*>).size
            NotionRef.child(cnt.toString()).setValue(
                hashMapOf("id" to id,"content" to content)
            )
        }
    }
}