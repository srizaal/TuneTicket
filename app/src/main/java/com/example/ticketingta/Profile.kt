package com.example.ticketingta

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketingta.activity.Login
import com.example.ticketingta.databinding.FragmentProfileBinding

class Profile : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private lateinit var profil: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profil = requireActivity().getSharedPreferences("login_session", AppCompatActivity.MODE_PRIVATE)

        binding?.idCustomer?.text = profil.getInt("id_customer", 0).toString()
        binding?.tvNamaUser?.text = profil.getString("nama", null)
        binding?.tvEmailUser?.text = profil.getString("email", null)

        // Picasso.get().load(profil.getString("foto", null)).into(binding?.imgProfile)

        binding?.btnLogout?.setOnClickListener {
            profil.edit().clear().commit()

            startActivity(Intent(requireActivity(), Login::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}