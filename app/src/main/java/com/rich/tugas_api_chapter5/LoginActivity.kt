package com.rich.tugas_api_chapter5

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rich.tugas_api_chapter5.databinding.ActivityLoginBinding
import com.rich.tugas_api_chapter5.databinding.ActivityRegisterBinding
import com.rich.tugas_api_chapter5.model.GetUserResponseItem
import com.rich.tugas_api_chapter5.network.APIClient
import com.rich.tugas_api_chapter5.viewmodel.ViewModelUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userVM: ViewModelUser
    private lateinit var sharedprefences :SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedprefences = this.getSharedPreferences("user", Context.MODE_PRIVATE)

        if(sharedprefences.getBoolean("isLogin",false)){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        //kalau diklik ke register langsung ke act regis
        binding.tvRegister.setOnClickListener { gotoRegister() }

        userVM = ViewModelProvider(this).get(ViewModelUser::class.java)
        binding.btnLogin.setOnClickListener {
            var inUser = binding.etUsername.text.toString()
            var inPassword = binding.etPassword.text.toString()
            auth(inUser, inPassword)
        }
    }


    private fun gotoRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
    private fun gotoHome(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun auth(username : String, password : String) {
        APIClient.instance.getAllUser()
            .enqueue(object : Callback<List<GetUserResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetUserResponseItem>>,
                    response: Response<List<GetUserResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        var responseBody = response.body()
                        if (responseBody != null) {
                            Log.d(TAG, "onResponse: ${responseBody.toString()}")
                            for (i in 0 until responseBody.size) {
                                if (responseBody[i].username.equals(username) && responseBody[i].password.equals(password)
                                ) {
                                    var addData = sharedprefences.edit()
                                    addData.putString("username", responseBody[i].username)
                                    addData.putBoolean("isLogin", true)
                                    addData.apply()
                                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT)
                                        .show()
                                    gotoHome()
                                }
                            }
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<GetUserResponseItem>>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
                }
            })
    }

}
