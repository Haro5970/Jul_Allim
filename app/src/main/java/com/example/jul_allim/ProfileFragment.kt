package com.example.jul_allim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentProfileBinding
import com.example.jul_allim.viewmodel.StudentVM

class ProfileFragment : Fragment() {

    //어댑터 선언
    private lateinit var adapter: StudentsAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(StudentVM::class.java) }

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        context?.let {
            adapter = StudentsAdapter()
        }

        // 리사이클러뷰 레이아웃매니저, 어댑터 설정
        binding.recStudents.layoutManager = LinearLayoutManager(context)
        binding.recStudents.adapter = adapter

        //데이터 반영
        observeData()

        //수정하기
        binding.editbutton.setOnClickListener {
            MainActivity.getInstance()
                ?.setMainFragment(ProfileEditFragment(), "회원 정보 수정")
        }

        return binding.root
    }

    private fun observeData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}
