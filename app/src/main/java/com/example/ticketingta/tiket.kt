package com.example.ticketingta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketingta.adapter.GroupTicketAdapter
import com.example.ticketingta.databinding.FragmentTiketBinding
import com.example.ticketingta.viewmodel.MyViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [tiket.newInstance] factory method to
 * create an instance of this fragment.
 */
class tiket : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentTiketBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTiketBinding.inflate(layoutInflater, container, false)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val profil = requireActivity().getSharedPreferences("login_session", AppCompatActivity.MODE_PRIVATE)
        val idCustomer = profil.getInt("id_customer", 0)

        val adapter = GroupTicketAdapter(requireContext())
        val rvGroupTicket = binding.rvGroupTicket
        rvGroupTicket.adapter = adapter
        rvGroupTicket.layoutManager = LinearLayoutManager(requireContext())

        myViewModel.getHalamanListTiket(idCustomer)

        myViewModel.halamanListTiketResponse.observe(viewLifecycleOwner){
            adapter.setData(it)
        }

        return binding.root
    }
}

