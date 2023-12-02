package com.example.jul_allim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jul_allim.Music
import com.example.jul_allim.Reservation
import com.example.jul_allim.repository.MusiclistRepository

class MusiclistViewModel : ViewModel() {
    private val repository = MusiclistRepository()
    private val music_list = MutableLiveData<ArrayList<Music>>(arrayListOf())
    val musics: LiveData<ArrayList<Music>> get() = music_list

    init{
        repository.observeMusic(music_list)
    }

    fun newMusics(music: Music){
        repository.newMusic(music)// 데이터베이스에 업데이트
    }
}