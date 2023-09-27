package com.example.ticketingta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketingta.adapter.EventAdapter
import com.example.ticketingta.databinding.FragmentBerandaBinding
import dummyEventList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Beranda.newInstance] factory method to
 * create an instance of this fragment.
 */
class Beranda : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentBerandaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBerandaBinding.inflate(layoutInflater,container,false)

        val adapter = EventAdapter(this, requireContext())
        val rvListEvent = binding.rvEvent
        rvListEvent.adapter = adapter
        rvListEvent.layoutManager = LinearLayoutManager(requireContext())
        //Make data dummy dulu, nanti di ganti
        adapter.setData(dummyEventList)

        return binding.root
    }
}