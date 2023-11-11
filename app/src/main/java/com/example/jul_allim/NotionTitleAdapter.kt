package com.example.jul_allim

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.ActivityMainBinding
import com.example.jul_allim.databinding.NotionPreviewBinding
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class NotionTitleAdapter(val notions: Array<Notion>,val JK: String)
    : RecyclerView.Adapter<NotionTitleAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = NotionPreviewBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(notions[position],JK)
    }

    override fun getItemCount() = notions.size

    class Holder(private val binding: NotionPreviewBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind ( notion: Notion,JK: String){

            binding.notionContent.text = notion.preview
            binding.notionId.text = notion.id


            binding.root.setOnClickListener{
                toNotion(notion)
            }
            binding.root.setOnLongClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    removeNotion(notion,JK)
                }

                MainActivity.getInstance()
                    ?.setMainFragment(NotionFragment(),"공지사항")

                true

            }
        }
    }

}