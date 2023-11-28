package com.example.jul_allim.repository

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ReservationRepository {
    // private val reservations = mutableListOf<Reservation>()

    /*
    fun getReservations(): List<Reservation> {
        return reservations
    }

    fun addReservation(reservation: Reservation) {
        reservations.add(reservation)
    }

     */

    private val database = Firebase.database
    private val ReservationsRef = database.getReference("reservations")

    fun observeReservation(date: String, time: String, onReservationsFetched: (List<String>) -> Unit) {
        ReservationsRef.child(date).child(time).child("musictitle")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val musicTitles: MutableList<String> = mutableListOf()
                    for (childSnapshot in dataSnapshot.children) {
                        val musicTitle = childSnapshot.getValue(String::class.java)
                        musicTitle?.let {
                            musicTitles.add(it)
                        }
                    }
                    onReservationsFetched(musicTitles)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                }
            })
    }



}
