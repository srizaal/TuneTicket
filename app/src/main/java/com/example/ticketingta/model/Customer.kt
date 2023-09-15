package com.example.ticketingta.model

data class Customer(
    val email: String,
    val fotoProfile: String,
    val kodeCustomer: Int,
    val listTransaksi: List<Transaksi>,
    val nama: String,
    val password: String,
    val telepon: String
)