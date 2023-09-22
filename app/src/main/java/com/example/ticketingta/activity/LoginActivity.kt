package com.example.ticketingta.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketingta.databinding.FragmentLoginBinding
import com.example.ticketingta.model.LoginResponse
import com.example.ticketingta.network.RetrofitClient
import okhttp3.Response
import javax.security.auth.callback.Callback


class LoginActivity : AppCompatActivity() {
    private var binding : FragmentLoginBinding? = null
    private var email : String = ""
    private var pass : String = ""

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.btnLogin.setOnClickListener {
            email = binding!!.etEmailLogin.text.toString()
            pass = binding!!.etPassword.text.toString()

            when{
                email == "" -> {
                    binding!!.etEmailLogin.error = "Email tidak boleh kosong"
                }
                pass == "" -> {
                    binding!!.etPassword.error = "Password tidak boleh kosong"
                }
            }

        }
    }

//    private fun getData() {
//        val api = RetrofitClient().getInstance()
//        api.login(email, pass).enqueue(object : Callback<LoginResponse>{
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if ()
//            }
//
//            override fun onFallure(call: Call<LoginResponse>, t: Throwable) {
//
//            }
//        })
//    }
}