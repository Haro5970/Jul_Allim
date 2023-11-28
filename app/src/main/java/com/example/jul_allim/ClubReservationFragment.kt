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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ClubReservationFragment : Fragment() {


    var binding: ActivityMainBinding? = null
    lateinit var adapter: ReservationAdapter // 전역 변수로 선언

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

        val startDate = LocalDate.of(2023, 1, 1) // 시작 날짜 설정
        val endDate = LocalDate.of(2023, 12, 31) // 종료 날짜 설정

        val dates = mutableListOf<String>()
        var currentDate = startDate

        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate.format(DateTimeFormatter.BASIC_ISO_DATE))
            currentDate = currentDate.plusDays(1)
        }

        val times = arrayOf("09:00~10:00", "10:00~11:00", "11:00~12:00", "12:00~13:00", "13:00~14:00", "14:00~15:00", "15:00~16:00", "16:00~17:00", "17:00~18:00", "18:00~19:00", "19:00~20:00", "20:00~21:00") // 원하는 시간을 추가하십시오.

        val reservations = mutableListOf<Reservation>()

        for (date in dates) {
            for (time in times) {
                reservations.add(Reservation(date, time, ""))
            }
        }

        val selectedYear = binding.pickDate.year
        val selectedMonth = binding.pickDate.month + 1
        val selectedDay = binding.pickDate.dayOfMonth
        val selectedDate = "${selectedYear}${String.format("%02d", selectedMonth)}${String.format("%02d", selectedDay)}"

        adapter = ReservationAdapter(reservations, selectedDate)

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
            val filteredReservations = reservations.filter { it.day == selectedDate }.toMutableList()
            adapter = ReservationAdapter(filteredReservations, selectedDate)
            binding.recTime.adapter = adapter
        }

        binding.btnWrite.setOnClickListener {
            adapter.updateMusictitles(binding)

            // val enteredTitle = binding.txtWrite.text.toString()

            // reservations.add(Reservation(selectedDate, selectedDate, enteredTitle))

            // val newreservations = adapter.getNewReservation(enteredTitle).toMutableList()
            // this.adapter = ReservationAdapter(newreservations, selectedDate)
            // binding.recTime.adapter = adapter
        }

        return binding.root
    }

}