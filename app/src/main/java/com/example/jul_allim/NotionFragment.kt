package com.example.jul_allim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNotionBinding

class NotionFragment : Fragment() {
    val jul_notions = arrayOf(
        Notion("0","항공우주 주말 캠프 중등부 멘토 모집"),
        Notion("1","줄울림 36기 회장단 모집"),
        Notion("2","정기공연 오디션 날짜 공지"),
        Notion("3","후드집업 수요조사"),
        Notion("4","줄울림 버스킹 수요조사"),
        Notion("5","2차 오디션 뒷풀이는 참여율이 저조해 취소합니다!"),
        Notion("6","동방합주 예약이 올라올 예정입니다."),
        Notion("7","2차 오디션 일정 공지"),
        Notion("8","가을 엠티 입금 안내")
    )
    val kau_notions = arrayOf(
        Notion("9","예비군 학과별 날짜 안내"),
        Notion("10","중간고사 푸드트럭 안내"),
        Notion("11","모여라 항대의숲!"),
        Notion("12","자봉단 모집")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 생성시 할일
        val binding = FragmentNotionBinding.inflate(inflater,container,false)
            // 줄울림 공지 리스트
        binding.julList.layoutManager = LinearLayoutManager(this.context)
        binding.julList.adapter = NotionTitleAdapter(jul_notions.copyOfRange(0,minOf(5,jul_notions.size)))

            // 학교 공지 리스트
        binding.kauList.layoutManager = LinearLayoutManager(this.context)
        binding.kauList.adapter = NotionTitleAdapter(kau_notions.copyOfRange(0,minOf(3,kau_notions.size)))

        binding.btnJul.setOnClickListener {
            MainActivity.getInstance()?.setMainFragment(NotionListFragment("줄울림 공지","Jul"),"공지사항")
        }

        binding.btnKau.setOnClickListener {
            MainActivity.getInstance()?.setMainFragment(NotionListFragment("학교 공지","Kau"),"공지사항")
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    fun toNotion(notion: Notion){
        MainActivity.getInstance()?.setMainFragment(NotionViewFragment(notion),"공지사항")
    }

}