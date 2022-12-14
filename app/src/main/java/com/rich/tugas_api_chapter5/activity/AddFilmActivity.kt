package com.rich.tugas_api_chapter5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rich.tugas_api_chapter5.LoginActivity
import com.rich.tugas_api_chapter5.MainActivity
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
            val name =binding.tambahNama.text.toString()
            val image = binding.tambahImage.text.toString()
            val director = binding.tambahDirector.text.toString()
            val desc = binding.tambahDesc.text.toString()
            addFilm(name,image,director,desc)
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"add film sukses", Toast.LENGTH_SHORT).show()


        }

    }
    fun addFilm(name : String, image : String, director : String, desc : String){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.callPostApiFilm(name,image,director,desc)
        viewModel.postLiveDataFilm().observe(this,{
            if(it != null){
                Toast.makeText(this,"add data sukses", Toast.LENGTH_SHORT).show()
            }
        })
    }
}