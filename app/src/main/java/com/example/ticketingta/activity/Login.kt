package com.example.ticketingta.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ticketingta.MainActivity
import com.example.ticketingta.R
import com.example.ticketingta.databinding.ActivityLoginBinding
import com.example.ticketingta.model.LoginResponse
import com.example.ticketingta.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private var binding : ActivityLoginBinding? = null
    private var email : String = ""
    private var pass : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.btnLogin.setOnClickListener {
            email = binding!!.etEmailLogin.text.toString()
            pass = binding!!.etPassword.text.toString()

            when {
                email == "" -> {
                    binding!!.etEmailLogin.error = "Email tidak boleh kosong"
                }
                pass == "" -> {
                    binding!!.etPassword.error = "Password tidak boleh kosong"
                }
                else -> {
                    binding!!.loading.visibility = View.VISIBLE
                    getData()
                }
            }
        }
    }

    private fun getData () {
        val api = RetrofitClient().getInstance()
        api.login(email, pass).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.response == true) {
                        binding!!.loading.visibility = View.GONE
                        startActivity(Intent(this@Login, MainActivity::class.java))
                        finish()
                    } else {
                        binding!!.loading.visibility = View.GONE
                        Toast.makeText(
                            this@Login,
                            "Login gagal, Periksa kembali email dan password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else {
                    Toast.makeText(
                        this@Login,
                        "Login gagal, terjadi kesalahan",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Pesan error", "${t.message}")
            }
        })
    }
}