package com.example.jul_allim

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jul_allim.databinding.FragmentMusicListBinding
import com.example.jul_allim.databinding.ListMusicBinding

class MusiclistAdapter(var musiclists: ArrayList<Music>) : RecyclerView.Adapter<MusiclistAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusiclistAdapter.Holder {
        val binding = ListMusicBinding.inflate(LayoutInflater.from(parent.context))
        return MusiclistAdapter.Holder(binding)
    }
    override fun getItemCount() = musiclists.size

    override fun onBindViewHolder(holder: MusiclistAdapter.Holder, position: Int) {
        holder.bind(musiclists[position])
    }

    class Holder(private val binding: ListMusicBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(music: Music) {
            binding.txtTitle.text = music.title_
            binding.txtVocal.text = music.vocal
            binding.txtPiano.text = music.piano
            binding.txtGuitar.text = music.guitar
            binding.txtBass.text = music.bass
            binding.txtDrum.text = music.drum
            binding.txtOther.text = music.other
        }
    }
}