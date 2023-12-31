import com.example.ticketingta.model.Artis
import com.example.ticketingta.model.Event

val artis1 = Artis(
    idArtis = 1,
    nama = "Andmesh Kamaleng",
    foto = "andmesh",
    deskripsi = "Penyanyi terkenal Indonesia dengan banyak penghargaan."
)

val artis2 = Artis(
    idArtis = 2,
    nama = "NOAH",
    foto = "noah",
    deskripsi = "Band rock populer dengan lagu-lagu hits."
)

val artis3 = Artis(
    idArtis = 3,
    nama = "Raisa",
    foto = "raisa",
    deskripsi = "Penyanyi dan pencipta lagu berbakat."
)

val artis4 = Artis(
    idArtis = 4,
    nama = "Bunga Citra Lestari",
    foto = "bcl.jpeg",
    deskripsi = "Penyanyi perempuan asal Indonesia."
)


val artis5 = Artis(
    idArtis = 5,
    nama = "Isyana Sarasvati",
    foto = "isyana",
    deskripsi = "Penyanyi, pianis, dan aktris berbakat."
)

val artis6 = Artis(
    idArtis = 6,
    nama = "Dewa 19",
    foto = "dewa19",
    deskripsi = "Band terkenal di Indonesia dengan fanbase besar."
)

val artis7 = Artis(
    idArtis = 7,
    nama = "Febi Putri",
    foto = "febiputri",
    deskripsi = "Penyanyi dengan lagu-lagu indie."
)

val artis8 = Artis(
    idArtis = 8,
    nama = "Mahalini",
    foto = "mahalini",
    deskripsi = "Penyanyi berbakat dari Indonesia."
)

val artis9 = Artis(
    idArtis = 9,
    nama = "Judika",
    foto = "judika",
    deskripsi = "Penyanyi pop Indonesia dengan suara merdu."
)

val artis10 = Artis(
    idArtis = 10,
    nama = "GAC (Gamaliel Audrey Cantika)",
    foto = "gac",
    deskripsi = "Grup musik pop Indonesia dengan harmoni vokal yang indah."
)

val artis11 = Artis(
    idArtis = 11,
    nama = "Tulus",
    foto = "tulus",
    deskripsi = "Penyanyi Indonesia dengan lagu-lagu hits."
)

val artis12 = Artis(
    idArtis = 12,
    nama = "Nadin Amizah",
    foto = "nadin",
    deskripsi = "Penyanyi perempuan Indonesia dengan gaya vokal unik."
)

val artis13 = Artis(
    idArtis = 13,
    nama = "Ari Lasso",
    foto = "arilasso",
    deskripsi = "Penyanyi pria Indonesia yang dikenal dengan lagu-lagu emosional."
)

val artis14 = Artis(
    idArtis = 14,
    nama = "RAN",
    foto = "ran",
    deskripsi = "Grup musik pop Indonesia dengan lagu-lagu yang catchy."
)

val artis15 = Artis(
    idArtis = 15,
    nama = "Rossa",
    foto = "rossa",
    deskripsi = "Penyanyi pop Indonesia dengan suara yang khas."
)





val eventMusik = Event(
    idEvent = 1,
    nama = "SYNCHRONIZE FESTIVAL",
    bannerEvent = "snychronize",
    jenisEvent = "Musik",
    deskripsi = "Nikmati malam yang penuh dengan musik spektakuler di konser ini. Artis terkenal dari berbagai genre musik akan tampil, termasuk rock, pop, dan hip-hop. Pastikan Anda hadir untuk pengalaman musik yang tak terlupakan!",
    tanggal = "2023-10-20",
    lokasi = "ICE BSD, Tanggerang",
    gambarMapLokasi = "icebsd",
    waktu = "19:00 - 23:00",
    hargaTiket = 150000,
    stokTiket = 1000,
    listArtis = listOf(artis1, artis2, artis3)
)

val eventMusik2 = Event(
    idEvent = 2,
    nama = "JAVA JAZZ",
    bannerEvent = "javajazz",
    jenisEvent = "Musik",
    deskripsi = "Saksikan festival jazz terbesar tahun ini dengan pemain jazz terbaik dari seluruh dunia. Dari jazz klasik hingga fusion modern, Anda akan mendengarkan musik berkualitas tinggi sepanjang hari.",
    tanggal = "2023-11-15",
    lokasi = "JIEXPO Kemayoran",
    gambarMapLokasi = "jiexpo",
    waktu = "12:00 - 22:00",
    hargaTiket = 120000,
    stokTiket = 800,
    listArtis = listOf(artis4, artis5, artis6)
)


val eventMusik3 = Event(
    idEvent = 3,
    nama = "SOUNDRENALINE",
    bannerEvent = "soundrenaline",
    jenisEvent = "Musik",
    deskripsi = "Sambut konser rock legendaris ini yang akan menggetarkan panggung dengan musik rock paling keras sepanjang masa. Siapkan diri Anda untuk malam yang tak terlupakan bersama legenda rock!",
    tanggal = "2023-12-10",
    lokasi = "Eco Park Ancol, Jakarta Utara",
    gambarMapLokasi = "ecoparkancol",
    waktu = "19:00 - 23:00",
    hargaTiket = 150000,
    stokTiket = 600,
    listArtis = listOf(artis7, artis8, artis9)
)


val eventMusik4 = Event(
    idEvent = 4,
    nama = "Djakarta Warehouse Project",
    bannerEvent = "dwp",
    jenisEvent = "Musik",
    deskripsi = "Djakarta Warehouse Project 2022: Tempat Pesta Penggemar EDM Mancanegara. Djakarta Warehouse Project (DWP) 2022 telah memeriahkan Jakarta pada 9-11 Desember 2022 dengan membawa musisi EDM ternama secara offline di Jiexpo Kemayoran.",
    tanggal = "2023-11-15",
    lokasi = "Panggung Jazz Terkenal",
    gambarMapLokasi = "map_panggung_jazz",
    waktu = "20:00 - 00:00",
    hargaTiket = 175000,
    stokTiket = 400,
    listArtis = listOf(artis10, artis11, artis12)
)


val eventMusik5 = Event(
    idEvent = 5,
    nama = "HeyFest",
    bannerEvent = "heyfest",
    jenisEvent = "Musik",
    deskripsi = "Heyfest! merupakan festival musik tahunan yang diadakan di Kota Bekasi sebagai tuan rumah setiap tahunnya",
    tanggal = "2023-12-10",
    lokasi = "Summarecon Mall Bekasi, Bekasi",
    gambarMapLokasi = "smb",
    waktu = "19:00 - 23:00",
    hargaTiket = 150000,
    stokTiket = 300,
    listArtis = listOf(artis13, artis14, artis15)
)

val dummyEventList : List<Event> = listOf(eventMusik,eventMusik2, eventMusik3, eventMusik4, eventMusik5)