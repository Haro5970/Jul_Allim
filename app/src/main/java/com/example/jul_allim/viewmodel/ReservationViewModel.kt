package com.example.jul_allim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jul_allim.Reservation
import com.example.jul_allim.repository.ReservationRepository

class ReservationViewModel : ViewModel() {
    private val reservationRepository = ReservationRepository()

    private val _reservations = MutableLiveData<List<Reservation>>()
    val reservations: LiveData<List<Reservation>>
        get() = _reservations

    fun fetchReservationsForDateTime(selectedDate: String, selectedTime: String) {
        reservationRepository.getReservationsForDateAndTime(selectedDate, selectedTime) { musicTitles ->
            val reservationsList = musicTitles.map { musicTitle ->
                Reservation(selectedDate, selectedTime, musicTitle)
            }
            _reservations.postValue(reservationsList)
        }
    }
}
