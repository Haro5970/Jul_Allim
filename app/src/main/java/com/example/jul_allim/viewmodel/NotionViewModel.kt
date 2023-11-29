package com.example.jul_allim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat

/*
class NotionViewModel: ViewModel() {
    private val repository = NotionRepository()

    private val NotionsJ = MutableLiveData<Array<Notion>>(arrayOf())
    private val NotionsK = MutableLiveData<Array<Notion>>(arrayOf())

    val notionJ: LiveData<Array<Notion>> get() = NotionsJ
    val notionK: LiveData<Array<Notion>> get() = NotionsK

    init{
        repository.observeNotion(NotionsJ,NotionsK)
    }

    fun getNotions(JK: String): Array<Notion>? = if(JK=="Jul") NotionsJ.value else NotionsK.value

    fun addNotions(content: String, JK: String, imgs: Array<String>){
        val today = SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis())
        val notionlist = getNotions(JK)!!.filter{
            it.id.startsWith(JK[0]+today)
        }
        val id = if(notionlist.size==0) JK[0]+today+"00"
                    else JK[0]+(notionlist[0].id.substring(1).toInt()+1).toString()

        repository.addNotion(id,content,imgs)
    }

    fun removeNotion(notion: Notion){
        repository.removeNotion(notion.id)
    }

}
*/