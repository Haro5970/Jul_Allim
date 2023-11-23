package com.example.jul_allim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jul_allim.databinding.ActivityMainBinding
import com.example.jul_allim.databinding.FragmentClubReservateBinding

class MusicListFragment : Fragment() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentClubReservateBinding.inflate(inflater,container,false)

        val musics = arrayOf(
            Music("푸르던", "윤서진", "유승빈","임세현", "석효준", "김수빈"),
        )
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music_list, container, false)
    }


}