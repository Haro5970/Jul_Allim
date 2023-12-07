package com.example.jul_allim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jul_allim.Reservation
import com.example.jul_allim.repository.ReservationRepository
import java.text.SimpleDateFormat

class ReservationViewModel : ViewModel() {
    private val repository = ReservationRepository()
    private val reserve_list = MutableLiveData<ArrayList<Reservation>>(arrayListOf())
    val reservations: LiveData<ArrayList<Reservation>> get() = reserve_list
    init {
        dateChange(SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()))
    }
    fun dateChange(date: String){
        repository.observeReservation(reserve_list, date)
    }

    fun newMusictitles(musictitle: List<Reservation>, date_: String){
        repository.newReservation(musictitle, date_)// 데이터베이스에 업데이트
    }
}