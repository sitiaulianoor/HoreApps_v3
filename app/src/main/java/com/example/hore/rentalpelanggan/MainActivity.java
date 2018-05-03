package com.example.hore.rentalpelanggan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hore.rentalpelanggan.Autentifikasi.Login;
import com.example.hore.rentalpelanggan.Base.DeviceToken;
import com.example.hore.rentalpelanggan.MenuInfoKesehatan.TipsOlahraga;
import com.example.hore.rentalpelanggan.MenuPencarian.MenuPencarian;
import com.example.hore.rentalpelanggan.MenuKelolaPenyewaan.MenuStatusPemesanan;
import com.example.hore.rentalpelanggan.MenuProfilPelanggan.MenuProfil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    String halamanStatus;
    String idPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_cari);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        idPelanggan = user.getUid();

        OneSignal.sendTag("UID", idPelanggan);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        String notif1 = getIntent().getStringExtra("notifBerhasil");
        String notif2 = getIntent().getStringExtra("notifSelesai");
        String notif7 = getIntent().getStringExtra("notifBatal");
        String notif8 = getIntent().getStringExtra("notifMenungguSisaPembayaran");

        int halamanStatusBelumBayar = getIntent().getIntExtra("halamanStatusBelumBayar", -1);
        int halamanStatusMenungguKonfirmasi = getIntent().getIntExtra("halamanStatusMenungguKonfirmasi", 0);
        int halamanStatusPengajuanPembatan = getIntent().getIntExtra("halamanStatusPengajuanPembatalan", 2);
        int halamanEditProfil = getIntent().getIntExtra("halamanEditProfil", 10);
        int halamanStatusMenungguKonfirmasiSisaPembayaran = getIntent().getIntExtra("halamanStatusMenungguKonfirmasiSisaPembayaran", 11);

        if (findViewById(R.id.content_frame) != null && notif1 != null && notif1.equals("berhasil") ) {
            Bundle bundle=new Bundle();
            bundle.putInt("tab3", 3);
            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
            menuStatusPemesanan.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
        } else if (findViewById(R.id.content_frame) != null && notif2 != null && notif2.equals("selesai")) {
            Bundle bundle=new Bundle();
            bundle.putInt("tab4", 4);
            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
            menuStatusPemesanan.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
        } else if (findViewById(R.id.content_frame) != null && notif7 != null && notif7.equals("batal")) {
            Bundle bundle=new Bundle();
            bundle.putInt("tab6", 6);
            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
            menuStatusPemesanan.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
        } else if (findViewById(R.id.content_frame) != null && notif8 != null && notif8.equals("menungguSisaPembayaran")) {
            Bundle bundle=new Bundle();
            bundle.putInt("tab7", 7);
            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
            menuStatusPemesanan.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
        } else if (findViewById(R.id.content_frame) != null && halamanStatusBelumBayar != -1) {
            Bundle bundle=new Bundle();
            bundle.putInt("tab1", 1);
            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
            menuStatusPemesanan.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
        } else if (findViewById(R.id.content_frame) != null && halamanStatusMenungguKonfirmasi != 0) {
            Bundle bundle=new Bundle();
            bundle.putInt("tab2", 2);
            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
            menuStatusPemesanan.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
        } else if (findViewById(R.id.content_frame) != null && halamanEditProfil != 10) {
            MenuProfil menuProfil = new MenuProfil();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuProfil).commit();
        } else if (findViewById(R.id.content_frame) != null && halamanStatusMenungguKonfirmasiSisaPembayaran != 11) {
            Bundle bundle=new Bundle();
            bundle.putInt("tab8", 8);
            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
            menuStatusPemesanan.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
        } else if (findViewById(R.id.content_frame) != null && halamanStatusPengajuanPembatan != 2) {
            Bundle bundle=new Bundle();
            bundle.putInt("tab5", 5);
            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
            menuStatusPemesanan.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
        }

//
//        int halamanStatus1 = getIntent().getIntExtra("halamanStatus", 0);
//        int halamanStatus2 = getIntent().getIntExtra("halamanStatus2", 1);
//        int halamanStatus3 = getIntent().getIntExtra("halamanStatus3", 2);
//
//        if (findViewById(R.id.content_frame) != null && halamanStatus1 != 0) {
//            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
//        } else if (findViewById(R.id.content_frame) != null && halamanStatus2 != 1) {
//            Bundle bundle=new Bundle();
//            bundle.putInt("valueHalamanStatus2", 2);
//            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
//            menuStatusPemesanan.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
//        } else if (findViewById(R.id.content_frame) != null && halamanStatus3 != 2) {
//            Bundle bundle=new Bundle();
//            bundle.putInt("valueHalamanStatus3", 3);
//            MenuStatusPemesanan menuStatusPemesanan = new MenuStatusPemesanan();
//            menuStatusPemesanan.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menuStatusPemesanan).commit();
//        }

        initData();
    }

    private void initData(){
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            signOut();
        } else {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            String token = FirebaseInstanceId.getInstance().getToken();
            DeviceToken.getInstance().addDeviceToken(mDatabase, idPelanggan, token);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void displaySelectedScreen (int itemId) {
        Fragment fragment = null; // membuat objek dari kelas fragment

        switch (itemId) {
            case R.id.nav_cari:
                fragment = new MenuPencarian();
                break;
            case R.id.nav_penyewaanku:
                fragment = new MenuStatusPemesanan();
                break;
            case R.id.nav_profilku:
                fragment = new MenuProfil();
                break;
            case R.id.nav_infokesahatan:
                fragment = new TipsOlahraga();
                break;
            case R.id.nav_keluar:
                signOut();
                break;
        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displaySelectedScreen(item.getItemId());
        return true;
    }

    public void signOut() {
        Toast.makeText(getApplicationContext(), "Anda Berhasil Logout", Toast.LENGTH_LONG).show();
        FirebaseAuth.getInstance().signOut();
//     Intent intent = new Intent(this, AutentifikasiTelepon.class);
        //Intent intent = new Intent(this, AutentifikasiTelepon.class);
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
