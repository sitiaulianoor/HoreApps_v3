package com.example.hore.rentalpelanggan.MenuInfoKesehatan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.hore.rentalpelanggan.R;

public class DetailTipsOlahraga extends AppCompatActivity {

    TextView a1, a2, b1, b2, c1, c2, d1, d2, e1, e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tips Olahraga");
        setContentView(R.layout.activity_detail_tips_olahraga);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        a1 = findViewById(R.id.judul1);
        a2 = findViewById(R.id.isi1);
        b1 = findViewById(R.id.judul2);
        b2 = findViewById(R.id.isi2);
        c1 = findViewById(R.id.judul3);
        c2 = findViewById(R.id.isi3);
        d1 = findViewById(R.id.judul4);
        d2 = findViewById(R.id.isi4);
        e1 = findViewById(R.id.judul5);
        e2 = findViewById(R.id.isi5);

        Intent intent = getIntent();

        String Ajudul = intent.getStringExtra("judul1");
        String Aisi = intent.getStringExtra("isi1");
        String Bjudul = intent.getStringExtra("judul2");
        String Bisi = intent.getStringExtra("isi2");
        String Cjudul = intent.getStringExtra("judul3");
        String Cisi = intent.getStringExtra("isi3");
        String Djudul = intent.getStringExtra("judul4");
        String Disi = intent.getStringExtra("isi4");
        String EJudul = intent.getStringExtra("judul5");
        String Eisi = intent.getStringExtra("isi5");

        a1.setText(Ajudul);
        a2.setText(Aisi);
        b1.setText(Bjudul);
        b2.setText(Bisi);
        c1.setText(Cjudul);
        c2.setText(Cisi);
        d1.setText(Djudul);
        d2.setText(Disi);
        e1.setText(EJudul);
        e2.setText(Eisi);
    }
}