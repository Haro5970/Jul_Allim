package com.example.jul_allim

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.NotionImgBinding

class NotionIMGAdapter(val imgs: Array<Bitmap>):
    RecyclerView.Adapter<NotionIMGAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = NotionImgBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(imgs[position])
    }

    override fun getItemCount() = imgs.size

    class Holder(private val binding: NotionImgBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind ( img: Bitmap){
            binding.imageView.setImageBitmap(img)
            binding.imageView.setOnClickListener{
                Toast.makeText( binding.root.context,"큰 이미지 보이기",Toast.LENGTH_SHORT).show()
            }
        }
    }
}