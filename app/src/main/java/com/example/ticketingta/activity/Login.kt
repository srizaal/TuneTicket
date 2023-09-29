package com.example.ticketingta.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ticketingta.MainActivity
import com.example.ticketingta.Profile
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
    private lateinit var profil : SharedPreferences
    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        profil = getSharedPreferences("login_session", MODE_PRIVATE)
        if (profil.getString("nama",null) != null){
//            startActivity(Intent(this@Login, Profile::class.java))
            startActivity(Intent(this@Login, MainActivity::class.java))
        }

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

        binding!!.tvMasuk.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun getData () {
        val api = RetrofitClient().getInstance()
        api.login(email, pass).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.response == true) {

                         getSharedPreferences("login_session", MODE_PRIVATE)
                            .edit()
                            .putInt("id_customer", response.body()?.payload!!.id_customer)
                            .putString("nama", response.body()?.payload?.nama)
                            .putString("email", response.body()?.payload?.email)
                            .putString("foto", response.body()?.payload?.foto)
                            .apply()

                        val nama = profil.getString("nama", null)

                        binding!!.loading.visibility = View.GONE

                        if (nama != null) {
                            Log.i(TAG, nama)
                        }

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