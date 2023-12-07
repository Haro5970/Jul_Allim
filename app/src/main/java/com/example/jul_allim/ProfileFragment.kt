package com.example.jul_allim

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.jul_allim.databinding.FragmentProfileBinding
import com.example.jul_allim.viewmodel.StudentVM
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Base64


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding ?= null
    private val binding get() = _binding!!

    //어댑터 선언
    private lateinit var adapter: StudentsAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(StudentVM::class.java) }

    //텍스트 수정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        context?.let {
            adapter = StudentsAdapter()
        }

        // 리사이클러뷰 레이아웃매니저, 어댑터 설정
        binding.recStudents.layoutManager = LinearLayoutManager(context)
        binding.recStudents.adapter = adapter

        //데이터 반영
        observeData()

        //수정하기 페이지로 넘어감
        binding.editbutton.setOnClickListener {
            MainActivity.getInstance()
                ?.setMainFragment(ProfileEditFragment(binding.imageView14.drawable.toBitmap()), "회원 정보 수정")
        }

        return binding.root
    }


    private fun observeData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            val myprofile = it.find {
                it.name== MainActivity.myname
            }

            // Update the UI with the data from myprofile
            myprofile?.let { profile ->
                binding.txtname.text = profile.name
                binding.txtnum.text = profile.id
                binding.txtss.text = profile.session
                binding.txtline.text = profile.line
                // Assuming imageView14 is an ImageView and profile.image is a Bitmap
                binding.imageView14.setImageBitmap(profile.bitmap())
            }

            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }


}
