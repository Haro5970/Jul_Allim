package com.example.jul_allim.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jul_allim.Notice
import com.example.jul_allim.NoticeImageAdapter
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat

class NoticeRepository {
    private val database = Firebase.database
    val NoticeLinesRef = database.getReference("Notice/lines")
    val NoticeImageRef = database.getReference("Notice/images")
    fun observeNoticeList(noticejList: MutableLiveData<Array<Notice>>, noticekList: MutableLiveData<Array<Notice>>) {
        NoticeLinesRef.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                val arrJ = arrayListOf<Notice>()
                val arrK = arrayListOf<Notice>()
                snapshot.children.forEach {
                    if(it.key!!.startsWith("J")){
                        arrJ.add(Notice(it.key!!,it.value as String))
                    }
                    else{
                        arrK.add(Notice(it.key!!,it.value as String))
                    }
                }
                arrJ.sortBy{it.id}; arrJ.reverse()
                arrK.sortBy{it.id}; arrK.reverse()
                noticejList.postValue(arrJ.toTypedArray())
                noticekList.postValue(arrK.toTypedArray())
            }
        })
    }
    fun getImages(id: String,liveData: MutableLiveData<Array<Bitmap>>) {
        NoticeImageRef.child(id).get().addOnSuccessListener {
            it.value?.let {
                val strData = it as ArrayList<String>
                val bitmapData = arrayListOf<Bitmap>()
                strData.forEach {
                    val imageBytes = Base64.decode(it, 0)
                    bitmapData.add(
                        BitmapFactory.decodeByteArray(
                            imageBytes,
                            0,
                            imageBytes.size
                        )
                    )
                }
                liveData.postValue(bitmapData.toTypedArray())
            } ?: run {
                liveData.postValue(arrayOf())
            }
        }
    }

    fun newNotice(id: String, lines: String, images: Array<Bitmap>) {
        NoticeLinesRef.child(id).setValue(lines)

        if(images.size>0){
            val strImages = arrayListOf<String>()
            images.forEach {
                val baos = ByteArrayOutputStream()
                it.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                strImages.add(Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT))
            }
            NoticeImageRef.child(id).setValue(strImages)
        }

    }

    fun removeNotice(id: String) {
        NoticeLinesRef.child(id).removeValue()
        NoticeImageRef.child(id).removeValue()
    }
}