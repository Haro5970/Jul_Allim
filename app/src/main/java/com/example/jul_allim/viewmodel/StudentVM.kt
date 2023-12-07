package com.example.jul_allim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jul_allim.Student
import com.example.jul_allim.repository.StudentRepo

class StudentVM : ViewModel() {

    //레포지토리 객체 생성
    private val repo = StudentRepo()
    private val studentList = MutableLiveData<MutableList<Student>>()


    fun fetchData():LiveData<MutableList<Student>> {
        repo.getData(studentList)
        return studentList
    }

    fun updateprof(num: String, ss: String, line: String, pic: String) {
        repo.profileUpdate(num,ss,line,pic)

    }
}
