package com.example.jul_allim.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jul_allim.MainActivity
import com.example.jul_allim.ProfileFragment
import com.example.jul_allim.Student
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.sql.Struct

class StudentRepo {

    private val database = Firebase.database

    val myRef = database.getReference("Student")
    // Modify the function to take parameters
    fun getData(studentList: MutableLiveData<MutableList<Student>>) {

        // 데이터 변경
        myRef.addValueEventListener(object : ValueEventListener{
            val listData: MutableList<Student> = mutableListOf<Student>()

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Student::class.java)
                        //리스트에 추가
                        listData.add(getData!!)

                        // MutableLiveData에 현재 리스트 값을 설정하여 Observer에 통지
                        studentList.postValue(listData)
                    }
                }
            }

            //에러발생시
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    // 내 정보만 수정

    fun profileUpdate(id: String, session: String, line: String, image: String){
        myRef.get().addOnSuccessListener{std->
            std.children.forEach {ch->

                val v = ch.value as HashMap<String,String>
                if (v.get("name") == MainActivity.myname) {
                    ch.ref.child("id").setValue(id)
                    ch.ref.child("line").setValue(line)
                    ch.ref.child("session").setValue(session)
                    ch.ref.child("image").setValue(image)
                }
            }
        }

    }
}
