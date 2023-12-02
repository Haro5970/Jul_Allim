package com.example.jul_allim

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.ListStudentsBinding

class StudentsAdapter : RecyclerView.Adapter<StudentsAdapter.Holder>(){
    private var studentList = mutableListOf<Student>()

    fun setListData(data:MutableList<Student>){
        studentList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListStudentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount()= studentList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(studentList[position])
    }

    inner class Holder(private val binding: ListStudentsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(student: Student){
            binding.txtName.text = student.name
            binding.txtId.text= student.id
            binding.txtSession.text = student.session
            binding.txtOneline.text = student.line

            binding.imageView.setImageBitmap(student.bitmap())
            }
        }
    }

