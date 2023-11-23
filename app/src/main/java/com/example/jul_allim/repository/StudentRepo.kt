package com.example.jul_allim.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jul_allim.Student
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class StudentRepo {

    //livedata 반환
    fun getData():LiveData<MutableList<Student>> {
       val mutableData = MutableLiveData<MutableList<Student>> ()
        val database = Firebase.database
        val myRef = database.getReference("Student")

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
                        mutableData.value = listData
                    }
                }
            }

            //에러발생시
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return mutableData



    }
}