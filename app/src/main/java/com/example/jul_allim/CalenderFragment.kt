package com.example.jul_allim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jul_allim.databinding.FragmentCalenderBinding
import java.text.SimpleDateFormat

class CalenderFragment : Fragment() {

    private var _binding: FragmentCalenderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalenderBinding.inflate(inflater, container, false)
        //binding.tv.text = SimpleDateFormat("yyyy년 MM월 dd일").format(binding.cal.date)
        binding.cal.setOnDateChangeListener { _, year, month, dayOfMonth ->
            binding.tv.text = "${year}년 ${month + 1}월 ${dayOfMonth}일"
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
