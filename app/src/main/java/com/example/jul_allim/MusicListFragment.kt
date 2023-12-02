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

    // var binding: ActivityMainBinding? = null
    lateinit var adapter: MusiclistAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMusicListBinding.inflate(inflater,container,false)


        /*val musics = arrayOf(
            Music("푸르던", "윤서진", "유승빈","임세현", "석효준", "김수빈", "황주원"),
        )
         */
        viewModel.musics.observe(viewLifecycleOwner) {
            val list= it
            list.add(0,Music("곡제목","보컬","건반","기타","베이스","드럼/카혼","그 외"))
            adapter = MusiclistAdapter(list)

            binding.recMusic.adapter = adapter
            binding.recMusic.layoutManager = LinearLayoutManager(context)
        }

        binding.btnApply.setOnClickListener{
            // 뮤직 하나 만들어서 객체를 토스해
            val enteredT: String = binding.writeMusictitle.text.toString()
            val enteredV: String = binding.writeVocal.text.toString()
            val enteredG: String = binding.writeGuitar.text.toString()
            val enteredB: String = binding.writeBass.text.toString()
            val enteredD: String = binding.writeDrum.text.toString()
            val enteredO: String = binding.writeOther.text.toString()
            val enteredP: String = binding.writePiano.text.toString()


            viewModel.newMusics(Music(enteredT, enteredV, enteredP, enteredG, enteredB, enteredD, enteredO))
        }

        return binding.root
    }
}