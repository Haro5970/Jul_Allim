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
    fun observeReservation(livelist: MutableLiveData<ArrayList<Reservation>>, date: String) {
        ReservationRef.child(date).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: ArrayList<Reservation> = arrayListOf()
                snapshot.children.forEach{
                    it.key?.let { key ->
                        list.add(Reservation(date,key, it.value as String))
                    }
                }
                livelist.postValue(list)
            }
        })
    }

    fun newReservation(livelist: List<Reservation>, date_: String) {
        livelist.forEach{reservation ->
            reservation.ymddate = date_
            ReservationRef.child(date_).child(reservation.time).setValue(reservation.musictitle)
        }
    }
}