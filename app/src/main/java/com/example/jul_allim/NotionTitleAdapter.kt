package com.example.jul_allim

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.NotionTitleListBinding

class NotionTitleAdapter(val notions: Array<Notion>)
    : RecyclerView.Adapter<NotionTitleAdapter.Hoder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hoder {
        val binding = NotionTitleListBinding.inflate(LayoutInflater.from(parent.context))
        return Hoder(binding)
    }

    override fun onBindViewHolder(holder: Hoder, position: Int) {
        holder.bind(notions[position])
    }

    override fun getItemCount() = notions.size

    class Hoder(private val binding: NotionTitleListBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind ( notion: Notion){
            binding.textView.text = " ● ${notion.content.subSequence(0,minOf(10,notion.content.length))}..."

            binding.root.setOnClickListener{
                Toast.makeText(binding.root.context,
                    "${notion.content} 로 넘어가기 미구현",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

}