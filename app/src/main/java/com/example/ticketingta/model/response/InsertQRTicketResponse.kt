package com.example.ticketingta.model.response

import com.google.gson.annotations.SerializedName

data class InsertQRTicketResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("idQR") val idQR: Int
)
