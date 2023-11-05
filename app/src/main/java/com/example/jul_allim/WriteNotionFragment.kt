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

class WriteNotionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWriteNotionBinding.inflate(inflater,container,false)

        binding.btnUpload.setOnClickListener {
            val JK = if(binding.swichJK.isChecked) "Kau" else "Jul"
            var notions: Array<Notion> = arrayOf()
            lifecycleScope.launch {
                launch { notions = getNotions(JK) }.join()
                val txt = binding.writespace.text.toString()
                val indexOfnotion: Int = notions.size
                Log.d("newNotion",indexOfnotion.toString())
                val id = "20231102"+indexOfnotion.toString()

                var data = hashMapOf<String,String>("id" to id,"content" to txt)
                launch {
                    Firebase.database.getReference("Notion").child(JK)
                        .child(indexOfnotion.toString()).setValue(data)
                }.join()
                MainActivity.getInstance()
                    ?.setMainFragment(NotionFragment(),"공지사항")
            }
        }
        return binding.root
    }

}