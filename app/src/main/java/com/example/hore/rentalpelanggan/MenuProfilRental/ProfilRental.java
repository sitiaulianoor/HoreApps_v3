package com.example.hore.rentalpelanggan.MenuProfilRental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hore.rentalpelanggan.MenuPencarian.RentalModel;
import com.example.hore.rentalpelanggan.MenuUlasan.DaftarUlasan;
import com.example.hore.rentalpelanggan.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilRental extends AppCompatActivity {
    ImageView imageView;
    TextView textViewNamaRental, textViewAlamatRental, textViewTelpRental, textViewEmailRental;
    DatabaseReference mDatabase;
    Button buttonLihatPenilaian;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Profil Rental");
        setContentView(R.layout.activity_profil_rental);

        imageView = (ImageView)findViewById(R.id.imageView);
        textViewNamaRental = (TextView)findViewById(R.id.textViewNamaRental);
        textViewAlamatRental = (TextView)findViewById(R.id.textViewAlamatRental);
        textViewTelpRental = (TextView)findViewById(R.id.textViewTelpRental);
        textViewEmailRental = (TextView)findViewById(R.id.textViewEmailRental);
        buttonLihatPenilaian = (Button)findViewById(R.id.btnLihatPenilaian);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        buttonLihatPenilaian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idRental = getIntent().getStringExtra("idRental");
                Bundle bundle = new Bundle();
                Intent intent = new Intent(ProfilRental.this, DaftarUlasan.class);
                bundle.putString("idRental", idRental);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        infoRental();
    }

    public void infoRental() {
        final String idRental = getIntent().getStringExtra("idRental");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("rentalKendaraan").child(idRental).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                RentalModel dataRental = dataSnapshot.getValue(RentalModel.class);
                textViewNamaRental.setText(dataRental.getNama_rental());
                textViewAlamatRental.setText(dataRental.getAlamat_rental());
                textViewTelpRental.setText(dataRental.getNotelfon_rental());
                textViewEmailRental.setText(dataRental.getEmailRental());
                Glide.with(getApplication()).load(dataRental.uriFotoProfil).into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
