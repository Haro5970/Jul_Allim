package com.example.jul_allim.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jul_allim.Notice
import com.example.jul_allim.repository.NoticeRepository
import java.text.SimpleDateFormat

class NoticeViewModel: ViewModel() {
    private val repository = NoticeRepository()

    private val noticeK_list = MutableLiveData<Array<Notice>>(arrayOf())
    private val noticeJ_list = MutableLiveData<Array<Notice>>(arrayOf())
    val noticeJL: LiveData<Array<Notice>> get() = noticeJ_list
    val noticeKL: LiveData<Array<Notice>> get() = noticeK_list

    init{
        repository.observeNoticeList(noticeJ_list,noticeK_list)
    }


    fun getNoticeImages(id: String,liveData: MutableLiveData<Array<Bitmap>>) {
        repository.getImages(id,liveData)
    }
    fun newNotice(IsJul: Boolean,lines: String, images: Array<Bitmap>) {
        val today = SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis())
        val noticelist = (if(IsJul) noticeJL.value!! else noticeKL.value!!).filter{
            it.id.startsWith( (if(IsJul) "J" else "K") +today)
        }
        val id = if(noticelist.size==0) (if(IsJul) "J" else "K")+today+"00"
                else (if(IsJul) "J" else "K")+(noticelist[0].id.substring(1).toInt()+1).toString()

        repository.newNotice(id,lines,images)
    }

    fun removeNotice(id: String) {
        repository.removeNotice(id)
    }

}