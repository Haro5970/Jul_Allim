package com.example.jul_allim.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jul_allim.Music
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class MusiclistRepository {
    private val database = Firebase.database
    val MusicRef =  database.getReference("Music")

    fun observeMusic(livelist: MutableLiveData<ArrayList<Music>>) {
        MusicRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                livelist.postValue(arrayListOf())
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: ArrayList<Music> = arrayListOf()
                snapshot.children.forEach{
                    Log.d("rdtfyguhjki",it.value.toString())
                    list.add(it.getValue(Music::class.java)!!)
                }
                livelist.postValue(list)
            }
        })
    }

    fun newMusic(livelist: Music) {
       MusicRef.get().addOnSuccessListener {
           MusicRef.child(it.childrenCount.toString()).setValue(livelist)
       }
    }
}
