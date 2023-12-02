package com.example.jul_allim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jul_allim.Reservation
import com.example.jul_allim.repository.ReservationRepository

class ReservationViewModel : ViewModel() {
    private val repository = ReservationRepository()
    private val reserve_list = MutableLiveData<MutableList<Reservation>>(mutableListOf())
    val reservations: LiveData<MutableList<Reservation>> get() = reserve_list
    var day: String = "20231108"

    init{
        repository.observeReservation(reserve_list, day)
    }

    fun dayChange(day_: String){
        repository.observeReservation(reserve_list, day_)
    }

    fun newMusictitles(musictitle: List<Reservation>){
        repository.newReservation(musictitle)// 데이터베이스에 업데이트
    }
}