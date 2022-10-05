package com.rich.tugas_api_chapter5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rich.tugas_api_chapter5.databinding.ActivityUpdateFilmBinding
import com.rich.tugas_api_chapter5.viewmodel.ViewModelFilm
import kotlin.properties.Delegates

class UpdateFilmActivity : AppCompatActivity() {
    lateinit var binding : ActivityUpdateFilmBinding
    lateinit var viewModel : ViewModelFilm
    var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        id = intent.getStringExtra("id")!!.toInt()
        Log.d("DEBUG_ID",id.toString())
        setDataToInput()

        binding.btnAdd.setOnClickListener{
            updateDataFilm()
            finish()
        }
    }

    fun setDataToInput(){
        viewModel.callGetFilmById(id)
        viewModel.getFilmById(id).observe(this, Observer {
            binding.tambahNama.setText(it.name)
            binding.tambahDirector.setText(it.director)
            binding.tambahDesc.setText(it.description)
            binding.tambahImage.setText(it.image)
        })
    }

    fun updateDataFilm(){
        var name = binding.tambahNama.text.toString()
        var director = binding.tambahDirector.text.toString()
        var desc = binding.tambahDesc.text.toString()
        var img  = binding.tambahImage.text.toString()
        Log.d("infoid",id.toString())

        viewModel.updateApiFilm(id,name,director,desc,img)
        viewModel.updatLiveDataFilm().observe(this, Observer { 
            if (it != null){
                Toast.makeText(this,"Update Data Success", Toast.LENGTH_SHORT).show()
            }
        })
    }

}