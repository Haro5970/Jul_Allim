package com.example.jul_allim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNoticeListBinding
import com.example.jul_allim.viewmodel.NoticeViewModel

class NoticeListFragment(val IsJul: Boolean) : Fragment() {
    val viewModel: NoticeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoticeListBinding.inflate(inflater,container,false)

        binding.noticeTitle.text = if(IsJul) "줄울림 공지" else "학교 공지"

        (if(IsJul) viewModel.noticeJL else viewModel.noticeKL)
        .observe(viewLifecycleOwner){
                binding.noticeList.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = NoticeAdapter(it,viewModel)
                }
        }

        return binding.root
    }
}