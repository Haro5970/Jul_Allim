package com.example.jul_allim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNotionBinding
import kotlinx.coroutines.launch

class NotionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 생성시 할일
        val binding = FragmentNotionBinding.inflate(inflater,container,false)


        var jul_notions: Array<Notion> = arrayOf()
        var kau_notions: Array<Notion> = arrayOf()
        lifecycleScope.launch {
            launch {
                // 공지 받아오기
                jul_notions = getNotions("Jul")
                kau_notions = getNotions("Kau")
            }.join()

            // 줄울림 공지 리스트
            binding.julList.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = NotionTitleAdapter(jul_notions.copyOfRange(0, minOf(7, jul_notions.size)),"Jul")
            }

            // 학교 공지 리스트
            binding.kauList.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = NotionTitleAdapter(kau_notions.copyOfRange(0, minOf(5, kau_notions.size)),"Kau")
            }


            // +버튼 클릭
            binding.btnJul.setOnClickListener {
                MainActivity.getInstance()
                    ?.setMainFragment(NotionListFragment("줄울림 공지", "Jul"), "공지사항")
            }
            binding.btnKau.setOnClickListener {
                MainActivity.getInstance()
                    ?.setMainFragment(NotionListFragment("학교 공지", "Kau"), "공지사항")
            }

            // 큰+버튼 클릭
            binding.btnNew.setOnClickListener {
                MainActivity.getInstance()
                    ?.setMainFragment(WriteNotionFragment(),"새 공지사항")
            }
        }


        return binding.root
    }
}