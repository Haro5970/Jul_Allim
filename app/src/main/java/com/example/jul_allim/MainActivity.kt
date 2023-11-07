package com.example.jul_allim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.jul_allim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

        // setMainFragment 메소드를 다른 프레그먼트에서 사용하기 위해 companion객체 생성
    init{
        instance = this
    }
    companion object{
        private var instance:MainActivity? = null
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            // 화면 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setMainFragment(NotionFragment(),"공지사항")
        setContentView(binding.root)



            // 하단바 버튼 클릭시 페이지 이동
        binding.btn1.setOnClickListener {
            setMainFragment(NotionFragment(),"공지사항")
        }
        binding.btn2.setOnClickListener {
            setMainFragment(CalenderFragment(),"캘린더")
        }
        binding.btn3.setOnClickListener {
            setMainFragment(ClubReservationFragment(),"동방예약")
        }

        binding.btn5.setOnClickListener {
            setMainFragment(ProfileFragment(), "프로필 설정")
        }

    }

    // fragment 띄울 프래그먼트, title 상단 제목
    /*
    MainActivity.getInstance()
        ?.setMainFragment(띄울 프래그먼트,화면 이름)
     */
    fun setMainFragment( fragment: Fragment,title: String){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.mainScreen.id,fragment)
            commit()
        }
        Log.d("setMainFragment",title)
        binding.textTitle.text = title
    }

    // 캘린더뷰


}
