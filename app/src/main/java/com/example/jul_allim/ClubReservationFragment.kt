package com.example.jul_allim

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.ActivityMainBinding
import com.example.jul_allim.databinding.FragmentClubReservateBinding
import com.example.jul_allim.viewmodel.ReservationViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ClubReservationFragment : Fragment() {

    val viewModel: ReservationViewModel by activityViewModels()

    lateinit var adapter: ReservationAdapter // 전역 변수로 선언

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

        val selectedYear = binding.pickDate.year
        val selectedMonth = binding.pickDate.month + 1
        val selectedDay = binding.pickDate.dayOfMonth
        val selectedDate = "${selectedYear}${String.format("%02d", selectedMonth)}${String.format("%02d", selectedDay)}"

        var reservations = mutableListOf<Reservation>()
        adapter = ReservationAdapter(reservations, selectedDate)

        // 리사이클러뷰 어뎁터
        binding.recTime.adapter = adapter
        // 아이템을 수직 방향으로 배치
        binding.recTime.layoutManager = LinearLayoutManager(context)

        viewModel.reservations.observe(viewLifecycleOwner){ data ->
            val times = arrayOf("09:00~10:00", "10:00~11:00", "11:00~12:00", "12:00~13:00", "13:00~14:00", "14:00~15:00", "15:00~16:00", "16:00~17:00", "17:00~18:00", "18:00~19:00", "19:00~20:00", "20:00~21:00") // 원하는 시간을 추가하십시오.
            reservations.clear()
            times.forEach { t->
                val r = data.find { it.time == t }
                if(r != null){
                    reservations.add(r)
                }
                else{
                    reservations.add(Reservation(selectedDate, t, ""))
                }
            }
        }

        binding.pickDate.setOnDateChangedListener { datePicker, i, i2, i3 ->
            val year = binding.pickDate.year
            val month = binding.pickDate.month+1
            val dayOfMonth = binding.pickDate.dayOfMonth
            val selectedDate_ = "${year}${String.format("%02d", month)}${String.format("%02d", dayOfMonth)}"

            viewModel.day = selectedDate_
            viewModel.dayChange(selectedDate_)

            Log.d("resDataChange","${viewModel.day}\n${reservations}")
        }

        binding.btnCh.setOnClickListener {
            val year = binding.pickDate.year
            val month = binding.pickDate.month+1
            val dayOfMonth = binding.pickDate.dayOfMonth
            val selectedDate_ = "${year}${String.format("%02d", month)}${String.format("%02d", dayOfMonth)}"

            Log.d("res","${selectedDate_}\n${viewModel.day}\n${reservations}")

            //
            binding.rsvCurrent.text = "${year}년 ${month}월 ${dayOfMonth}일 동방 예약 현황"
            binding.rsvCurrent.visibility = View.VISIBLE
            binding.recTime.visibility = View.VISIBLE
            // 20231101 형식으로 날짜 selectedDate에 저장하고 adapter에 비교함수 따라서 보이게 or 안보이게
            adapter = ReservationAdapter(reservations, selectedDate)
            binding.recTime.adapter = adapter
        }

        binding.btnWrite.setOnClickListener {
            viewModel.newMusictitles(adapter.updateMusictitles(binding))
        }

        return binding.root
    }

}