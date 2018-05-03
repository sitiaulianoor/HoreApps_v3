package com.example.hore.rentalpelanggan.MenuPencarian;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hore.rentalpelanggan.R;
import com.example.hore.rentalpelanggan.Utils.ShowAlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MenuPencarian extends Fragment {
    private int tgl, bln, thn;
    EditText tglSewa, tglKembali;
    Spinner spKategori, spJumlahKendaraan;
    Button btnCari;
    String kategoriKendaraan, jumlahKendaraan;
    private boolean isSpinnerTouched = false;
    String valueTglSewa = null;
    String valueTglKembali = null;
    private int pickerTglSewa, pickerBlnSewa, pickerThnSewa;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Pencarian Lapangan");
        View v = inflater.inflate(R.layout.fragment_menu_pencarian, container, false);

        tglSewa = (EditText) v.findViewById(R.id.editTextTglSewa);
        tglKembali = (EditText) v.findViewById(R.id.editTextTglKembali);
        btnCari = (Button) v.findViewById(R.id.buttonCari);

        //list dialog daftar kategori tempat
        ArrayList<String> listKategori = new ArrayList<>();
        listKategori.add(new String("Futsal"));
        listKategori.add(new String("Basket"));
        listKategori.add(new String("Kolam Renang"));
        spKategori = (Spinner) v.findViewById(R.id.spinnerKategori);
        SpinnerAdapterKategoriTempat adapterKategori = new SpinnerAdapterKategoriTempat(getContext(), R.layout.spinner_kategori_layout, R.id.txtspinner, listKategori) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) itemView.findViewById(R.id.txtspinner)).setText("");
                }
                return itemView;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        spKategori.setAdapter(adapterKategori);
        spKategori.setSelection(adapterKategori.getCount());
        spKategori.setPrompt("Kategori Tempat");
        spKategori.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouched = true;
                return false;
            }
        });
        spKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!isSpinnerTouched){
                    String kategori = null;
                    kategoriKendaraan = kategori;
                }else {
                    String kategori = adapterView.getItemAtPosition(i).toString();
                    kategoriKendaraan = kategori;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> listJumlahKendaraan = new ArrayList<>();
        listJumlahKendaraan.add(new String("1"));
        listJumlahKendaraan.add(new String("2"));
        listJumlahKendaraan.add(new String("3"));
        listJumlahKendaraan.add(new String("4"));
        listJumlahKendaraan.add(new String("5"));
        spJumlahKendaraan = (Spinner) v.findViewById(R.id.spinnerJumlahKendaraan);
        SpinnerAdapterJumlahTempat adapterJumlahKendaraan = new SpinnerAdapterJumlahTempat(getContext(), R.layout.spinner_jumlah_tempat_layout, R.id.txtspinnerJumlahKendaraan, listJumlahKendaraan) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) itemView.findViewById(R.id.txtspinnerJumlahKendaraan)).setText("");
                }
                return itemView;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        spJumlahKendaraan.setAdapter(adapterJumlahKendaraan);
        spJumlahKendaraan.setSelection(adapterJumlahKendaraan.getCount());
        spJumlahKendaraan.setPrompt("Jumlah Tempat");
        spJumlahKendaraan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouched = true;
                return false;
            }
        });
        spJumlahKendaraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!isSpinnerTouched){
                    String jmlKendaraan = null;
                    jumlahKendaraan = jmlKendaraan;
                }else {
                    String jmlKendaraan = adapterView.getItemAtPosition(i).toString();
                    jumlahKendaraan = jmlKendaraan;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        tglSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                final Calendar cal = Calendar.getInstance();
                pickerTglSewa = calendar.get(Calendar.DAY_OF_MONTH);
                pickerBlnSewa = calendar.get(Calendar.MONTH);
                pickerThnSewa = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tglSewa.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        valueTglSewa = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year; // 15/3/2018

                    }
                }
                        , pickerTglSewa, pickerBlnSewa, pickerThnSewa);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                cal.add(Calendar.MONTH, 5);
                datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
                datePickerDialog.show();

            }
        });

        tglKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valueTglSewa == null) {
                    ShowAlertDialog.showAlert("Silahkan Pilih Tanggal Sewa Terlebih Dahulu", getContext());
                } else {
                    final Calendar calendar = Calendar.getInstance();
                    final Calendar cal = Calendar.getInstance();
                    tgl = calendar.get(Calendar.DAY_OF_MONTH);
                    bln = calendar.get(Calendar.MONTH);
                    thn = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            tglKembali.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            valueTglKembali = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                        }
                    }
                            , pickerTglSewa, pickerBlnSewa, pickerThnSewa);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date convertedDate = new Date();
                    try {
                        convertedDate = dateFormat.parse(valueTglSewa);
                        Long longDate = convertedDate.getTime();
                        datePickerDialog.getDatePicker().setMinDate(longDate);
                        datePickerDialog.show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
//                cal.add(Calendar.MONTH, 5);
//                datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
//                datePickerDialog.show();

//                cal.set(pickerThnSewa, pickerBlnSewa, pickerThnSewa);
//                Date a = cal.getTime();
//                Long b = a.getTime();
//                datePickerDialog.getDatePicker().setMinDate(b);
//                datePickerDialog.show();

            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cekKolomIsian()) {
                    cariKendaraan();
                    getActivity().finish();
                }
            }
        });

        return v;
    }

    public void cariKendaraan() {
        String tglSewaPencarian = tglSewa.getText().toString().trim();
        String tglKembaliPencarian = tglKembali.getText().toString().trim();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getActivity(), MenuHasilPencarian.class);
        bundle.putString("tglSewaPencarian", tglSewaPencarian);
        bundle.putString("tglKembaliPencarian", tglKembaliPencarian);
        bundle.putString("kategoriKendaraanPencarian", kategoriKendaraan);
        bundle.putString("jumlahKendaraanPencarian", jumlahKendaraan);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private boolean cekKolomIsian(){
        boolean sukses = true;
        if ( jumlahKendaraan == null || kategoriKendaraan == null || valueTglSewa == null ||
                valueTglKembali == null){
            sukses = false;
            ShowAlertDialog.showAlert("Lengkapi Seluruh Kolom Isian", getActivity());
        }
        return sukses;
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }
}