package com.example.jul_allim

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.NoticeImgBinding


class NoticeImageAdapter(val imgs: Array<Bitmap>, val operation:((Bitmap)->Unit)? =null):
    RecyclerView.Adapter<NoticeImageAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = NoticeImgBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(imgs[position],operation)
    }

    override fun getItemCount() = imgs.size

    class Holder(private val binding: NoticeImgBinding) : RecyclerView.ViewHolder(binding.root){
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