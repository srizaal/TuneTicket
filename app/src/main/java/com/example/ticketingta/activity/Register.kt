package com.example.ticketingta.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.ticketingta.R
import com.example.ticketingta.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var namaET: TextInputEditText
    private lateinit var emailET: TextInputEditText
    private lateinit var teleponET: TextInputEditText
    private lateinit var passwordET: TextInputEditText
    private lateinit var btnSignUp: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        binding.tvMasuk.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }
}