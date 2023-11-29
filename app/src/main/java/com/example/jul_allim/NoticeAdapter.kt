package com.example.jul_allim

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.NoticePreviewBinding
import com.example.jul_allim.viewmodel.NoticeViewModel

class NoticeAdapter(val notices: Array<Notice>,val viewModel: NoticeViewModel ): RecyclerView.Adapter<NoticeAdapter.Holder>(){
    class Holder(val binding: NoticePreviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(notice: Notice,viewModel: NoticeViewModel) {
            binding.noticeId.text = notice.id
            binding.noticeLines.text = notice.lines.subSequence(0,minOf(15,notice.lines.length))

            binding.root.setOnClickListener{
                MainActivity.getInstance()
                    ?.setMainFragment(NoticeViewFragment(notice),"공지사항")
            }

            if(MainActivity.IsAdmin){
                binding.root.setOnLongClickListener {
                    viewModel.removeNotice(notice.id)
                    true
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = NoticePreviewBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount() = notices.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(notices[position],viewModel)
    }

}