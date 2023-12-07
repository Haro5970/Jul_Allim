package com.example.jul_allim

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentProfileEditBinding
import com.example.jul_allim.viewmodel.StudentVM
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.io.ByteArrayOutputStream

val database = Firebase.database
val userRef = database.getReference("Student").orderByChild("name").equalTo("배별하")

class ProfileEditFragment(var picbitmap: Bitmap) : Fragment() {
    private lateinit var binding: FragmentProfileEditBinding
    private val viewModel: StudentVM by activityViewModels()
    val pic: String get() {
        //이미지 비트맵을 스트링으로
        val baos = ByteArrayOutputStream()
        picbitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
        return Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //생성시
        binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        binding.imageView2.setImageBitmap(picbitmap)

        binding.button2.setOnClickListener {
            val num = binding.editstnum.text.toString()
            val ss = binding.editsessoin.text.toString()
            val line = binding.editline.text.toString()
            viewModel.updateprof(num,ss,line,pic)
            MainActivity.getInstance()?.setMainFragment(ProfileFragment(),"프로필")


            val resBundle = Bundle().apply{
                putString("num", num)
                putString("ss", ss)
                putString("line", line)
            }
            setFragmentResult("text", resBundle)
        }

        binding.button.setOnClickListener {
            addImg()
        }

        return binding.root
    }

    //이미지추가
    private fun addImg(){
        // sdk 버전에 따른 파일 읽기 권한
        val readPermission =
            if (Build.VERSION.SDK_INT <= 32) android.Manifest.permission.READ_EXTERNAL_STORAGE
            else android.Manifest.permission.READ_MEDIA_IMAGES
        val getPermission =
            ContextCompat.checkSelfPermission(this.requireContext(), readPermission)

        if (getPermission == PackageManager.PERMISSION_DENIED) {
            // 권한 요청
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(readPermission), 1)

        } else {
            // 권한이 있는 경우 갤러리 실행
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            // 갤러리에서 사진을 고른 후
            setImg.launch(intent)
        }
    }

    private val setImg = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        //결과 코드 OK , 결가값 null 아니면
        if(it.resultCode == Activity.RESULT_OK && it.data != null){
            val uri  = it.data!!.data!!
            // uri에서 비트맵 이미지 생성
            val img: Bitmap =
                if(Build.VERSION.SDK_INT >= 28) ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver,uri))
                else MediaStore.Images.Media.getBitmap(requireContext().contentResolver,uri)
            binding.imageView2.setImageBitmap(img)
            picbitmap = img
        }
    }




    }





