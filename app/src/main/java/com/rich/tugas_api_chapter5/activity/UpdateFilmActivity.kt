package com.rich.tugas_api_chapter5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rich.tugas_api_chapter5.R
import com.rich.tugas_api_chapter5.databinding.ActivityUpdateFilmBinding
import com.rich.tugas_api_chapter5.viewmodel.ViewModelFilm

class UpdateFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityUpdateFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener{
            var fetId = intent.getIntExtra("update",0)
            var name = binding.tambahNama.text.toString()
            var director = binding.tambahDirector.text.toString()
            var desc = binding.tambahDesc.text.toString()
            var img  = binding.tambahImage.text.toString()
            Log.d("infoid",fetId.toString())

            updateDataFilm(fetId,name,director,desc,img)
            finish()
        }
    }
    fun updateDataFilm(id : Int, name : String, director : String, desc : String, img : String){
        var viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.updateApiFilm(id,name,director,desc,img)
        viewModel.updatLiveDataFilm().observe(this, Observer { 
            if (it != null){
                Toast.makeText(this,"Update Data Success", Toast.LENGTH_SHORT).show()
            }
        })
    }

}