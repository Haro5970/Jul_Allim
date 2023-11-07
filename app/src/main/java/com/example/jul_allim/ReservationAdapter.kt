package com.example.jul_allim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.ListTimeBinding
import org.intellij.lang.annotations.JdkConstants.ListSelectionMode

class ReservationAdapter(val day: Array<Reservation>)
    : RecyclerView.Adapter<ReservationAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListTimeBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount() = day.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(day[position])
    }
    class Holder(private val binding: ListTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: Reservation) {
            binding.txtTime.text = reservation.time
            binding.txtMusictitle.text = reservation.musictitle

        }

    }
}