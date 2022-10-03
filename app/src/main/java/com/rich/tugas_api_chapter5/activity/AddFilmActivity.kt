package com.rich.tugas_api_chapter5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rich.tugas_api_chapter5.R
import com.rich.tugas_api_chapter5.databinding.ActivityAddFilmBinding
import com.rich.tugas_api_chapter5.viewmodel.ViewModelFilm

class AddFilmActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            var name =binding.tambahNama.text.toString()
            var image = binding.tambahImage.text.toString()
            var director = binding.tambahDirector.text.toString()
            var desc = binding.tambahDesc.text.toString()
            addFilm(name,image,director,desc)
        }

    }
    fun addFilm(name : String, image : String, director : String, desc : String){
        var viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.callPostApiFilm(name,image,director,desc)
        viewModel.postLiveDataCar().observe(this,{
            if(it != null){
                Toast.makeText(this,"add data sukses", Toast.LENGTH_SHORT).show()
            }
        })
    }
}