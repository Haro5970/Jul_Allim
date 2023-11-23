package com.example.jul_allim

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.FragmentClubReservateBinding
import com.example.jul_allim.databinding.ListTimeBinding

class ReservationAdapter(var reservations: Array<Reservation>, var selectedDate: String)
    : RecyclerView.Adapter<ReservationAdapter.Holder>() {

    private var selectedTime: String? = null
    var enteredTitle: String = ""
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
    // 체크된 것만 반환
    fun getCheckedReservations(): List<Reservation> {
        return reservations.filter { it.isChecked }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateMusictitles(fragmentBinding: FragmentClubReservateBinding) {

        enteredTitle = fragmentBinding.txtWrite.text.toString()

        val checkedReservations = getCheckedReservations()

        for (reservation in checkedReservations) {
            reservation.musictitle = enteredTitle
            // reservation.isChecked = false // 체크박스를 숨기기
            Log.d("태그1","체크박스 숨기는곳")
            // false로 바꾸고 나서 visibility도 GONE으로 바꿔야하는데 이를 어캄

        }

        notifyDataSetChanged() // 데이터가 변경되었음을 어댑터에 알림
        Log.d("태그2","어뎁터 알림 보냄?")
    }

    fun getNewReservation(enteredTitle: String): Array<Reservation> {
        val newReservations = arrayOf(

            Reservation(selectedDate, selectedTime, enteredTitle),
        )
        Log.d("태그3","배열 반환 하나?")

        // newReservations 배열 반환
        return newReservations
    }

    class Holder(private val binding: ListTimeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reservation: Reservation) {
            binding.txtTime.text = reservation.time
            binding.txtMusictitle.text = reservation.preview

            // 체크 돼있는거 체크박스 없애야하는데 얘 뭐 모르겟 응 뭐
            // binding.btnMs.visibility = if (reservation.isChecked) View.GONE else View.VISIBLE
            Log.d("태그4","설마 얘 없어지나?")


            // musictitle이 비어 있으면 btn_ms를 보이게 함
            binding.btnMs.visibility = if (reservation.musictitle.isEmpty()) View.VISIBLE else View.GONE

            binding.txtMusictitle.visibility = View.VISIBLE

            // musictitle이 비어 있지 않으면 txtMusictitle을 보이게 함
            // binding.txtMusictitle.visibility = if (reservation.musictitle.isEmpty()) View.GONE else View.VISIBLE


            // ischecked 값 바꾸는거 다시 생각하기
            binding.btnMs.setOnCheckedChangeListener(null)
            binding.btnMs.isChecked = reservation.isChecked
            binding.btnMs.setOnCheckedChangeListener { _, isChecked ->
                reservation.isChecked = isChecked
            }
        }

    }


}
