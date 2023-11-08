package com.example.jul_allim

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.ActivityMainBinding
import com.example.jul_allim.databinding.FragmentCalenderBinding
import com.example.jul_allim.databinding.FragmentClubReservateBinding
import com.example.jul_allim.databinding.FragmentNotionBinding
import kotlinx.coroutines.launch

class ClubReservationFragment : Fragment() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentClubReservateBinding.inflate(inflater,container,false)

        val reservations = arrayOf(
            Reservation("20231107", "09:00~10:00", "푸르던"),
            Reservation("20231108", "10:00~11:00", "푸르던"),
            Reservation("20231109", "11:00~12:00", "푸르던"),
            Reservation("20231109", "12:00~13:00", "푸르던"),
            Reservation("20231109", "13:00~14:00", ""),
            Reservation("20231109", "14:00~15:00", ""),
            Reservation("20231109", "15:00~16:00", "푸르던"),
            Reservation("20231109", "16:00~17:00", "푸르던"),
            Reservation("20231109", "17:00~18:00", "푸르던"),
            Reservation("20231109", "18:00~19:00", ""),
            Reservation("20231109", "19:00~20:00", ""),
            Reservation("20231109", "20:00~21:00", ""),
        )
        val selectedYear = binding.pickDate.year
        val selectedMonth = binding.pickDate.month + 1 // DatePicker의 month는 0부터 시작하므로 1을 더해줍니다.
        val selectedDay = binding.pickDate.dayOfMonth

        val selectedDate = "${selectedYear}${String.format("%02d", selectedMonth)}${String.format("%02d", selectedDay)}"

        val adapter = ReservationAdapter(reservations, selectedDate)

        binding.recTime.adapter = adapter
        binding.recTime.layoutManager = LinearLayoutManager(context)

        binding.pickDate.setOnDateChangedListener { _, year, month, dayOfMonth ->
            val selectedDate = "${year}${String.format("%02d", month + 1)}${String.format("%02d", dayOfMonth)}"
            val filteredReservations = reservations.filter { it.day == selectedDate }.toTypedArray()
            adapter.reservations = filteredReservations
            adapter.notifyDataSetChanged()
        }


        //날짜에 맞는거 뜨게 어케함!!
        /*binding.pickDate.setOnDateChangedListener { _, year, month, dayOfMonth ->
            val selectedDate = "${year}${String.format("%02d", month + 1)}${String.format("%02d", dayOfMonth)}"
            val filteredReservations = reservations.filter { it.day == selectedDate }.toTypedArray()
            adapter.day = filteredReservations
            adapter.notifyDataSetChanged()
        }

         */

        binding.btnCh.setOnClickListener {
            val year = binding.pickDate.year
            val month = binding.pickDate.month
            val dayOfMonth = binding.pickDate.dayOfMonth
            binding.rsvCurrent.text = "${year}년 ${month + 1}월 ${dayOfMonth}일 동방 예약 현황"
            binding.rsvCurrent.visibility = View.VISIBLE
            binding.recTime.visibility = View.VISIBLE

            val selectedDate = "${year}${String.format("%02d", month + 1)}${String.format("%02d", dayOfMonth)}"
            val filteredReservations = reservations.filter { it.day == selectedDate }.toTypedArray()
            val adapter = ReservationAdapter(filteredReservations, selectedDate)
            binding.recTime.adapter = adapter
        }


        return binding.root
    }

}

