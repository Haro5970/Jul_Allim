package com.example.jul_allim

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
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

        fun getSelectedDate(binding: DatePicker): String {
            val year = binding.year
            val month = binding.month + 1
            val dayOfMonth = binding.dayOfMonth
            return "${year}${String.format("%02d", month)}${String.format("%02d", dayOfMonth)}"
        }

        val reservations = mutableListOf<Reservation>()
        binding.recTime.layoutManager = LinearLayoutManager(context)
        binding.recTime.adapter = ReservationAdapter(reservations)

        viewModel.reservations.observe(viewLifecycleOwner){ data ->
            val times = arrayOf("09:00~10:00", "10:00~11:00", "11:00~12:00", "12:00~13:00", "13:00~14:00", "14:00~15:00", "15:00~16:00", "16:00~17:00", "17:00~18:00", "18:00~19:00", "19:00~20:00", "20:00~21:00")
            reservations.clear()
            times.forEach { reservet->
                val title = data.find { it.time == reservet }
                title?.let{
                    reservations.add(title)
                }?: run{
                    reservations.add(Reservation(getSelectedDate(binding.pickDate), reservet, ""))
                }
            }
            binding.recTime.adapter = ReservationAdapter(reservations)

            binding.rsvCurrent.text = getSelectedDate(binding.pickDate)+"동방예약현황"
        }


        binding.pickDate.setOnDateChangedListener { datePicker, i, i2, i3 ->
            val selectedDate_ = getSelectedDate(binding.pickDate)
            viewModel.dayChange(selectedDate_)
            Log.d("resDataChange","${selectedDate_}\n${reservations}")

            binding.txtWrite.text.clear()
        }

        binding.btnWrite.setOnClickListener {
            val selectedDate_ = getSelectedDate(binding.pickDate)
            viewModel.newMusictitles((binding.recTime.adapter as ReservationAdapter).updateMusictitles(binding), selectedDate_)
            Log.d("res2","${selectedDate_}\n${selectedDate_}\n${reservations}")
            binding.txtWrite.text.clear()
        }
        return binding.root
    }

}