package com.example.ticketingta.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketingta.R
import com.example.ticketingta.adapter.ListTicketAdapter
import com.example.ticketingta.databinding.ActivityDetailTiketBinding
import com.example.ticketingta.viewmodel.MyViewModel

class DetailTiket : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTiketBinding
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTiketBinding.inflate(layoutInflater)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val adapter = ListTicketAdapter(this)
        val rvListTiket = binding.rvJenisTiket
        rvListTiket.adapter = adapter
        rvListTiket.layoutManager = LinearLayoutManager(this)

        // ambil data List Ticket
        myViewModel.halamanListTiketSpesifikResponse.observe(this, Observer {
            adapter.setData(it)
        })


        setContentView(binding.root)
    }
}