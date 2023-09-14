import com.example.ticketingta.model.Artis
import com.example.ticketingta.model.Event

val artis1 = Artis(
    kodeArtis = 1,
    nama = "Andmesh Kamaleng",
    foto = "andmesh.jpg",
    deskripsi = "Penyanyi terkenal Indonesia dengan banyak penghargaan."
)

val artis2 = Artis(
    kodeArtis = 2,
    nama = "NOAH",
    foto = "noah.jpg",
    deskripsi = "Band rock populer dengan lagu-lagu hits."
)

val artis3 = Artis(
    kodeArtis = 3,
    nama = "Raisa",
    foto = "raisa.png",
    deskripsi = "Penyanyi dan pencipta lagu berbakat."
)

val artis4 = Artis(
    kodeArtis = 4,
    nama = "Bunga Citra Lestari",
    foto = "bcl.jpeg",
    deskripsi = "Penyanyi perempuan asal Indonesia."
)


val artis5 = Artis(
    kodeArtis = 5,
    nama = "Isyana Sarasvati",
    foto = "isyana.jpg",
    deskripsi = "Penyanyi, pianis, dan aktris berbakat."
)

val artis6 = Artis(
    kodeArtis = 6,
    nama = "Dewa 19",
    foto = "dewa19.jpg",
    deskripsi = "Band terkenal di Indonesia dengan fanbase besar."
)

val artis7 = Artis(
    kodeArtis = 7,
    nama = "Febi Putri",
    foto = "febiputri.jpg",
    deskripsi = "Penyanyi dengan lagu-lagu indie."
)

val artis8 = Artis(
    kodeArtis = 8,
    nama = "Mahalini",
    foto = "mahalini.jpg",
    deskripsi = "Penyanyi berbakat dari Indonesia."
)

val artis9 = Artis(
    kodeArtis = 9,
    nama = "Judika",
    foto = "judika.jpg",
    deskripsi = "Penyanyi pop Indonesia dengan suara merdu."
)

val artis10 = Artis(
    kodeArtis = 10,
    nama = "GAC (Gamaliel Audrey Cantika)",
    foto = "gac.jpg",
    deskripsi = "Grup musik pop Indonesia dengan harmoni vokal yang indah."
)

val artis11 = Artis(
    kodeArtis = 11,
    nama = "Tulus",
    foto = "tulus.jpg",
    deskripsi = "Penyanyi Indonesia dengan lagu-lagu hits."
)

val artis12 = Artis(
    kodeArtis = 12,
    nama = "Nadin Amizah",
    foto = "nadin.jpg",
    deskripsi = "Penyanyi perempuan Indonesia dengan gaya vokal unik."
)

val artis13 = Artis(
    kodeArtis = 13,
    nama = "Ari Lasso",
    foto = "arilasso.jpg",
    deskripsi = "Penyanyi pria Indonesia yang dikenal dengan lagu-lagu emosional."
)

val artis14 = Artis(
    kodeArtis = 14,
    nama = "RAN",
    foto = "ran.jpg",
    deskripsi = "Grup musik pop Indonesia dengan lagu-lagu yang catchy."
)

val artis15 = Artis(
    kodeArtis = 15,
    nama = "Rossa",
    foto = "rossa.jpg",
    deskripsi = "Penyanyi pop Indonesia dengan suara yang khas."
)





val eventMusik = Event(
    kodeEvent = 1,
    nama = "SYNCHRONIZE FESTIVAL",
    bannerEvent = "snychronize.png",
    jenisEvent = "Musik",
    deskripsi = "Nikmati malam yang penuh dengan musik spektakuler di konser ini. Artis terkenal dari berbagai genre musik akan tampil, termasuk rock, pop, dan hip-hop. Pastikan Anda hadir untuk pengalaman musik yang tak terlupakan!",
    tanggal = "2023-10-20",
    lokasi = "ICE BSD, Tanggerang",
    gambarMapLokasi = "icebsd.PNG",
    waktu = "19:00 - 23:00",
    hargaTiket = 150000,
    stokTiket = 1000,
    listArtis = listOf(artis1, artis2, artis3)
)

val eventMusik2 = Event(
    kodeEvent = 2,
    nama = "JAVA JAZZ",
    bannerEvent = "javajazz.jpg",
    jenisEvent = "Musik",
    deskripsi = "Saksikan festival jazz terbesar tahun ini dengan pemain jazz terbaik dari seluruh dunia. Dari jazz klasik hingga fusion modern, Anda akan mendengarkan musik berkualitas tinggi sepanjang hari.",
    tanggal = "2023-11-15",
    lokasi = "JIEXPO Kemayoran",
    gambarMapLokasi = "jiexpo.PNG",
    waktu = "12:00 - 22:00",
    hargaTiket = 120000,
    stokTiket = 800,
    listArtis = listOf(artis4, artis5, artis6)
)


val eventMusik3 = Event(
    kodeEvent = 3,
    nama = "SOUNDRENALINE",
    bannerEvent = "soundrenaline.png",
    jenisEvent = "Musik",
    deskripsi = "Sambut konser rock legendaris ini yang akan menggetarkan panggung dengan musik rock paling keras sepanjang masa. Siapkan diri Anda untuk malam yang tak terlupakan bersama legenda rock!",
    tanggal = "2023-12-10",
    lokasi = "Eco Park Ancol, Jakarta Utara",
    gambarMapLokasi = "ecoparkancol.PNG",
    waktu = "19:00 - 23:00",
    hargaTiket = 150000,
    stokTiket = 600,
    listArtis = listOf(artis7, artis8, artis9)
)


val eventMusik4 = Event(
    kodeEvent = 4,
    nama = "Djakarta Warehouse Project",
    bannerEvent = "dwp.jpg",
    jenisEvent = "Musik",
    deskripsi = "Djakarta Warehouse Project 2022: Tempat Pesta Penggemar EDM Mancanegara. Djakarta Warehouse Project (DWP) 2022 telah memeriahkan Jakarta pada 9-11 Desember 2022 dengan membawa musisi EDM ternama secara offline di Jiexpo Kemayoran.",
    tanggal = "2023-11-15",
    lokasi = "Panggung Jazz Terkenal",
    gambarMapLokasi = "map_panggung_jazz.jpg",
    waktu = "20:00 - 00:00",
    hargaTiket = 175000,
    stokTiket = 400,
    listArtis = listOf(artis10, artis11, artis12)
)


val eventMusik5 = Event(
    kodeEvent = 5,
    nama = "HeyFest",
    bannerEvent = "heyfest.jpg",
    jenisEvent = "Musik",
    deskripsi = "Heyfest! merupakan festival musik tahunan yang diadakan di Kota Bekasi sebagai tuan rumah setiap tahunnya",
    tanggal = "2023-12-10",
    lokasi = "Summarecon Mall Bekasi, Bekasi",
    gambarMapLokasi = "smb.PNG",
    waktu = "19:00 - 23:00",
    hargaTiket = 150000,
    stokTiket = 300,
    listArtis = listOf(artis13, artis14, artis15)
)