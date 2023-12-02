package com.example.jul_allim

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.jul_allim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var lastFragment: Array<Any>

        // setMainFragment 메소드를 다른 프레그먼트에서 사용하기 위해 companion객체 생성
    init{
        instance = this
    }
    companion object{
        private var instance:MainActivity? = null
        var IsAdmin: Boolean = false
        val myname: String = "배별하"
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            // 화면 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setMainFragment(NoticeMainFragment(),"공지사항")
        lastFragment = arrayOf( NoticeMainFragment(),"공지사항",1)
        setUnderbarColor(1)
        setContentView(binding.root)

            // 하단바 버튼 클릭시 페이지 이동
        binding.btn1.setOnClickListener {
            setMainFragment(NoticeMainFragment(),"공지사항")
            lastFragment = arrayOf( NoticeMainFragment(),"공지사항",1)
            setUnderbarColor(1)
        }
        binding.btn2.setOnClickListener {
            setMainFragment(CalenderFragment(),"캘린더")
            lastFragment = arrayOf( CalenderFragment(),"캘린더",2)
            setUnderbarColor(2)
        }
        binding.btn3.setOnClickListener {
            setMainFragment(ClubReservationFragment(),"동방예약")
            lastFragment = arrayOf( ClubReservationFragment(),"동방예약",3)
            setUnderbarColor(3)
        }
        binding.btn4.setOnClickListener {
            setMainFragment(MusicListFragment(),"곡리스트")
            lastFragment = arrayOf( MusicListFragment(),"곡리스트",4)
            setUnderbarColor(4)
        }
        binding.btn5.setOnClickListener {
            setMainFragment(ProfileFragment(), "프로필 설정")
            lastFragment = arrayOf( ProfileFragment(),"프로필 설정",5)
            setUnderbarColor(5)
        }

        // 임시 관리자 권한 설정
        var cnt: Int = 0
        binding.Logo.setOnClickListener {
            cnt += 1
            if(cnt >= 5){
                IsAdmin = !IsAdmin
                cnt = 0
                setMainFragment(lastFragment[0] as Fragment,lastFragment[1] as String)
            }
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
    // 하단버튼 색변경
    fun setUnderbarColor( btnN: Int){

        val btn_arr=arrayOf(binding.btn1,binding.btn2,binding.btn3,binding.btn4,binding.btn5)
        for( i:Int in 1..5){
            if(btnN==i){
                btn_arr[i-1].imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
            }
            else{
                btn_arr[i-1].imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))
            }
        }
    }


    var backPressedTime: Long = 0
    override fun onBackPressed() {
        if (binding.mainScreen.getFragment<Fragment>() != lastFragment[0]){
            setMainFragment(lastFragment[0] as Fragment, lastFragment[1] as String)
            setUnderbarColor(lastFragment[2] as Int)
        }
        else{
            if (System.currentTimeMillis() - backPressedTime < 2000) {
                super.onBackPressed()
                return
            }
            Toast.makeText(this, "한번 더 클릭 시 종료됩니다.", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }
    }
}



/*
list {r1,ㄱ2,ㄱ3

list.foreach{res->
    ref.child(res.day).child(res.time).setValue(res.titile)

*
* */