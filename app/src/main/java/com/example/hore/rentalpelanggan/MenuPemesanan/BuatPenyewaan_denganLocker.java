package com.example.hore.rentalpelanggan.MenuPemesanan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hore.rentalpelanggan.Autentifikasi.PelangganModel;
import com.example.hore.rentalpelanggan.MainActivity;
import com.example.hore.rentalpelanggan.MenuPembayaran.DaftarRekeningPembayaran;
import com.example.hore.rentalpelanggan.MenuPencarian.TempatModel;
import com.example.hore.rentalpelanggan.R;
import com.example.hore.rentalpelanggan.SisaTempatModel;
import com.example.hore.rentalpelanggan.Utils.ShowAlertDialog;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class BuatPenyewaan_denganLocker extends AppCompatActivity {
    private FirebaseAuth auth;
    TextView textViewIdentitasPelanggan, textViewNamaPelanggan, textViewAlamatPelanggan, textViewNomorTelponPelanggan,
    textViewEmailPelanggan;
    EditText editTextLokasiPenjemputan, editTextKeteranganKhusus;
    Spinner spinnerJamPenjemputan;
    Button buttonBuatPesanan;
    String idPelanggan, jamPenjemputan, idPenyewaan;
    DatabaseReference mDatabase;
    double latitude_penjemputan, longitude_penjemputan;
    boolean statusUlasan;
    private boolean isSpinnerTouched = false;
    int jmlKendaraanPencarian;
    boolean sisa;
    Date tanggalSewaPencarian, tanggalKembaliPencarian, tglSewaDipesan, tglKembaliDipesan;
    int sisaKendaraan;
    ProgressDialog progressDialog;


    private final static int PLACE_PICKER_REQUEST = 2;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Buat Sewa");
        setContentView(R.layout.activity_buat_pesanan2_dengan_supir);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        textViewIdentitasPelanggan = (TextView)findViewById(R.id.textViewIdentitasPelanggan);
        textViewNamaPelanggan = (TextView)findViewById(R.id.textViewNamaLengkap);
        textViewAlamatPelanggan = (TextView)findViewById(R.id.textViewAlamatLengkap);
        textViewNomorTelponPelanggan = (TextView)findViewById(R.id.textViewNomorTelpon);
        textViewEmailPelanggan = (TextView)findViewById(R.id.textViewEmail);
        editTextLokasiPenjemputan = (EditText)findViewById(R.id.editTextLokasiPenjemputan);
        editTextKeteranganKhusus = (EditText)findViewById(R.id.editTextKeteranganKhusus);
        buttonBuatPesanan = (Button)findViewById(R.id.btnBuatPesanan);
        progressDialog = new ProgressDialog(BuatPenyewaan_denganLocker.this);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idPelanggan = user.getUid();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //list dialog spinner jam penjemputan
        ArrayList<String> listJam = new ArrayList<>();
        listJam.add(new String("05.00"));
        listJam.add(new String("06.00"));
        listJam.add(new String("07.00"));
        listJam.add(new String("08.00"));
        listJam.add(new String("09.00"));
        listJam.add(new String("10.00"));
        listJam.add(new String("11.00"));
        listJam.add(new String("12.00"));
        spinnerJamPenjemputan = (Spinner) findViewById(R.id.spinnerWaktuPenjemputan);
        SpinnerAdapterJam adapterJam = new SpinnerAdapterJam(getApplicationContext(), R.layout.spinner_jam_layout, R.id.txtspinnerJam, listJam) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) itemView.findViewById(R.id.txtspinnerJam)).setText("");
                }
                return itemView;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        spinnerJamPenjemputan.setAdapter(adapterJam);
        spinnerJamPenjemputan.setSelection(adapterJam.getCount());
        spinnerJamPenjemputan.setPrompt("Jam Penjemputan");
        spinnerJamPenjemputan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouched = true;
                return false;
            }
        });
        spinnerJamPenjemputan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!isSpinnerTouched){
                    String jam = null;
                    jamPenjemputan = jam;
                }else {
                    String jam = adapterView.getItemAtPosition(i).toString();
                    jamPenjemputan = jam;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonBuatPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cekSisa()) {
                    buatSewa();
                    kelolaSisa();
                }
            }
        });

        editTextLokasiPenjemputan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lokasiPenjemputan();
            }
        });

        infoPelanggan();
    }

    public void infoPelanggan(){
        mDatabase.child("pelanggan").child(idPelanggan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PelangganModel pelanggan = dataSnapshot.getValue(PelangganModel.class);
                textViewIdentitasPelanggan.setText(pelanggan.getNoIdentitas());
                textViewNamaPelanggan.setText(pelanggan.getNamaLengkap());
                textViewAlamatPelanggan.setText(pelanggan.getAlamat());
                textViewNomorTelponPelanggan.setText(pelanggan.getNoTelp());
                textViewEmailPelanggan.setText(pelanggan.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public  void buatSewa() {
        progressDialog.setMessage("Harap tunggu..."); // Setting Message
        progressDialog.setTitle("Memproses Penyewaan"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        final String idRental = getIntent().getStringExtra("idRental");
        final String idKendaraan = getIntent().getStringExtra("idKendaraan");
        final String kategoriKendaraan = getIntent().getStringExtra("kategoriKendaraan");
        final String jumlahKendaraanPencarian = getIntent().getStringExtra("jumlahKendaraanPencarian");
        final String tglSewaPencarian = getIntent().getStringExtra("tglSewaPencarian");
        final String tglKembaliPencarian = getIntent().getStringExtra("tglKembaliPencarian");
        final int jumlahHariPenyewaan = getIntent().getIntExtra("jumlahHariPenyewaan", 0);
        final double totalBiayaPembayaran = getIntent().getDoubleExtra("totalBiayaPembayaran", 0);
        final int jmlKendaraanPencarian = Integer.valueOf(jumlahKendaraanPencarian);
        final String idRekeningRental = "";
        statusUlasan = false;
        String keteranganKhusus = editTextKeteranganKhusus.getText().toString();
        String alamatPenjemputan = editTextLokasiPenjemputan.getText().toString();

        Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 1);
        Date a = calendar.getTime();
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        String batasWaktuPembayaran = df.format(a);

        final String id = mDatabase.push().getKey();
        idPenyewaan = id;
        String statusPemesanan1 = "Belum Bayar";
        String currentDate = DateFormat.getDateTimeInstance().format(new Date());

        if (cekKolomIsian()) {
                final PenyewaanModel dataSewa = new PenyewaanModel(id, idKendaraan, idPelanggan, idRental, statusPemesanan1, currentDate, tglSewaPencarian,
                        tglKembaliPencarian, keteranganKhusus, jamPenjemputan, jmlKendaraanPencarian, latitude_penjemputan, longitude_penjemputan, alamatPenjemputan,
                        jumlahHariPenyewaan, totalBiayaPembayaran, batasWaktuPembayaran, kategoriKendaraan, idRekeningRental, statusUlasan);

                mDatabase.child("penyewaanKendaraan").child("belumBayar").child(id).setValue(dataSewa).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Penyewaan Gagal", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Penyewaan berhasil", Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            Intent intent = new Intent(BuatPenyewaan_denganLocker.this, DaftarRekeningPembayaran.class);
                            bundle.putString("idKendaraan", idKendaraan);
                            bundle.putString("idRental", idRental);
                            bundle.putString("idPenyewaan", idPenyewaan);
                            bundle.putString("kategoriKendaraan", kategoriKendaraan);
                            bundle.putString("tglSewaPencarian", tglSewaPencarian);
                            bundle.putString("tglKembaliPencarian", tglKembaliPencarian);
                            bundle.putString("jumlahKendaraanPencarian", jumlahKendaraanPencarian);
                            bundle.putInt("jumlahHariPenyewaan", jumlahHariPenyewaan);
                            bundle.putDouble("totalBiayaPembayaran", totalBiayaPembayaran);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            //buatPemberitahuan();
                            buatPemberitahuanOnesignal();
                        }
                    }
                });
        }
    }

    private void buatPemberitahuan() {
        String idPemberitahuan = mDatabase.push().getKey();
        final String idRental = getIntent().getStringExtra("idRental");
        final String idKendaraan = getIntent().getStringExtra("idKendaraan");
        final String tglSewaPencarian = getIntent().getStringExtra("tglSewaPencarian");
        final String tglKembaliPencarian = getIntent().getStringExtra("tglKembaliPencarian");
        //int valueHalaman1 = 0;
        String valueHalaman1 = "belumBayar";
        String statusPemesanan1 = "Belum Bayar";
        HashMap<String, Object> dataNotif = new HashMap<>();
        dataNotif.put("idPemberitahuan", idPemberitahuan);
        dataNotif.put("idRental", idRental);
        dataNotif.put("idKendaraan", idKendaraan);
        dataNotif.put("tglSewa", tglSewaPencarian);
        dataNotif.put("tglKembalian", tglKembaliPencarian);
        dataNotif.put("nilaiHalaman", valueHalaman1);
        dataNotif.put("statusPenyewaan", statusPemesanan1);
        dataNotif.put("idPelanggan", idPelanggan);
        dataNotif.put("idPenyewaan", idPenyewaan);
        mDatabase.child("pemberitahuan").child("rental").child("belumBayar").child(idRental).child(idPemberitahuan).setValue(dataNotif);
        //mDatabase.child("pemberitahuan").child("rental").child("belumBayar").child(idRental).child(idPemberitahuan).child("nilaiHalaman").setValue(valueHalaman);
    }

    private void buatPemberitahuanOnesignal() {
        final String idRental = getIntent().getStringExtra("idRental");
        final String tglSewaPencarian = getIntent().getStringExtra("tglSewaPencarian");
        final String tglKembaliPencarian = getIntent().getStringExtra("tglKembaliPencarian");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            // JSON here
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic MWRlZjUzNzUtMjMwMS00NDQxLTgyMDEtYThhNmU0MDlmNTg5");
            con.setRequestMethod("POST");

            String strJsonBody = "{"
                    +   "\"app_id\": \"8d59b6c9-1cd7-4c76-8390-38a065ac6924\","
                    +   "\"filters\": [{\"field\": \"tag\", \"key\": \"UID\", \"relation\": \"=\", \"value\": \"" + idRental +"\"}],"
                    +   "\"data\": {\"statusPenyewaan\": \"belumBayar\"},"
                    +   "\"headings\": {\"en\": \"Penyewaan Belum Bayar\"},"
                    +   "\"contents\": {\"en\": \"Untuk Tanggal "+tglSewaPencarian+" - "+tglKembaliPencarian+"\"}"
                    + "}";

//            +   "\"contents\": {\"en\": \"Terdapat Penyewaan Baru untuk Tanggal "+tglSewaPencarian+" - "+tglKembaliPencarian+"\"}"


            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (  httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);
        }
        catch(Throwable t) {
            t.printStackTrace();
        }

    }

    public boolean cekKolomIsian() {
        boolean sukses = true;
        if (TextUtils.isEmpty(editTextKeteranganKhusus.getText().toString()) || jamPenjemputan == null){
            sukses = false;
            ShowAlertDialog.showAlert("Lengkapi Seluruh Kolom Isian", this);
        }
        return sukses;
    }

    public void lokasiPenjemputan() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            Intent intent = builder.build(BuatPenyewaan_denganLocker.this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public  boolean cekSisa() {
        final String idKendaraan = getIntent().getStringExtra("idKendaraan");
        final String kategoriKendaraan = getIntent().getStringExtra("kategoriKendaraan");
        final String jumlahKendaraanPencarian = getIntent().getStringExtra("jumlahKendaraanPencarian");
        final String tglSewaPencarian = getIntent().getStringExtra("tglSewaPencarian");
        final String tglKembaliPencarian = getIntent().getStringExtra("tglKembaliPencarian");
        jmlKendaraanPencarian = Integer.parseInt(jumlahKendaraanPencarian);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            tanggalSewaPencarian = format.parse(tglSewaPencarian);
            tanggalKembaliPencarian = format.parse(tglKembaliPencarian);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mDatabase.child("cekSisaKendaraan").orderByChild("idKendaraan").equalTo(idKendaraan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String idCek = idKendaraan;
                        SisaTempatModel sisaModel = postSnapshot.getValue(SisaTempatModel.class);
                        int sisaKendaraan = sisaModel.getSisaKendaraan();

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                        try {
                            tglSewaDipesan = format.parse(sisaModel.getTglSewa());
                            tglKembaliDipesan = format.parse(sisaModel.getTglKembali());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if ((tanggalSewaPencarian.before(tglKembaliDipesan) || tanggalSewaPencarian.equals(tglKembaliDipesan)) && (tanggalKembaliPencarian.after(tglSewaDipesan) || tanggalKembaliPencarian.equals(tglSewaDipesan))
                                || tanggalSewaPencarian.equals(tglSewaDipesan) && tanggalKembaliPencarian.equals(tglKembaliDipesan)) {
                            String id = sisaModel.getIdCekSisa();
                            if (sisaKendaraan > jmlKendaraanPencarian || sisaKendaraan == jmlKendaraanPencarian) {
                                boolean cek = true;
                                sisa = cek;
                                //Toast.makeText(getApplicationContext(), "kendaraan masih tersedia", Toast.LENGTH_LONG).show();
                            }
                            else {
                                boolean cek = false;
                                sisa = cek;
                                //Toast.makeText(getApplicationContext(), "kendaraan udah abis alias ga cukup", Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(), "Kendaraan sudah tidak tersedia", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(BuatPenyewaan_denganLocker.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            boolean cek = true;
                            sisa = cek;
                            //Toast.makeText(getApplicationContext(), "kendaraan masih tersedia dan tgl nya ga sama", Toast.LENGTH_LONG).show();
                        }
                    }

                } else {
                    boolean cek = true;
                    sisa = cek;
                    //Toast.makeText(getApplicationContext(), "kendaraan masih tersedia dan belum ada yg mesen sama sekali", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return sisa;
    }

    public void kelolaSisa() {
        final String idKendaraan = getIntent().getStringExtra("idKendaraan");
        final String kategoriKendaraan = getIntent().getStringExtra("kategoriKendaraan");
        final String jumlahKendaraanPencarian = getIntent().getStringExtra("jumlahKendaraanPencarian");
        final String tglSewaPencarian = getIntent().getStringExtra("tglSewaPencarian");
        final String tglKembaliPencarian = getIntent().getStringExtra("tglKembaliPencarian");
        jmlKendaraanPencarian = Integer.parseInt(jumlahKendaraanPencarian);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            tanggalSewaPencarian = format.parse(tglSewaPencarian);
            tanggalKembaliPencarian = format.parse(tglKembaliPencarian);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mDatabase.child("cekSisaKendaraan").orderByChild("idKendaraan").equalTo(idKendaraan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String idCek = idKendaraan;
                        SisaTempatModel sisaModel = postSnapshot.getValue(SisaTempatModel.class);
                        final int sisaKendaraan = sisaModel.getSisaKendaraan();
                        final String a = sisaModel.getIdCekSisa();

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                        try {
                            tglSewaDipesan = format.parse(sisaModel.getTglSewa());
                            tglKembaliDipesan = format.parse(sisaModel.getTglKembali());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if ((tanggalSewaPencarian.before(tglKembaliDipesan) || tanggalSewaPencarian.equals(tglKembaliDipesan)) && (tanggalKembaliPencarian.after(tglSewaDipesan) || tanggalKembaliPencarian.equals(tglSewaDipesan))
                                || tanggalSewaPencarian.equals(tglSewaDipesan) && tanggalKembaliPencarian.equals(tglKembaliDipesan)) {
                            if (sisaKendaraan > jmlKendaraanPencarian || sisaKendaraan == jmlKendaraanPencarian) {
                                perbaruiSisa(jmlKendaraanPencarian, a, sisaKendaraan);
                                //Toast.makeText(getApplicationContext(), "di update oiii", Toast.LENGTH_LONG).show();
                            }
                            else {
                                //Toast.makeText(getApplicationContext(), "kendaraan udah abis alias ga cukup", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            buatSisaKendaraan(jmlKendaraanPencarian);
                            //Toast.makeText(getApplicationContext(), "Buat sisa karena tanggalnya ga sama", Toast.LENGTH_LONG).show();
                        }
                    }

                } else {
                    buatSisaKendaraan(jmlKendaraanPencarian);
                    //Toast.makeText(getApplicationContext(), "Buat sisa karena ga ada sama sekali di tabel cek sisa", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void perbaruiSisa(final int jmlKendaraanPesanan, String idCek, int sisaModelKendaraan) {
        final String idKendaraan = getIntent().getStringExtra("idKendaraan");
        final String tglSewaPencarian = getIntent().getStringExtra("tglSewaPencarian");
        final String tglKembaliPencarian = getIntent().getStringExtra("tglKembaliPencarian");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            tanggalSewaPencarian = format.parse(tglSewaPencarian);
            tanggalKembaliPencarian = format.parse(tglKembaliPencarian);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int sisaKendaraan = sisaModelKendaraan - jmlKendaraanPesanan;
        mDatabase.child("cekSisaKendaraan").child(idCek).child("sisaKendaraan").setValue(sisaKendaraan);
    }

    public void buatSisaKendaraan(final int jmlKendaraanPesanan) {
        final String idKendaraan = getIntent().getStringExtra("idKendaraan");
        final String kategoriKendaraan = getIntent().getStringExtra("kategoriKendaraan");
        final String tglSewaPencarian = getIntent().getStringExtra("tglSewaPencarian");
        final String tglKembaliPencarian = getIntent().getStringExtra("tglKembaliPencarian");
        final String idCek = mDatabase.push().getKey();

        mDatabase.child("kendaraan").child(kategoriKendaraan).child(idKendaraan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TempatModel dataKendaraan = dataSnapshot.getValue(TempatModel.class);
                int jmlKendaraanModel = dataKendaraan.getJumlahKendaraan();
                int sisa = jmlKendaraanModel - jmlKendaraanPesanan;
                sisaKendaraan = sisa;
                SisaTempatModel dataSisaKendaraan = new SisaTempatModel(idCek, tglSewaPencarian, tglKembaliPencarian, idKendaraan, sisaKendaraan);
                mDatabase.child("cekSisaKendaraan").child(idCek).setValue(dataSisaKendaraan);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PLACE_PICKER_REQUEST && resultCode==RESULT_OK && data != null){
            Place place = PlacePicker.getPlace(BuatPenyewaan_denganLocker.this, data);
            latitude_penjemputan=place.getLatLng().latitude;
            longitude_penjemputan=place.getLatLng().longitude;

            this.editTextLokasiPenjemputan.setText(""+place.getAddress());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
