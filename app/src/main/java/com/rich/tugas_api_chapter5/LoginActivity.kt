package com.rich.tugas_api_chapter5

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rich.tugas_api_chapter5.databinding.ActivityLoginBinding
import com.rich.tugas_api_chapter5.databinding.ActivityRegisterBinding
import com.rich.tugas_api_chapter5.model.DataUser
import com.rich.tugas_api_chapter5.model.GetFilmResponseItem
import com.rich.tugas_api_chapter5.model.GetUserResponseItem
import com.rich.tugas_api_chapter5.network.APIClient
import com.rich.tugas_api_chapter5.viewmodel.ViewModelUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userVM: ViewModelUser
    private lateinit var sharedprefences :SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userVM = ViewModelProvider(this).get(ViewModelUser::class.java)

        binding.btnLogin.setOnClickListener {
            var inUser = binding.etUsername.text.toString()
            var inPassword = binding.etPassword.text.toString()
            auth(inUser, inPassword)

        sharedprefences = this.getSharedPreferences("user", Context.MODE_PRIVATE)

        }

    }

    fun toast(mess: String){
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show()
    }

    fun RegisterActivity(user:String, password :String){

    }

    fun auth(username : String, password : String) {
        APIClient.instance.getAllUser()
            .enqueue(object : Callback<List<GetUserResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetUserResponseItem>>,
                    response: Response<List<GetUserResponseItem>>) {
                    if (response.isSuccessful) {
                        var responseBody = response.body()
                        if (responseBody != null) {
                            Log.d(TAG, "onResponse: ${responseBody.toString()}")
                            for (i in 0 until responseBody.size) {
                                if (responseBody[i].username.equals(username) && responseBody[i].password.equals(password)
                                ) {
                                    var addData = sharedprefences.edit()
                                    addData.putString("address", responseBody[i].address)
                                    addData.putInt("age", responseBody[i].age!!)
                                    addData.putString("name", responseBody[i].name)
                                    addData.putString("password", responseBody[i].password)
                                    addData.putString("id", responseBody[i].id)
                                    addData.apply()
                                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
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
