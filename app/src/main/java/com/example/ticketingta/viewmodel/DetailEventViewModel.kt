package com.example.ticketingta.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailEventViewModel() : ViewModel() {

    //View Model Percobaan ternyata gk penting

    var idCustomer : Int = 0
    var idEvent: Int = 0


    // LiveData untuk idPemesanan Terbaru

    private val _idPemesananTerbaru = MutableLiveData<Int>()
    val idPemesananTerbaru : LiveData<Int> = _idPemesananTerbaru



    init {
        idCustomer = 0
        idEvent = 0
        _idPemesananTerbaru.value = 0
    }


    fun getIdCustomer (idCustomer : Int){
        this.idCustomer = idCustomer
    }

    fun getIdEvent (idEvent : Int){
        this.idEvent = idEvent
    }

    fun getIdPemesananTerbaru(idPemesanan : Int){
        _idPemesananTerbaru.value = idPemesanan
    }

}