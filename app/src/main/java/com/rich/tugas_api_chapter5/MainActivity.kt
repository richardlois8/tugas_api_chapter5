package com.rich.tugas_api_chapter5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rich.tugas_api_chapter5.activity.AddFilmActivity
import com.rich.tugas_api_chapter5.adapter.FilmAdapter
import com.rich.tugas_api_chapter5.databinding.ActivityMainBinding
import com.rich.tugas_api_chapter5.viewmodel.ViewModelFilm

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var getDataUsername = sharedPref.getString("username", "")
        binding.tvUsername.text = "Hello, $getDataUsername"

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddFilmActivity::class.java)
            startActivity(intent)
        }

        showDataFilm()
    }

    fun showDataFilm(){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)

        viewModel.getLiveDataFilem().observe(this, Observer {
            if (it != null){
                binding.rvListFilm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvListFilm.adapter = FilmAdapter(it)
            }
        })
        viewModel.callApiFilm()
    }
}