package com.example.ticketingta.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ticketingta.R
import com.example.ticketingta.databinding.ActivityPilihTiketBinding
import com.example.ticketingta.dummydata.metodePembayaranList
import com.example.ticketingta.model.Event
import com.example.ticketingta.model.MetodePembayaran
import com.example.ticketingta.viewmodel.MyViewModel
import com.example.ticketingta.viewmodel.PilihTiketViewModel

class PilihTiket : AppCompatActivity() {

    private lateinit var binding: ActivityPilihTiketBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var mPilihTiketViewModel: PilihTiketViewModel
    // Deklarasikan properti untuk menyimpan data event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPilihTiketBinding.inflate(layoutInflater)

        //View Model untuk ambil data Event
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        mPilihTiketViewModel = ViewModelProvider(this).get(PilihTiketViewModel::class.java)

        //Intent untuk ambil data Event dan data pemesanan terbaru
        val intent = intent
        val idEvent = intent.getIntExtra("idEvent", 0)
        val idPemesanan = intent.getIntExtra("idPemesananBaru", 0)

        Log.d("Event ID Tes", "ID Event: $idEvent")
        Log.d("Pemesanan ID Tes", "ID Pemesanan: $idPemesanan")
        
        val radioGroupPaymentMethods = binding.radioGroupPaymentMethods
//        val radioButtonMandiri = binding.rbMandiri
//        val radioButtonBCA = binding.rbBCA
//        val radioButtonBRI = binding.rbBRI


        radioGroupPaymentMethods.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.rbMandiri -> {
                    val metodePembayaran : MetodePembayaran? = metodePembayaranList.firstOrNull{it.nama  == "Mandiri"}
                    mPilihTiketViewModel.metodePembayaran = metodePembayaran
                    Log.d("Tes Metode Pembayaran", "Metode Pembayaran ; ${mPilihTiketViewModel.metodePembayaran}")
                }
                R.id.rbBCA -> {
                    val metodePembayaran : MetodePembayaran? = metodePembayaranList.firstOrNull{it.nama  == "BCA"}
                    mPilihTiketViewModel.metodePembayaran = metodePembayaran
                    Log.d("Tes Metode Pembayaran", "Metode Pembayaran ; ${mPilihTiketViewModel.metodePembayaran}")
                }
                R.id.rbBRI -> {
                    val metodePembayaran : MetodePembayaran? = metodePembayaranList.firstOrNull{it.nama  == "BRI"}
                    mPilihTiketViewModel.metodePembayaran = metodePembayaran
                    Log.d("Tes Metode Pembayaran", "Metode Pembayaran ; ${mPilihTiketViewModel.metodePembayaran}")
                }
            }
        }
      

        // tampilkan data event
        myViewModel.event.observe(this, Observer {
            showEvent(it)
            mPilihTiketViewModel.getHargaTiket(it)
        })

//        var jumlahTiket = 1

        mPilihTiketViewModel.ticketCount.observe(this, Observer {
            binding.tvQtyTiket.text = it.toString()
        })
        mPilihTiketViewModel.hargaTotal.observe(this, Observer {
            if(it != 0 ){
                binding.tvTotalHargaTiket.text = it.toString()
            }else{
                binding.tvTotalHargaTiket.text = mPilihTiketViewModel.hargaTiket.toString()
            }
        })

        setContentView(binding.root)

        binding.qtyKurang.setOnClickListener {
//            if(jumlahTiket > 1){
//                jumlahTiket--
//                Log.d("Tes Jumlah Tiket", "Jumlah Tiket : $jumlahTiket")
//                binding.tvQtyTiket.text = jumlahTiket.toString()
//                binding.tvTotalHargaTiket.text = (jumlahTiket * eventData?.hargaTiket!!).toString()
//            }
            mPilihTiketViewModel.kurangiJumlahTiket()
            val jumlahTiket = mPilihTiketViewModel.ticketCount.value
            Log.d("Tes Jumlah Tiket", "Jumlah Tiket : $jumlahTiket")
        }

        binding.qtyTambah.setOnClickListener {
//            jumlahTiket++
//            Log.d("Tes Jumlah Tiket", "Jumlah Tiket : $jumlahTiket")
//            binding.tvQtyTiket.text = jumlahTiket.toString()
//            binding.tvTotalHargaTiket.text = (jumlahTiket * eventData?.hargaTiket!!).toString()
            mPilihTiketViewModel.tambahJumlahTiket()

            val jumlahTiket = mPilihTiketViewModel.ticketCount.value
            Log.d("Tes Jumlah Tiket", "Jumlah Tiket : $jumlahTiket")
        }

//        binding.tvQtyTiket.text = jumlahTiket.toString()
//        binding.tvTotalHargaTiket.text = (jumlahTiket * eventData?.hargaTiket!!).toString()


        //jika button back ditekan
        binding.icBack.setOnClickListener {
            //Menampilkan Dialog konfirmasi pembatalan pemesanan
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah anda ingin membatalkan pemesanan?").setCancelable(false)
                .setPositiveButton("Ya") { dialog, id -> deletePemesanan(idPemesanan) }
                .setNegativeButton("Tidak") { dialog, id -> }
            val alerts = builder.create()
            alerts.show()
        }

        // Jika button bayar ditekan
        binding.btnBayarPilihTiket.setOnClickListener {
            //Ambil data untuk membuat data pembayaran
            val jumlahTiket = mPilihTiketViewModel.ticketCount.value
            val metodePembayaran = mPilihTiketViewModel.metodePembayaran
            val hargaTiket = mPilihTiketViewModel.hargaTiket
            val biayaTransfer: Int? = metodePembayaran?.biayaTransfer
            val totalPembayaran = hargaTiket * jumlahTiket!! + biayaTransfer!!
            val statusPembayaran = "Belum Bayar"
            val idMetodePembayaran : Int = metodePembayaran.idMetodePembayaran!!

            //Buat data pembayaran
            myViewModel.insertPembayaran(
                jumlahTiket = jumlahTiket,
                totalPembayaran = totalPembayaran,
                statusPembayaran = statusPembayaran,
                idPemesanan = idPemesanan,
                idMetodePembayaran = idMetodePembayaran
            )

        }

        myViewModel.insertPembayaranResponse.observe(this, Observer {

            //Jika Response tidak null, lakukan pengecekan status dan idPembayaran baru
            if (it != null){
                //Jika Insert Berhasil
                if(it.status == 1){

                    val idPembayaranBaru = it.idPembayaran
                    val intent = Intent(this, PaymentKonfirmasi::class.java)
                    intent.putExtra("idPembayaranBaru", idPembayaranBaru)
                    intent.putExtra("idPemesananBaru", idPemesanan)

                    //Delete response dulu sebelum pindah activity
                    myViewModel.deleteInsertPembayaranResponse()

                    //Load Data halaman selanjutnya
                    myViewModel.getHalamanPayment(idPemesanan)

                    //Pindah Halaman
                    startActivity(intent)

                }
            }
        })



    }

    private fun showEvent(event: Event) {
        binding.tvNamaEvent.text = event.nama
        binding.tvTanggalTiket.text = event.tanggal
        binding.tvHargaTiketDetail.text = event.hargaTiket.toString()
        binding.tvTotalHargaTiket.text = event.hargaTiket.toString()
    }

    private fun deletePemesanan(idPemesanan: Int) {
        myViewModel.deletePemesanan(idPemesanan)
        finish()
    }

    override fun onBackPressed() {
        val intent = intent
        val idPemesanan = intent.getIntExtra("idPemesananBaru", 0)
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah anda ingin membatalkan pemesanan?").setCancelable(false)
            .setPositiveButton("Ya") { dialog, id -> deletePemesanan(idPemesanan) }
            .setNegativeButton("Tidak") { dialog, id -> }
        val alerts = builder.create()
        alerts.show()
    }

}