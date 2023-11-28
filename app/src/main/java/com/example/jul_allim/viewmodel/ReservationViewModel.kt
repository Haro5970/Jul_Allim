package com.example.jul_allim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jul_allim.Reservation
import com.example.jul_allim.repository.ReservationRepository

class ReservationViewModel : ViewModel() {
    // val reservations = MutableLiveData<List<Reservation>>()
/*
    fun loadReservations() {
        reservations.value = repository.getReservations()
    }

    fun addReservation(reservation: Reservation) {
        repository.addReservation(reservation)
        loadReservations()
    }

   */
    // private val repository = ReservationRepository()

    /*
    private val Date = MutableLiveData<Array<Reservation>>(arrayOf())
    private val Time = MutableLiveData<Array<Reservation>>(arrayOf())

    val date: LiveData<Array<Reservation>> get() = Date
    val time: LiveData<Array<Reservation>> get() = Time
    /*
    init{
        repository.observeReservation(date,time, )
    }
    */

     */

    private val reservationRepository = ReservationRepository()

    private val _reservations = MutableLiveData<List<Reservation>>()
    val reservations: LiveData<List<Reservation>>
        get() = _reservations

    fun fetchReservationsForDateTime(selectedDate: String?, selectedTime: String?) {
        // selectedDate와 selectedTime이 null인지 확인하고 처리
        if (selectedDate.isNullOrEmpty() || selectedTime.isNullOrEmpty()) {
            // 오류 처리 또는 기본 값 할당 등의 작업을 수행할 수 있습니다.
            return
        }

        reservationRepository.observeReservation(selectedDate!!, selectedTime!!) { musicTitles ->
            val reservationsList = musicTitles.map { musicTitle ->
                Reservation(selectedDate, selectedTime, musicTitle)
            }
            _reservations.postValue(reservationsList)
        }
    }





}

