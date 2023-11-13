package com.example.jul_allim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.jul_allim.databinding.FragmentNotionBinding
import com.example.jul_allim.databinding.FragmentWriteNotionBinding
import com.example.jul_allim.viewmodel.NotionViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class WriteNotionFragment : Fragment() {
    val viewmodel: NotionViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWriteNotionBinding.inflate(inflater,container,false)

        binding.btnUpload.setOnClickListener {

            viewmodel.addNotions(binding.writespace.text.toString(),
                if(binding.swichJK.isChecked) "Kau" else "Jul" )

            MainActivity.getInstance()
                ?.setMainFragment(NotionFragment(),"공지사항")
        }

        return binding.root
    }

}