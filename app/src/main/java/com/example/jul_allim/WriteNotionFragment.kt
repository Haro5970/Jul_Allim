package com.example.jul_allim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.jul_allim.databinding.FragmentNotionBinding
import com.example.jul_allim.databinding.FragmentWriteNotionBinding
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class WriteNotionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWriteNotionBinding.inflate(inflater,container,false)
        val today = SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis())
        binding.btnUpload.setOnClickListener {
            val JK = if(binding.swichJK.isChecked) "Kau" else "Jul"
            var notions: Array<Notion> = arrayOf()
            lifecycleScope.launch {

                launch { notions = getNotions(JK).filter{it.id.startsWith(today)}.toTypedArray() }.join()
                val id = if(notions.size==0) today+"00" else (notions.get(0).id.toInt()+1).toString()
                val txt = binding.writespace.text.toString()
                val data = hashMapOf<String,String>("id" to id,"content" to txt)
                launch {
                    Firebase.database.getReference("Notion").child(JK)
                        .child(notions.size.toString()).setValue(data)
                }.join()
                MainActivity.getInstance()
                    ?.setMainFragment(NotionFragment(),"공지사항")
            }
        }
        return binding.root
    }

}