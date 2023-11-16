package com.example.jul_allim

import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNotionListBinding
import com.example.jul_allim.viewmodel.NotionViewModel
import com.google.android.gms.common.api.internal.LifecycleFragment
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.database.values
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.reflect.typeOf

class NotionListFragment(val title: String, val Jul_Kau: String) : Fragment() {
    val viewmodel: NotionViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNotionListBinding.inflate(inflater, container, false)

        val observeNotions = if(Jul_Kau=="Jul") viewmodel.notionJ else viewmodel.notionK
        observeNotions.observe(viewLifecycleOwner){
            binding.notionList.apply{
                adapter = NotionTitleAdapter(viewmodel.notionJ.value!!,Jul_Kau)
                layoutManager = LinearLayoutManager(binding.root.context)
            }
        }
        binding.notionTitle.text = title

        return binding.root

    }

}