package com.rich.tugas_api_chapter5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rich.tugas_api_chapter5.databinding.ActivityLoginBinding
import com.rich.tugas_api_chapter5.databinding.ActivityRegisterBinding
import com.rich.tugas_api_chapter5.viewmodel.ViewModelUser

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userVM: ViewModelUser
    private lateinit var sharedprefences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userVM = ViewModelProvider(this).get(ViewModelUser::class.java)
        binding.tvRegister.setOnClickListener {
            var inUser = binding.etUsername.text.toString()
            var inPassword = binding.etPassword.text.toString()
            gotoRegister()
            sharedprefences = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        }


    }

    private fun gotoRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}