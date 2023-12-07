package com.example.jul_allim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentMusicListBinding
import com.example.jul_allim.viewmodel.MusiclistViewModel

class MusicListFragment : Fragment() {

    val viewModel: MusiclistViewModel by activityViewModels()

    lateinit var adapter: MusiclistAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMusicListBinding.inflate(inflater,container,false)

        viewModel.musics.observe(viewLifecycleOwner) {
            val list = it.clone() as ArrayList<Music>
            list.add(0,Music("곡제목","보컬","건반","기타","베이스","퍼커션","그 외"))
            adapter = MusiclistAdapter(list)

            binding.recMusic.adapter = adapter
            binding.recMusic.layoutManager = LinearLayoutManager(context)
        }

        fun clearText(){
            binding.writeMusictitle.text.clear()
            binding.writeVocal.text.clear()
            binding.writeGuitar.text.clear()
            binding.writeBass.text.clear()
            binding.writeDrum.text.clear()
            binding.writeOther.text.clear()
            binding.writePiano.text.clear()
        }

        binding.btnApply.setOnClickListener{
            val enteredT: String = binding.writeMusictitle.text.toString()
            val enteredV: String = binding.writeVocal.text.toString()
            val enteredG: String = binding.writeGuitar.text.toString()
            val enteredB: String = binding.writeBass.text.toString()
            val enteredD: String = binding.writeDrum.text.toString()
            val enteredO: String = binding.writeOther.text.toString()
            val enteredP: String = binding.writePiano.text.toString()

            viewModel.newMusics(Music(enteredT, enteredV, enteredP, enteredG, enteredB, enteredD, enteredO))
            clearText()
        }

        return binding.root
    }
}