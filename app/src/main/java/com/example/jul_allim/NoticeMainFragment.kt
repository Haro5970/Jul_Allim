package com.example.jul_allim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNoticeMainBinding
import com.example.jul_allim.viewmodel.NoticeViewModel

class NoticeMainFragment : Fragment() {
    val viewModel: NoticeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoticeMainBinding.inflate(inflater,container,false)

        viewModel.noticeJL.observe(viewLifecycleOwner){
            binding.julList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = NoticeAdapter(it.copyOfRange(0,minOf(7,it.size)),viewModel)
            }
        }
        viewModel.noticeKL.observe(viewLifecycleOwner){
            binding.kauList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = NoticeAdapter(it.copyOfRange(0,minOf(5,it.size)),viewModel)
            }
        }

        binding.btnJul.setOnClickListener{
            MainActivity.getInstance()
                ?.setMainFragment(NoticeListFragment(true),"공지사항")
        }
        binding.btnKau.setOnClickListener{
            MainActivity.getInstance()
                ?.setMainFragment(NoticeListFragment(false),"공지사항")
        }

        binding.btnNew.isVisible = MainActivity.IsAdmin
        binding.btnNew.setOnClickListener{
            MainActivity.getInstance()
                ?.setMainFragment(NoticeWriteFragment(),"새 공지사항")
        }

        return binding.root
    }
}