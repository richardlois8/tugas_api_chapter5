package com.rich.tugas_api_chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rich.tugas_api_chapter5.databinding.ActivityRegisterBinding
import com.rich.tugas_api_chapter5.viewmodel.ViewModelUser

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var userVM : ViewModelUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userVM = ViewModelProvider(this).get(ViewModelUser::class.java)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = binding.etName.text.toString()
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val passwordConfirm = binding.etPasswordConfirm.text.toString()

        if(name.isEmpty() || username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show()
        }else{
            if(password == passwordConfirm){
                userVM.callPostApiUser(name, username, password)
                userVM.postLiveDataUser().observe(this) {
                    if (it != null) {
                        Toast.makeText(this, "Registration Success", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }else{
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
            }
        }
    }
}