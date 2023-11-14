package com.example.jul_allim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.NotionPreviewBinding
import com.example.jul_allim.viewmodel.NotionViewModel

class NotionTitleAdapter(var notions: Array<Notion>,val JK: String)
    : RecyclerView.Adapter<NotionTitleAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = NotionPreviewBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(notions[position])
    }

    override fun getItemCount() = notions.size

    class Holder(private val binding: NotionPreviewBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind ( notion: Notion){

            binding.notionContent.text = notion.preview
            binding.notionId.text = notion.id


            binding.root.setOnClickListener{
                toNotion(notion)
            }
            if(MainActivity.IsAdmin){
                binding.root.setOnLongClickListener {
                    NotionViewModel().removeNotion(notion)

                    true

                }
            }
        }
    }

}