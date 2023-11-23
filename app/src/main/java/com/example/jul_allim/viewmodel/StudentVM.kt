package com.example.jul_allim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jul_allim.Student
import com.example.jul_allim.repository.StudentRepo

class StudentVM : ViewModel() {

    //레포지토리 객체 생성
    private val repo = StudentRepo()
    //livedata 반환
    fun fetchData():LiveData<MutableList<Student>> {
        val mutableData = MutableLiveData<MutableList<Student>>()
        repo.getData().observeForever {
            mutableData.value = it
        }

        return mutableData
    }
}