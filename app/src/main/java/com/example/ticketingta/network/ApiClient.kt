package com.example.ticketingta.network

import com.example.ticketingta.model.Event
import com.example.ticketingta.model.LoginResponse
import com.example.ticketingta.model.MetodePembayaran
import com.example.ticketingta.model.response.DeletePembayaranResponse
import com.example.ticketingta.model.response.DeletePemesananResponse
import com.example.ticketingta.model.response.HalamanListTiketResponse
import com.example.ticketingta.model.response.HalamanListTiketSpesifikResponse
import com.example.ticketingta.model.response.HalamanPaymentResponse
import com.example.ticketingta.model.response.InsertPembayaranResponse
import com.example.ticketingta.model.response.InsertPemesananResponse
import com.example.ticketingta.model.response.InsertQRTicketResponse
import com.example.ticketingta.model.response.PembayaranResponse
import com.example.ticketingta.model.response.PemesananResponse
import com.example.ticketingta.model.response.UpdatePembayaranResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {
    @FormUrlEncoded
    @POST("login/login_service.php")
    fun login(
        @Field("post_email") email : String,
        @Field("post_password") password : String
    ): Call<LoginResponse>

    // Endpoint untuk mendapatkan event
    @GET("phpapi/phpapi2.php?function=get_event")
//    fun getEvent(@Query("kode") kode: Int? = null): Call<EventResponse>
    fun getOneEvent(@Query("kode") kode: Int): Call<Event>

    // Endpoint untuk mendapatkan event
    @GET("phpapi/phpapi2.php?function=get_event")
    fun getListEvent(): Call<List<Event>>

    // Endpoint untuk mendapatkan metode pembayaran
    @GET("phpapi/phpapi2.php?function=get_metodePembayaran")
    fun getMetodePembayaran(@Query("id") id: Int? = null): Call<List<MetodePembayaran>>

    // Endpoint untuk insert pemesanan
    @FormUrlEncoded
    @POST("phpapi/phpapi2.php?function=insert_pemesanan")
    fun insertPemesanan(
        @Field("id_customer") idCustomer: Int,
        @Field("id_event") idEvent: Int
    ): Call<InsertPemesananResponse>

    // Endpoint untuk mendapatkan pemesanan terbaru
    @GET("phpapi/phpapi2.php?function=get_pemesanan_terbaru")
    fun getPemesananTerbaru(@Query("id_customer") idCustomer: Int): Call<PemesananResponse>

    // Endpoint untuk menghapus pemesanan
    @DELETE("phpapi/phpapi2.php?function=delete_pemesanan")
    fun deletePemesanan(@Query("id_pemesanan") idPemesanan: Int): Call<DeletePemesananResponse>

    // Endpoint untuk insert pembayaran
    @FormUrlEncoded
    @POST("phpapi/phpapi2.php?function=insert_pembayaran")
    fun insertPembayaran(
        @Field("jumlah_tiket")  jumlahTiket: Int,
        @Field("total_pembayaran")  totalPembayaran: Int,
        @Field("status_pembayaran")  statusPembayaran: String,
        @Field("id_pemesanan")  idPemesanan: Int,
        @Field("id_metode_pembayaran")  idMetodePembayaran: Int
    ): Call<InsertPembayaranResponse>

    // Endpoint untuk mengupdate pembayaran
    @PATCH("phpapi/phpapi2.php?function=update_pembayaran")
    fun updatePembayaran(@Query("id_pembayaran") idPembayaran: Int): Call<UpdatePembayaranResponse>

    // Endpoint untuk menghapus pembayaran
    @DELETE("phpapi/phpapi2.php?function=delete_pembayaran")
    fun deletePembayaran(@Query("id_pembayaran") idPembayaran: Int): Call<DeletePembayaranResponse>

    // Endpoint untuk mendapatkan pembayaran terbaru
    @GET("phpapi/phpapi2.php?function=get_pembayaran_terbaru")
    fun getPembayaranTerbaru(
        @Query("id_customer") idCustomer: Int?,
        @Query("status_pembayaran") statusPembayaran: String?
    ): Call<PembayaranResponse>

    // Endpoint untuk mendapatkan halaman payment
    @GET("phpapi/phpapi2.php?function=get_halaman_payment")
    fun getHalamanPayment(@Query("id_pemesanan") idPemesanan: Int): Call<HalamanPaymentResponse>

    // Endpoint untuk insert QR Ticket
    @FormUrlEncoded
    @POST("phpapi/phpapi2.php?function=insert_qrticket")
    fun insertQRTicket(
        @Field("id_qr") idQR: Int,
        @Field("gambar_qr") gambarQR: String,
        @Field("id_pembayaran") idPembayaran: Int,
        @Field("status_pemakaian") statusPemakaian: String
    ): Call<InsertQRTicketResponse>

    // Endpoint untuk mendapatkan halaman list tiket
    @GET("phpapi/phpapi2.php?function=get_halaman_list_tiket")
    fun getHalamanListTiket(
        @Query("id_customer") idCustomer: Int
    ): Call<HalamanListTiketResponse>

    // Endpoint untuk mendapatkan halaman list tiket
    @GET("phpapi/phpapi2.php?function=get_halaman_list_tiket")
    fun getHalamanListTiketSpesifik(
        @Query("id_customer") idCustomer: Int,
        @Query("id_pemesanan") idPemesanan: Int
    ): Call<HalamanListTiketSpesifikResponse>




    





}