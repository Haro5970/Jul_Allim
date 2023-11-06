package com.example.jul_allim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jul_allim.databinding.FragmentNotionViewBinding

class NotionViewFragment(val notion: Notion) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNotionViewBinding.inflate(inflater, container, false)
        binding.notionView.text = notion.content
        binding.notionId.text = notion.id
        return binding.root
    }
}