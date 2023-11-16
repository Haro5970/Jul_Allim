package com.example.jul_allim

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNotionViewBinding

class NotionViewFragment(val notion: Notion) : Fragment() {
    lateinit var binding: FragmentNotionViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotionViewBinding.inflate(inflater, container, false)
        binding.notionView.text = notion.content
        binding.notionId.text = notion.id
        if(notion.imgs.size!=0){
            binding.notionImgs.apply {
                adapter = NotionIMGAdapter(notion.imgs,setBigImg)
                layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }
            }
        }
        binding.bigImgView.isVisible = false
        binding.bigImgView.setOnClickListener { binding.bigImgView.isVisible=false }


        return binding.root
    }

    val setBigImg: (Bitmap) -> (Unit) = {
        binding.bigImgView.isVisible = true
        binding.bigImgView.setImageBitmap(it)
    }

}