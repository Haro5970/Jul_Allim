package com.example.jul_allim.repository

import androidx.lifecycle.MutableLiveData
import com.example.jul_allim.Reservation
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ReservationRepository {
    private val database = Firebase.database
    val ReservationRef = database.getReference("Schedule")
    fun observeReservation(livelist: MutableLiveData<MutableList<Reservation>>, date: String) {
        ReservationRef.child(date).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                livelist.postValue(mutableListOf())
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: MutableList<Reservation> = mutableListOf()
                snapshot.children.forEach{
                    list.add(Reservation(date,it.key, it.value as String))
                }
                livelist.postValue(list)
            }
        })
    }

    fun newReservation(livelist: List<Reservation>, day_: String) {
        livelist.forEach{reservation ->
            reservation.day = day_
            ReservationRef.child(day_).child(reservation.time!!).setValue(reservation.musictitle)
        }
    }
}