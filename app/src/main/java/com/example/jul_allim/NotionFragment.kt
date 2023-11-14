package com.example.jul_allim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNotionBinding
import com.example.jul_allim.viewmodel.NotionViewModel
import kotlinx.coroutines.launch

class NotionFragment : Fragment() {

    val viewmodel: NotionViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 생성시 할일
        val binding = FragmentNotionBinding.inflate(inflater, container, false)


        viewmodel.notionJ.observe(viewLifecycleOwner) {
            binding.julList.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = NotionTitleAdapter(
                    viewmodel.notionJ.value!!.copyOfRange(
                        0,
                        minOf(7, viewmodel.notionJ.value!!.size)
                    ), "Jul"
                )
            }
        }

        viewmodel.notionK.observe(viewLifecycleOwner) {
            binding.kauList.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = NotionTitleAdapter(
                    viewmodel.notionK.value!!.copyOfRange(
                        0,
                        minOf(5, viewmodel.notionK.value!!.size)
                    ), "Kau"
                )
            }
        }


        // +버튼
        binding.btnJul.setOnClickListener {
            MainActivity.getInstance()
                ?.setMainFragment(NotionListFragment("줄울림 공지", "Jul"), "공지사항")
        }
        binding.btnKau.setOnClickListener {
            MainActivity.getInstance()
                ?.setMainFragment(NotionListFragment("학교 공지", "Kau"), "공지사항")
        }

        // 큰+버튼
        if(!MainActivity.IsAdmin){
            binding.btnNew.isInvisible = true
        }
        binding.btnNew.setOnClickListener {
            MainActivity.getInstance()
                ?.setMainFragment(WriteNotionFragment(), "새 공지사항")

        }


        return binding.root
    }
}