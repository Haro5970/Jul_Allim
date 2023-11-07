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



    /*val reservations = arrayOf(
        Reservation("20231107", "9:00~10:00", "푸르던"),
        Reservation("20231108", "10:00~11:00", "푸르던"),
        Reservation("20231109", "16:00~18:00", "푸르던"),
        )

     */

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

        var reservations: Array<Reservation> = arrayOf()
        lifecycleScope.launch {

        }

        binding.pickDate.setOnDateChangedListener { _, year, month, dayOfMonth ->
            binding.rsvCurrent.text = "${year}년 ${month + 1}월 ${dayOfMonth}일 동방 예약 현황"
        }
        binding.recTime.adapter

        return binding.root
    }

}

