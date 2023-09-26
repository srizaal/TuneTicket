package com.example.ticketingta

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticketingta.databinding.ActivityProfilBinding
import com.squareup.picasso.Picasso

class Profil : AppCompatActivity() {
    private var binding : ActivityProfilBinding? = null
    private  lateinit var profil: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        binding?.idCustomer?.text = profil.getInt("id_customer", 0).toString()
        binding?.tvNamaUser?.text = profil.getString("nama", null)
        binding?.tvEmailUser?.text = profil.getString("email", null)

//        Picasso.get().load(profil.getString("foto", null)).into(binding?.imgProfile)
    }
}