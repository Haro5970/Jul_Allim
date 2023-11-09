package com.example.jul_allim

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.ActivityMainBinding
import com.example.jul_allim.databinding.FragmentClubReservateBinding


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

        //
        val reservations = arrayOf(
            Reservation("20231107", "09:00~10:00", "푸르던"),
            Reservation("20231107", "10:00~11:00", "푸르던"),
            Reservation("20231107", "11:00~12:00", "기댈곳"),
            Reservation("20231107", "12:00~13:00", "기댈곳"),
            Reservation("20231107", "13:00~14:00", ""),
            Reservation("20231107", "14:00~15:00", ""),
            Reservation("20231107", "15:00~16:00", "wheniwasyourman"),
            Reservation("20231107", "16:00~17:00", "wheniwasyourman"),
            Reservation("20231107", "17:00~18:00", ""),
            Reservation("20231107", "18:00~19:00", ""),
            Reservation("20231107", "19:00~20:00", ""),
            Reservation("20231107", "20:00~21:00", ""),

            Reservation("20231108", "09:00~10:00", ""),
            Reservation("20231108", "10:00~11:00", ""),
            Reservation("20231108", "11:00~12:00", ""),
            Reservation("20231108", "12:00~13:00", "살아가는거야"),
            Reservation("20231108", "13:00~14:00", "살아가는거야"),
            Reservation("20231108", "14:00~15:00", ""),
            Reservation("20231108", "15:00~16:00", "안티프리즈"),
            Reservation("20231108", "16:00~17:00", "안티프리즈"),
            Reservation("20231108", "17:00~18:00", ""),
            Reservation("20231108", "18:00~19:00", ""),
            Reservation("20231108", "19:00~20:00", ""),
            Reservation("20231108", "20:00~21:00", ""),

            Reservation("20231109", "09:00~10:00", "편지"),
            Reservation("20231109", "10:00~11:00", "편지"),
            Reservation("20231109", "11:00~12:00", ""),
            Reservation("20231109", "12:00~13:00", ""),
            Reservation("20231109", "13:00~14:00", ""),
            Reservation("20231109", "14:00~15:00", ""),
            Reservation("20231109", "15:00~16:00", "사랑하기 때문에"),
            Reservation("20231109", "16:00~17:00", "사랑하기 때문에"),
            Reservation("20231109", "17:00~18:00", ""),
            Reservation("20231109", "18:00~19:00", ""),
            Reservation("20231109", "19:00~20:00", ""),
            Reservation("20231109", "20:00~21:00", ""),
        )
        val selectedYear = binding.pickDate.year
        val selectedMonth = binding.pickDate.month + 1
        val selectedDay = binding.pickDate.dayOfMonth
        val selectedDate = "${selectedYear}${String.format("%02d", selectedMonth)}${String.format("%02d", selectedDay)}"

        val adapter = ReservationAdapter(reservations, selectedDate)

        // 리사이클러뷰 어뎁터
        binding.recTime.adapter = adapter
        // 아이템을 수직 방향으로 배치
        binding.recTime.layoutManager = LinearLayoutManager(context)

        binding.btnCh.setOnClickListener {
            val year = binding.pickDate.year
            val month = binding.pickDate.month
            val dayOfMonth = binding.pickDate.dayOfMonth
            //
            binding.rsvCurrent.text = "${year}년 ${month + 1}월 ${dayOfMonth}일 동방 예약 현황"
            binding.rsvCurrent.visibility = View.VISIBLE
            binding.recTime.visibility = View.VISIBLE

            // 20231101 형식으로 날짜 selectedDate에 저장하고 adapter에 비교함수 따라서 보이게 or 안보이게
            val selectedDate = "${year}${String.format("%02d", month + 1)}${String.format("%02d", dayOfMonth)}"
            val filteredReservations = reservations.filter { it.day == selectedDate }.toTypedArray()
            val adapter = ReservationAdapter(filteredReservations, selectedDate)
            binding.recTime.adapter = adapter
        }


        return binding.root
    }

}

