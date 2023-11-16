package com.example.jul_allim

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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
        if(notion.imgs.size!=0){
            binding.notionImgs.apply {
                adapter = NotionIMGAdapter(notion.imgs)
                layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }
            }
        }
        return binding.root
    }
}