package com.example.ticketingta.model

data class LoginPayload (
    val id_customer : Int,
    val nama : String,
    val email : String,
    val foto : String
        )