package com.example.jul_allim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.ListTimeBinding

class ReservationAdapter(var reservations: Array<Reservation>, var selectedDate: String)
    : RecyclerView.Adapter<ReservationAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListTimeBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount() = reservations.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val reservation = reservations[position]

        // 현재 선택된 날짜와 예약의 날짜를 비교합니다.
        if (reservation.day == selectedDate) {
            holder.bind(reservation)
        } else {
            // 현재 선택된 날짜와 예약의 날짜가 다르면 항목을 숨깁니다.
            holder.itemView.visibility = View.GONE
        }
    }
    class Holder(private val binding: ListTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: Reservation) {
            binding.txtTime.text = reservation.time
            binding.txtMusictitle.text = reservation.musictitle

            // musictitle이 비어 있으면 btn_ms를 보이게 함
            binding.btnMs.visibility = if (reservation.musictitle.isNullOrEmpty()) View.VISIBLE else View.GONE

            //
            // val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))

            // Reservation 객체의 날짜와 현재 날짜가 같으면 항목을 보이게 함
            /*if (reservation.day == currentDate) {
                binding.txtTime.text = reservation.time
                binding.txtMusictitle.text = reservation.musictitle
                binding.btnMs.visibility = if (reservation.musictitle.isNullOrEmpty()) View.VISIBLE else View.GONE
            } else {
                // Reservation 객체의 날짜와 현재 날짜가 다르면 항목을 보이지 않게 함
                binding.root.visibility = View.GONE
            }

             */
        }
    }




}