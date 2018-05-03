package com.example.hore.rentalpelanggan.MenuInfoKesehatan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hore.rentalpelanggan.R;


public class TipsOlahraga extends Fragment {
    public ListView lvItem;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        String[] level = new String[]{
                "Cara Melakukan Olahraga yang Baik dan Benar",
                "Macam-macam Olahraga mudah",
                "Program Menurunkan Berat Badan",
                "Program Membentuk Badan",
                "Program Olahraga Ibu Hamil"};

        final View v = inflater.inflate(R.layout.fragment_tips_olahraga, container, false);

        lvItem = (ListView) v.findViewById(R.id.list_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
            getActivity(),
                android.R.layout.simple_list_item_1,
                level
        );

        lvItem.setAdapter(listViewAdapter);


        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent a = new Intent(getActivity().getApplication(), DetailTipsOlahraga.class);
                switch (position) {
                    case 0:
                        a.putExtra("judul1", "Pemanasan");
                        a.putExtra("isi1", "Setiap akan berolahraga wsatu hal yang sangat penting untuk dilakukan yaitu tentu saja adalah pemanasn. Pemanasan ini bisa berupa gerakan-gerakan ringan atau peregangan-peregangan otot yang bisa dilakukan. Karena tanpa adanya pemanasan tubuh bisa menjadi terkejut ketika langsung berolahraga. Apalagi untuk olahraga yang berat, ini justru bisa berbahaya dan resiko terjadinya cedera bisa sangat tinggi.\n" +
                                "\n" +
                                "Bahkan tubuh sehat yang dirasakan sebelumnya justru menjadi cedera atau sakit, jadi jangan melupakan sesi pemanasan. Bahaya olahraga tanpa pemanasan nantinya akan berdampak buruk seperti pegal-pegal, atau bahkan terkilir. Contoh olahraga yang membakar kalori seperti lari, sepakbola, basktet dan bulu tangkis.");
                        a.putExtra("judul2", "Peralatan dan Perlengkapan");
                        a.putExtra("isi2", "Untuk peralatan tentu saja anda harus memastikan peralatan yang akan digunakan misalnya memeriksa komponen-komponen jika anda mengguanakan alat berat atau angkat beban. Jangan sampai karena alat yang mendadak rusak ini mencederai anda. Kemudia gunakan peralatan yang sesuai misalnya sarung tangan khusus untuk agkat beban agar tidak licin dan tidak lepas. Atau pelindung kaki saat bermain sepakbola, atau pelindung gigi saat tinju, jadi cek peralatan dan gunakan perlengkapan yang sesuai.");
                        a.putExtra("judul3", "Jaga Kesehatan");
                        a.putExtra("isi3", "Meskipun olahraga dilakukan untuk kesehatan janga pernah melakukan olahraga ketika sedang sakit, karena ini bisa semakin membuat lemah, jadi ketika sakit maka yang terbaik dilakukan adalah melakukan penyembuhan dan juga istirahat setelah tubuh sudah fit dan sehat kembali maka bisa dilakukan olahraga.");
                        a.putExtra("judul4", "Teratur");
                        a.putExtra("isi4", "Apapun itu tentu akan baik untuk dilakukan secara teratur. Bukan dengan mengabungkan atau memforsirnya dalam satu waktu, maka sebaiknya lakukan oahraga secara teratur misalnya 15 menit, 30 menit, atau selama satu jam setiap hari, atau tiga kali dalam seminggu.");
                        startActivity(a);
                        break;
                    case 1:
                        Intent b = new Intent(getActivity().getApplication(), DetailTipsOlahraga.class);
                        b.putExtra("judul1", "Jalan Cepat");
                        b.putExtra("isi1", "Ini merupakan salah satu jenis olahraga sederhana yang dapat anda lakukan setiap harinya. Luangkan waktu anda sejenak setiap pagi untuk berjalan kaki atau jika jarak sekolah atau tempat anda bekerja cukup dekat, anda dapat berjalan kaki untuk mencapai sekolah atau tempat anda bekerja. ");
                        b.putExtra("judul2", "Lari");
                        b.putExtra("isi2", "Mungkin ini salah satu olahraga yang tidak sulit anda lakukan. Anda bisa meluangkan waktu di pagi hari untuk melakukan jenis olahraga yang satu ini. Olahraga ini sangat baik untuk masa otot anda dan juga membakar kalori dalam tubuh anda.  ");
                        b.putExtra("judul3", "Naik Turun Tangga");
                        b.putExtra("isi3", "Jika rumah Anda memiliki dua lantai, Anda bisa memanfaatkan tangga yang ada untuk berolahraga. Olahraga ini, seperti olahraga jogging. Anda cukup berjalan menaiki dan menuruni tangga seperti jalan biasa. Lakukan gerakan ini secara berulang dan berirama.\n" +
                                "\n" +
                                "Menjelang akhir olahraga, tempo berjalan sedikit lebih cepat seperti berlari. Olahraga ini bertujuan melatih otot-otot kaki.");
                        b.putExtra("judul4", "Senam Aerobik");
                        b.putExtra("isi4", "Olahraga ini juga sangat mudah anda lakukan setiap harinya. Jika di iringi musik olahraga ini juga makin menyenangkan untuk dilakukan setiap harinya.Olahraga ini juga dapat dilakukan oleh semua orang di berbagai jenjang umur dari anak-anak hingga lansia. Manfaatnya sangat baik untuk kesehatan tubuh anda dan menjaga kebugaran anda setiap harinya.  ");
                        b.putExtra("judul5", "Bersepedah");
                        b.putExtra("isi5", "Olahraga ini juga sangat mudah dilakukan oleh semua orang di berbagai jenjang umur. Anda dapat melakukannya setiap pagi menjelang aktivitas padat anda setiap harinya. Manfaatnya juga sangat baik untuk menjaga kondisi dan kesehatan dan kebugaran tubuh anda. ");
                        startActivity(b);
                        break;

                    case 2:
                        Intent c = new Intent(getActivity().getApplication(), DetailTipsOlahraga.class);
                        c.putExtra("judul1", "Banyak Bergerak atau Olahraga");
                        c.putExtra("isi1", "Bagi anda yang memiliki banyak waktu tentunya bisa memulai program penurunan badan ini dengan melakukan olahraga secara teratur baik itu joging, senam aerobik, maupun jalan santai. Namun bagi anda yang tidak memiliki waktu luang untuk melakukan olah raga anda juga tidak perlu khawatir karena ada cara lain yang bisa ditempuh.\n" +
                                "\n" +
                                "Caranya yaitu anda harus banyak bergerak, misalnya jika pekerjaan anda berada dikantor yang memiliki lif maka bisa mencoba menggunakan tangga, selain itu terapkan untuk sering berjalan-jalan di tempat kerja. Hal itu akan membantu membakar kalori dalam tubuh. Intinya anda harus banyak bergerak dan beraktifitas yang membuat tubuh bergerak.");
                        c.putExtra("judul2", "Konsumsi Protein");
                        c.putExtra("isi2", "Protein merupakan salah satu zat penunjang diet yang sangat dianjurkan. Usut punya usut ternyata proten memiliki fungsi yang luar biasa yaitu mampu membakar lemak dan cocok sebagai penunjang diet sehat anda.\n" +
                                "\n" +
                                "Protein bisa diperoleh dari putih telur, selain itu bisa juga di dapat dari oat meal gandum maupun sereal. Konsumsi protein ini sangat di anjurkan sebagai sarapan pagi anda.");
                        c.putExtra("judul3", "Makan Makanan Kaya Serat");
                        c.putExtra("isi3", "Sudah menjadi rahasia umum bahwa makanan berserat termasuk salah satu kunci keberhasilan dalam cara menurunkan berat badan anda. Konsumsi makanan berserat ini dianjurkan untuk dikonsumsi di siang hari atau malam hari.\n" +
                                "\n" +
                                "Makan berserat tinggi ini bisa didapat dari buah dan sayur sayuran. Buah mislanya pir, pisang, pepaya, dan jambu biji. Sayur misalnya brokoli, labu, bayam, lobak, kentang, ubi jalar, kembang kol, kubis merah, dan sayur-sayuran hijau.");
                        c.putExtra("judul4", "Ganti Makanan Karbohidrat Sederhana dengan Karbohidrat Kompleks");
                        c.putExtra("isi4", "Karbihidrat merupakan sumber dari energi dan gula, namun jenis karbohidrat sederhana akan susah diserap oleh tubuh sehingga cenderung untuk ditimbun dalam dalam bentuk lemak. Makanan berkarbohidrat sederhana ini biasanya bersumber dari nasi, mie, pasta, dan lain-lain.\n" +
                                "\n" +
                                "Bagi mereka yang menjalanan program penurunan badan maka wajib hukumnya mengganti makanan berkabohidrat sederhana dengan makanan berkabohidrat kompleks misalnya beras merah, gandum, oat meal, sereal, dan jenis kacang-kacangan.");
                        c.putExtra("judul5", "Perhatikan Makan Malam Anda");
                        c.putExtra("isi5", "Dimalam hari tubuh cenderung lebih sedikit beraktifitas sehingga asupan karbohidrat tidak terlalu dibutuhkan. Maka dari itu agar penurunan berat badan anda berhasil sebaiknya hindari makan malam dengan makanan yang mengandung karbohidrat. Ada baiknya makan malam diganti dengan menu berupa sayur-sayuran yang direbus bukan yang ditumis dengan minyak.\n" +
                                "\n" +
                                "Tambahkan buah-buahan sebagai asupan gizi untuk menu makan malam. Jangan lupa juga untuk menghindari makan makanan yang dioleh dengan di goreng agar cara menurunkan berat badan ini memberikan hasil yang maksimal.");
                        startActivity(c);
                        break;
                    case 3:
                        Intent d = new Intent(getActivity().getApplication(), DetailTipsOlahraga.class);
                        d.putExtra("judul1", "Push Up");
                        d.putExtra("isi1", "Saat melakukan push up, beberapa otot tubuh pada bagian bahu depan, dada dan lengan yang bekerja sangat kuat. Gerakan push up dilakukan secara rutin, maka otot bekerja untuk 3 sistem tubuh tersebut akan terlatih. Pada bagian bisep dan trisep lengan otot akan menarik kekuatan tubuh dengan cara yang sangat menarik. Semua bagian otot penggerak akan bekerja untuk bagian  dada saat badan melakukan gerakan naik dan turun.\n" +
                                "\n" +
                                "Selain itu, bagian lengan belakang yang banyak memiliki serabut otot kecil akan terlatih sehingga bisa menjadi lebih kuat. Jika dilakukan secara rutin maka otot pada bagian dada, lengan dan bahu akan terbentuk secara alami dan menunjang perkembangan otot di sekitar badan bagian atas.");
                        d.putExtra("judul2", "Squats");
                        d.putExtra("isi2", "Latihan ini dilakukan secara rutin agar mendapatkan otot betis yang kuat, Untuk mendapatkan hasil yang cepat tambahkan beban di bahu agar mendapatkan tekanan yang cukup besar untuk otot betis anda sehingga perlu ekstra dalam menjaga keseimbangan tubuh dan ekstra tenaga bagi otot betis.");
                        d.putExtra("judul3", "Skipping");
                        d.putExtra("isi3", "Skipping atau lompat tali bisa dilakukan kapan saja, lakukan setiap hari minimal 20 menit untuk mendapatkan otot betis yang kuat, skipping juga di yakini dapat menurunkan berat badan dengan cepat.");
                        d.putExtra("judul4", "Sprint");
                        d.putExtra("isi4", "Sprint atau Berlari Cepat sangat berguna dalam membentuk otot kaki terutama otot betis, dengan sprint tubuh bekerja dalam mendorong dan memberikan tekanan pada kaki hingga ke bagian lengan, sprint sangat cepat dalam membentuk otot kaki.");
                        d.putExtra("judul5", "Leg Press Machine");
                        d.putExtra("isi5", "Leg press machine bermanfaat untuk mengembangkan otot bagian paha dan otot kaki bagian lainnya, berbagai variasi dapat di lakukan dalam latihan ini untuk mendapatkan hasil pada otot kaki yang lainnya. Ambil posisi duduk di kursi dan mulailah dengan beban ringan terlebih dahulu dan tambahkan beban sedikit demi sedikit hingga batas kekuatan otot betis anda. Dorong secara perlahan dan rasakan gerakan tersebut, lalu ulangi hingga beberapa kali.");
                        startActivity(d);
                        break;
                    case 4:

                        Intent e = new Intent(getActivity().getApplication(), DetailTipsOlahraga.class);
                        e.putExtra("judul1", "Jalan Kaki");
                        e.putExtra("isi1", "Dengan menggunakan alat ini, ibu bisa melakukannya di rumah atau di pusat kebugaran. Tak perlu berlari, ibu bisa memilih mode berjalan pelan. Berjalan disarankan bagi ibu hamil yang memasuki trimester kedua. Jika ibu lebih suka melakukan jalan kaki di luar ruangan, sebaiknya hindari waktu-waktu ketika polusi udara tinggi. Sebaiknya memilih pagi hari ketika udara masih bersih dan segar.\n");
                        e.putExtra("judul2", "Aquarobics");
                        e.putExtra("isi2", "Jenis olahraga yang satu ini merupakan jenis  olahraga kebugaran yang tepat untuk ibu hamil.  Rosa Pereira yang melakukan penelitian di Uniersitas Campinas, Brazil menemukan baha sekeitar 73 persen ibu hamil yang menggikuti aerobik ini tidak memerlukan obat penghilang rasa sakit (analgesik) ketika persalinan. Alasan mengapa olahraga ini baik juga untuk hamil adalah karena dibandingkan olahraga lainnya, olahraga ini memiliki risiko yang rendah untuk menyebabkan detak jantung berdebar lebih kencang.");
                        e.putExtra("judul3", "Renang");
                        e.putExtra("isi3", "Ibu hamil yang memilih berenang sebagai olahraga dapat dikatakan berisiko kecil mengalami cedera. Beban tubuh yang ringan ketika terapung bisa membuat kaki beristirahat tanpa perlu bekerja keras untuk menopang tubuh yang semakin berat. Slain itu, air juga mmebantu untuk meringankan tekanan pada tulang, sendi dan ligamen serta memberikan tekanan optimal pada otot dan sistm kardiovaskular ketika hamil. Namun yang perlu diperhatikan ibu sebelum berenang adalah kondisi kolam. Pastikan jika lantai dan air kolam dalam keadaan bersih dan tidak licin. Lalu juga pastikan suhu air kolam lebih tinggi sehingga ibu tidak merasa lapar berlebihan sesudah berenang.");
                        e.putExtra("judul4", "Gym");
                        e.putExtra("isi4", "Jika sebelum hamil ibu termasuk yang akti berolahraga di pusat kebugaran, maka tak ada salahnya untuk mengikuti program olahraga khusus ibu hamil. Biasanya, ada personal trainer khusus yang akan membantu ibu hamil berolahraga. Tentu saja setiap gerakannya aman dan diawasi ahli sehingga tidak berdampak negatif pada kehamilan. Biasanya, program olahraga di pusat kebugaran ini diperuntukkan untuk ibu hamil di trimester kedua.");
                        e.putExtra("judul5", "Senam Kegel");
                        e.putExtra("isi5", "Mendekati tanggal persalinan, senam kegel dapat membantu menguatkan otot panggul atau disebut juga otot PC. Bagian tubuh ini penting bagi wanita karena disitulah tempat berkumpulnya otot yang bertugas menyangga organ penting. Kandung kemih, rahim, serta rektum adalah organ yang disangga oleh otot PC agar dapat bekerja dengan baik. Untuk melakukan jenis olahraga ini, sebaiknya minta saran dari dokter dan ahli agar tidak salah bergerak.");
                        startActivity(e);
                        break;
                }


                //memanggil set on Item ClickListener untuk Listview, jadi jika salah satu item list view diklik akan
                //akan bereaksi menampilkan toast atau aksi lainya.
                //Step 4
            }
        });
        return v;
    }

}

