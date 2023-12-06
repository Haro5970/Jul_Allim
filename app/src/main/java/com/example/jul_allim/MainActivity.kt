package com.example.jul_allim

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.jul_allim.databinding.ActivityMainBinding

class Navbtn(
    val btn: android.widget.ImageButton,
    val frag: Fragment,
    val title: String,
    )

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

        // setMainFragment 메소드를 다른 프레그먼트에서 사용하기 위해 companion객체 생성
    init{
        instance = this
    }
    companion object{ // 다른곳에서 사용할 것들
        private var instance:MainActivity? = null
        var IsAdmin: Boolean = false
        val myname: String = "배별하"
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    lateinit var btn_nav: Array<Navbtn>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            // 화면 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_nav = arrayOf(
            Navbtn(binding.btn1,NoticeMainFragment(),"공지사항"),
            Navbtn(binding.btn2,CalenderFragment(),"캘린더"),
            Navbtn(binding.btn3,ClubReservationFragment(),"동방예약"),
            Navbtn(binding.btn4,MusicListFragment(),"곡리스트"),
            Navbtn(binding.btn5,ProfileFragment(),"프로필") )

        setMainFragment(NoticeMainFragment(),"공지사항")
        lastNav=btn_nav[0]


        btn_nav.forEach { nav->
            nav.btn.setOnClickListener{
                setMainFragment(nav.frag,nav.title)

                btn_nav.forEach {
                    it.btn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))
                }
                nav.btn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
            }
        }

        // 임시 관리자 권한 설정
        var cnt: Int = 0
        binding.Logo.setOnClickListener {
            cnt += 1
            if(cnt >= 5){
                IsAdmin = !IsAdmin
                cnt = 0
                Toast.makeText(this, if(IsAdmin) "관리자 모드가 실행되었습니다." else "관리자 모드가 중기되었습니다.", Toast.LENGTH_SHORT).show()
                setMainFragment(btn_nav[0].frag,btn_nav[0].title)
                btn_nav.forEach {
                    it.btn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))
                }
                btn_nav[0].btn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
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
        binding.textTitle.text = title
    }


    var backPressedTime: Long = 0
    lateinit var lastNav: Navbtn
    override fun onBackPressed() {
        if (binding.mainScreen.getFragment<Fragment>() != lastNav.frag){
            setMainFragment(lastNav.frag, lastNav.title)
            btn_nav.forEach {
                it.btn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))
            }
            lastNav.btn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
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