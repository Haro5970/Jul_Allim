package com.example.jul_allim

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log

data class Student( val name: String = "Name",
                    val id: String = "ID",
                    val session:String = "Session",
                    val line : String = "Line",
                    val image : String = "img"


){
    val bitmap: () -> Bitmap
        get() = {
            val strData = image

            val imageBytes = Base64.decode(strData, 0)


            BitmapFactory.decodeByteArray(
                imageBytes,
                0,
                imageBytes.size
            )
        }
}

