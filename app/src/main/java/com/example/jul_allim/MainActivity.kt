package com.example.jul_allim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.jul_allim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            // 화면 초기화 부분
        binding = ActivityMainBinding.inflate(layoutInflater)
        setMainFragment(NotionFragment())
        setContentView(binding.root)

            // 하단바 버튼 클릭시 페이지 이동
        binding.btn1.setOnClickListener {
            binding.textTitle.text = "공지사항"
            setMainFragment(NotionFragment())
        }
        binding.btn2.setOnClickListener {
            binding.textTitle.text = "캘린더"
            setMainFragment(CalenderFragment())
        }


    }

    fun setMainFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.mainScreen.id,fragment)
            commit()
        }
    }
}
