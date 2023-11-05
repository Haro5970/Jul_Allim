package com.example.jul_allim

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.NotionPreviewBinding

class NotionTitleAdapter(val notions: Array<Notion>)
    : RecyclerView.Adapter<NotionTitleAdapter.Hoder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hoder {
        val binding = NotionPreviewBinding.inflate(LayoutInflater.from(parent.context))
        return Hoder(binding)
    }

    override fun onBindViewHolder(holder: Hoder, position: Int) {
        holder.bind(notions[position])
    }

    override fun getItemCount() = notions.size

    class Hoder(private val binding: NotionPreviewBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind ( notion: Notion){

            binding.notionContent.text = notion.preview
            binding.notionId.text = notion.id


            binding.root.setOnClickListener{
                toNotion(notion)
            }
        }
    }

}