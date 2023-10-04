package com.example.ticketingta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ticketingta.model.Event
import com.example.ticketingta.model.MetodePembayaran
import com.example.ticketingta.model.response.HalamanListTiketResponse
import com.example.ticketingta.model.response.HalamanListTiketSpesifikResponse
import com.example.ticketingta.model.response.HalamanPaymentResponse
import com.example.ticketingta.model.response.InsertPembayaranResponse
import com.example.ticketingta.model.response.InsertPemesananResponse
import com.example.ticketingta.network.RetrofitClient
import com.example.ticketingta.repository.MyRepositoryProvider
import com.example.ticketingta.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : Repository

    init {
        val api = RetrofitClient().getInstance()
//        repository = Repository(api)
        repository = MyRepositoryProvider.provideRepository(api)
        getListEvent() //Cobain pas init langsing dikerjain
    }

    // LiveData untuk daftar event
    val eventList: LiveData<List<Event>> = repository.eventList

    // Fungsi untuk mendapatkan daftar event
    fun getListEvent() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getListEvent()
        }
    }

    // LiveData untuk satu event
    val event: LiveData<Event> = repository.event

    // Fungsi untuk mendapatkan satu event
    fun getOneEvent(kode: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getOneEvent(kode)
        }
    }

    // LiveData untuk menampung hasil getMetodePembayaran
    val metodePembayaranList: LiveData<List<MetodePembayaran>> = repository.metodePembayaranList

    // Fungsi untuk mendapatkan metode pembayaran
    fun getMetodePembayaran(id: Int? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMetodePembayaran(id)
        }
    }

    // LiveData untuk response insert pemesanan
    val insertPemesananResponse: LiveData<InsertPemesananResponse?> = repository.insertPemesananResponse

    // Fungsi untuk melakukan insert pemesanan
    fun insertPemesanan(idCustomer: Int, idEvent: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertPemesanan(idCustomer, idEvent)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error

            }
        }
    }

    // Fungsi untuk mendelete insertPemesananResponse setelah selesai melakukan pemesanan
    fun deleteInsertPemesananResponse(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteResponseInsertPemenasanan()
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error

            }
        }
    }

    // Fungsi untuk menghapus pemesanan
    fun deletePemesanan(idPemesanan: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deletePemesanan(idPemesanan)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }


    // LiveData untuk response insert pembayaran
    val insertPembayaranResponse: LiveData<InsertPembayaranResponse?> = repository.insertPembayaranResponse

    // Fungsi untuk melakukan insert pembayaran
    fun insertPembayaran(
        jumlahTiket: Int,
        totalPembayaran: Int,
        statusPembayaran: String,
        idPemesanan: Int,
        idMetodePembayaran: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertPembayaran(jumlahTiket, totalPembayaran, statusPembayaran, idPemesanan, idMetodePembayaran)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }

    fun deleteInsertPembayaranResponse(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteResponseInsertPembayaran()
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }

    // Fungsi untuk mengupdate pembayaran
    fun updatePembayaran(idPembayaran: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updatePembayaran(idPembayaran)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }

    // Fungsi untuk menghapus pembayaran
    fun deletePembayaran(idPembayaran: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deletePembayaran(idPembayaran)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }


    // LiveData untuk response halaman payment
    val halamanPaymentResponse: LiveData<HalamanPaymentResponse> = repository.halamanPaymentResponse

    // Fungsi untuk mendapatkan halaman payment
    fun getHalamanPayment(idPemesanan: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getHalamanPayment(idPemesanan)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }


    // Fungsi untuk insert QR Ticket
    fun insertQRTicket(idQR: Int, gambarQR: String, idPembayaran: Int, statusPemakaian: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertQRTicket(idQR, gambarQR, idPembayaran, statusPemakaian)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }

    // LiveData untuk response halaman list tiket
    val halamanListTiketResponse: LiveData<HalamanListTiketResponse> = repository.halamanListTiketResponse

    // Fungsi untuk mendapatkan halaman list tiket
    fun getHalamanListTiket(idCustomer: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getHalamanListTiket(idCustomer)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }

    // LiveData untuk response halaman list tiket spesifik
    val halamanListTiketSpesifikResponse: LiveData<HalamanListTiketSpesifikResponse> = repository.halamanListTiketSpesifikResponse

    // Fungsi untuk mendapatkan halaman list tiket spesifik
    fun getHalamanListTiketSpesifik(idCustomer: Int, idPemesanan: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getHalamanListTiketSpesifik(idCustomer, idPemesanan)
            } catch (e: Exception) {
                // Handle error, misalnya dengan mengirimnya ke LiveData khusus untuk error
            }
        }
    }


}