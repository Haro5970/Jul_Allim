package com.example.jul_allim

import android.graphics.Bitmap
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ScrollingView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNoticeViewBinding
import com.example.jul_allim.viewmodel.NoticeViewModel

class NoticeViewFragment(val notice: Notice) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoticeViewBinding.inflate(inflater,container,false)
        val viewModel: NoticeViewModel by activityViewModels()

        binding.noticeView.movementMethod = ScrollingMovementMethod.getInstance()
        binding.noticeId.text = notice.id
        binding.noticeView.text = notice.lines
        binding.bigImgView.isVisible = false

        val imgs = MutableLiveData<Array<Bitmap>>(arrayOf())
        viewModel.getNoticeImages(notice.id,imgs)
        imgs.observe(viewLifecycleOwner){
            binding.bigImgView.isVisible = false
            binding.bigImgView.setOnClickListener { binding.bigImgView.isVisible=false }
            if(it.size>0){
                binding.noticeImgs.apply {
                    adapter = NoticeImageAdapter(it) {
                        binding.bigImgView.isVisible = true
                        binding.bigImgView.setImageBitmap(it) }
                    layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }
                }
            }
        }
        return binding.root
    }
}