package com.example.ticketingta.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketingta.model.Event
import com.example.ticketingta.model.MetodePembayaran
import com.example.ticketingta.network.RetrofitClient

class PilihTiketViewModel() : ViewModel() {

    // LiveData untuk jumlah tiket
    private val _ticketCount = MutableLiveData<Int>()
    val ticketCount: LiveData<Int> = _ticketCount

    // LiveData untuk harga Total
    private val _hargaTotal = MutableLiveData<Int>()
    val hargaTotal: LiveData<Int> = _hargaTotal

    var hargaTiket : Int = 0

    var metodePembayaran : MetodePembayaran? = null

    init {
        _ticketCount.value = 1 // Inisialisasi jumlah tiket
        metodePembayaran = null

    }

    // Constructor atau metode untuk menginisialisasi data event untuk harga tiket
    fun getHargaTiket(eventData: Event) {
        hargaTiket = eventData.hargaTiket!!
        Log.d(" Tes Harga Tiket", "(View Model) Harga Tiket : $hargaTiket")
    }

    fun getMetodePembayaran(metodePembayaran: MetodePembayaran?){
        this.metodePembayaran = metodePembayaran
    }

    // Metode untuk menambah jumlah tiket
    fun tambahJumlahTiket() {
        val currentCount = _ticketCount.value ?: 0
        _ticketCount.value = currentCount + 1
        _hargaTotal.value = _ticketCount.value!! * hargaTiket
    }

    // Metode untuk mengurangi jumlah tiket
    fun kurangiJumlahTiket() {
        val currentCount = _ticketCount.value ?: 0
        if (currentCount > 1) {
            _ticketCount.value = currentCount - 1
            _hargaTotal.value = _ticketCount.value!! * hargaTiket
        }
    }

}