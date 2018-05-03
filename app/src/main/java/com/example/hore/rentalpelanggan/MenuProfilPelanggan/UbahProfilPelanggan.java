package com.example.hore.rentalpelanggan.MenuProfilPelanggan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hore.rentalpelanggan.Autentifikasi.PelangganModel;
import com.example.hore.rentalpelanggan.MainActivity;
import com.example.hore.rentalpelanggan.R;
import com.example.hore.rentalpelanggan.Utils.ShowAlertDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UbahProfilPelanggan extends AppCompatActivity {
    EditText editTextNoIdentitas, editTextNamaLengkap, editTextAlamat, editTextNotelp;
    TextView textViewEmail;
    ImageView imageViewFotoProfil;
    Button buttonSimpan;
    private String idPelanggan, emailPelanggan;

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ubah Profil");
        setContentView(R.layout.activity_ubah_profil_pelanggan);
        imageViewFotoProfil = (ImageView)findViewById(R.id.imageView);
        editTextNoIdentitas = (EditText) findViewById(R.id.editTextNoIdentitas);
        editTextNamaLengkap = (EditText) findViewById(R.id.editTextNamaLengkap);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextNotelp = (EditText) findViewById(R.id.editTextNotelp);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        buttonSimpan = (Button) findViewById(R.id.buttonSimpan);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cekUpdateData()) {
                    simpanPerubahanProfilPelanggan();
                    finish();
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idPelanggan = user.getUid();
        emailPelanggan = user.getEmail();

        getInfoPelanggan();
    }

    public void getInfoPelanggan() {
        mDatabase.child("pelanggan").child(idPelanggan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PelangganModel dataPelanggan = dataSnapshot.getValue(PelangganModel.class);
                editTextNoIdentitas.setText(dataPelanggan.getNoIdentitas());
                editTextNamaLengkap.setText(dataPelanggan.getNamaLengkap());
                editTextAlamat.setText(dataPelanggan.getAlamat());
                editTextNotelp.setText(dataPelanggan.getNoTelp());
                textViewEmail.setText(emailPelanggan);
                Glide.with(getApplication()).load(dataPelanggan.getUriFotoPelanggan()).into(imageViewFotoProfil);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void simpanPerubahanProfilPelanggan() {
        mDatabase.child("pelanggan").child(idPelanggan).child("noIdentitas").setValue(editTextNoIdentitas.getText().toString());
        mDatabase.child("pelanggan").child(idPelanggan).child("namaLengkap").setValue(editTextNamaLengkap.getText().toString());
        mDatabase.child("pelanggan").child(idPelanggan).child("alamat").setValue(editTextAlamat.getText().toString());
        mDatabase.child("pelanggan").child(idPelanggan).child("noTelp").setValue(editTextNotelp.getText().toString());
        Intent intent = new Intent(UbahProfilPelanggan.this, MainActivity.class);
        intent.putExtra("halamanEditProfil", 11);
        startActivity(intent);
    }

    private boolean cekUpdateData() {
        boolean sukses;
        if (TextUtils.isEmpty(editTextNoIdentitas.getText().toString()) || TextUtils.isEmpty(editTextNamaLengkap.getText().toString()) ||
                TextUtils.isEmpty(editTextAlamat.getText().toString()) || TextUtils.isEmpty(editTextNotelp.getText().toString()) ||
                        TextUtils.isEmpty(editTextNotelp.getText().toString())) {
            sukses = false;
            ShowAlertDialog.showAlert("Lengkapi Seluruh Kolom Isian", this);
        } else {
            sukses = true;
        }
        return sukses;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}