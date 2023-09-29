package com.example.ticketingta.model.response

import com.example.ticketingta.model.Event
import com.example.ticketingta.model.Pembayaran

data class HalamanPaymentResponse (
    val event: Event,
    val idPemesanan: String,
    val pembayaran: Pembayaran
        )
