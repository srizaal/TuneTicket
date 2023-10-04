package com.example.ticketingta.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.example.ticketingta.R
import com.example.ticketingta.databinding.ActivityRegisterBinding
import com.example.ticketingta.network.ApiClient
import com.google.android.material.textfield.TextInputEditText

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var nama: String = ""
    private var email: String = ""
    private var telepon: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            nama = binding.namaET.text.toString()
            email = binding.emailET.text.toString()
            telepon = binding.teleponET.text.toString()
            password = binding.passwordET.text.toString()


            when {
                nama == "" -> {
                    binding.namaET.error = "Nama tidak boleh kosong"
                }
                email == "" -> {
                    binding!!.emailET.error = "Email tidak boleh kosong"
                }
                telepon == "" -> {
                    binding!!.teleponET.error = "Telepon tidak boleh kosong"
                }
                password == "" -> {
                    binding!!.passwordET.error = "Password tidak boleh kosong"
                }
                else -> {
                    binding.loading.visibility = View.VISIBLE
                    inputData()
                }
            }
        }

        binding.tvMasuk.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun inputData() {

        // Melakukan validasi sederhana, Anda dapat menyesuaikannya sesuai kebutuhan
        if (nama.isNotEmpty() && email.isNotEmpty() && telepon.isNotEmpty() && password.isNotEmpty()) {
            // Menampilkan data melalui logcat
            Log.i("Data Input", "Nama: $nama, Email: $email, Telepon: $telepon, Password: $password")

            // Di sini, Anda dapat menyimpan data ke tempat penyimpanan atau melakukan tindakan lainnya sesuai kebutuhan
        } else {
            // Menampilkan pesan jika ada field yang kosong
            Log.e("Data Input", "Semua field harus diisi")
        }
    }

}