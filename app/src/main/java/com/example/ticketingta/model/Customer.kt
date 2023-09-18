package com.example.ticketingta.model

data class Customer(
    val email: String,
    val fotoProfile: String,
    val idCustomer: Int,
    val listPemesanan: List<Pemesanan>,
    val nama: String,
    val password: String,
    val telepon: String
)