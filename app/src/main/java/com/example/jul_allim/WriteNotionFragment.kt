package com.example.jul_allim

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jul_allim.databinding.FragmentNotionBinding
import com.example.jul_allim.databinding.FragmentWriteNotionBinding
import com.example.jul_allim.viewmodel.NotionViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat

class WriteNotionFragment : Fragment() {
    val viewmodel: NotionViewModel by activityViewModels()
    val img_arr: ArrayList<Bitmap> = arrayListOf()
    lateinit var binding: FragmentWriteNotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteNotionBinding.inflate(inflater,container,false)

        binding.btnUpload.setOnClickListener {
            val img_string: ArrayList<String> = arrayListOf()
            img_arr.forEach {
                img_string.add(it.ToString())
            }

            viewmodel.addNotions(binding.writespace.text.toString(),
                if(binding.swichJK.isChecked) "Kau" else "Jul",img_string.toTypedArray())

            MainActivity.getInstance()
                ?.setMainFragment(NotionFragment(),"공지사항")
        }

        binding.btnIMG.setOnClickListener {
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
                // intent의 data와 type을 동시에 설정하는 메서드
                intent.setDataAndType(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*"
                )
                activityResult.launch(intent)


            }
        }


        return binding.root
    }
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        //결과 코드 OK , 결가값 null 아니면
        if(it.resultCode == RESULT_OK && it.data != null){
            //값 담기
            val uri  = it.data!!.data!!
            val img: Bitmap =
                if(Build.VERSION.SDK_INT >= 28) ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver,uri))
                else MediaStore.Images.Media.getBitmap(requireContext().contentResolver,uri)
            img_arr.add(img)
            Log.d("addIMG",img_arr.toString())
            binding.imglist.apply {
                adapter=NotionIMGAdapter(img_arr.toTypedArray())
                layoutManager =  LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }
            }
        }
    }
}

fun Bitmap.ToString(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}