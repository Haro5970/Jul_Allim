package com.example.jul_allim

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.NotionImgBinding

class NotionIMGAdapter(val imgs: Array<Bitmap>, val operation:((Bitmap)->Unit)? =null):
    RecyclerView.Adapter<NotionIMGAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = NotionImgBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(imgs[position],operation)
    }

    override fun getItemCount() = imgs.size

    class Holder(private val binding: NotionImgBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind (img: Bitmap, operation: ((Bitmap) -> Unit)?){
            binding.imageView.setImageBitmap(img)
            binding.imageView.setOnClickListener{
                if(operation!=null) {
                    operation(img)
                }
            }
        }
    }
}