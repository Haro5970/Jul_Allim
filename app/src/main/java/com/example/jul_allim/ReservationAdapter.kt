package com.example.jul_allim

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.FragmentClubReservateBinding
import com.example.jul_allim.databinding.ListTimeBinding

class ReservationAdapter(var reservations: MutableList<Reservation>, var selectedDate: String)
    : RecyclerView.Adapter<ReservationAdapter.Holder>() {

    private var selectedTime: String? = null
    // var enteredTitle
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListTimeBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount() = reservations.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val reservation = reservations[position]

        // 선택 날짜랑 예약현황날짜 비교
        if (reservation.day == selectedDate) {
            holder.bind(reservation)

        } else {
            // 다르면 안 뜨게
            holder.itemView.visibility = View.GONE
        }
    }

    fun getCheckedReservations(): List<Reservation> {
        return reservations.filter { it.isChecked }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateMusictitles(fragmentBinding: FragmentClubReservateBinding) {

        var enteredTitle: String = fragmentBinding.txtWrite.text.toString()

        val checkedReservations = getCheckedReservations()

        for (reservation in checkedReservations) {
            reservation.musictitle = enteredTitle
        }
        // getNewReservation(enteredTitle)

        notifyDataSetChanged() // 데이터가 변경되었음을 어댑터에 알림
    }

    fun getNewReservation(enteredTitle: String): MutableList<Reservation> {
        reservations.add(Reservation(selectedDate, selectedTime, enteredTitle))
        return reservations
    }

    class Holder(private val binding: ListTimeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reservation: Reservation) {
            binding.txtTime.text = reservation.time
            binding.txtMusictitle.text = reservation.musictitle

            // musictitle이 비어 있으면 btn_ms를 보이게 함
            binding.btnMs.visibility = if (reservation.musictitle.isEmpty()) View.VISIBLE else View.GONE

            binding.btnMs.setOnCheckedChangeListener(null)
            binding.btnMs.isChecked = reservation.isChecked
            binding.btnMs.setOnCheckedChangeListener { _, isChecked ->
                reservation.isChecked = isChecked
            }
        }

    }


}
