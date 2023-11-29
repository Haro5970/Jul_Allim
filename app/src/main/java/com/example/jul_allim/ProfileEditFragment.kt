package com.example.jul_allim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.jul_allim.databinding.FragmentProfileEditBinding


class ProfileEditFragment() : Fragment() {
    private lateinit var binding: FragmentProfileEditBinding

    var name: String? = null
    var num: String? = null
    var ss: String? = null
    var line: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //생성시
        val binding = FragmentProfileEditBinding.inflate(inflater, container, false)

        //프로퍼티는 스마트캐스트가 안되기 때문에 영역함수 let 이용
        arguments?.let{

            binding.editname.setText(it.getString("name"))
            binding.editstnum.setText(it.getString("num"))
            binding.editsessoin.setText(it.getString("ss"))
            binding.editline.setText(it.getString("line"))
        }

        binding.button2.setOnClickListener {

            val resBundle = Bundle().apply{

                putString("name", binding.editname.text.toString())
                putString("num", binding.editstnum.text.toString())
                putString("ss", binding.editsessoin.text.toString())
                putString("line", binding.editline.text.toString())

            }

            setFragmentResult("text", resBundle)


        }


        return binding.root
    }

    // Fragment는 파라미터로 받으면 안되기 때문에
    companion object{

        fun newInstance(name:String, num:String, ss: String, line: String) =

            ProfileEditFragment().apply {
                arguments = Bundle().apply{


                    name?.let{
                        putString("name", it)
                    }
                    num?.let{
                        putString("num", it)
                    }
                    ss?.let{
                        putString("ss", it)
                    }

                    line?.let{
                        putString("line", it)
                    }


                }


            }

        }


    }





