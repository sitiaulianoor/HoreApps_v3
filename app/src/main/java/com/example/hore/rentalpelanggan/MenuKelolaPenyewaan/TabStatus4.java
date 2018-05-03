package com.example.hore.rentalpelanggan.MenuKelolaPenyewaan;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.hore.rentalpelanggan.MenuPemesanan.PenyewaanModel;
import com.example.hore.rentalpelanggan.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class TabStatus4 extends Fragment {
    private RecyclerView recyclerView;
    private TabStatus4Adapter adapter;
    private List<PenyewaanModel> penyewaanModel;
    private DatabaseReference mDatabase;
    ProgressBar progressBar;
    private FirebaseAuth auth;
    private String idPelanggan;
    ImageView ic_noOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_status4, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.listView);
        recyclerView.setHasFixedSize(true);
        ic_noOrder = (ImageView)v.findViewById(R.id.ic_noOrder);

        final FragmentActivity c = getActivity();
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

        progressBar = (ProgressBar) v.findViewById(R.id.progress_circle);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FEBD3D"), PorterDuff.Mode.SRC_ATOP);
        progressBar.setVisibility(View.VISIBLE);
        ic_noOrder.setVisibility(View.GONE);
        penyewaanModel = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idPelanggan = user.getUid();
        Firebase.setAndroidContext(getActivity());

        getDataPemesanan();
        return v;
    }

    public void getDataPemesanan() {
        try {
            String status4 = "selesai";
            mDatabase.child("penyewaanKendaraan").child(status4).orderByChild("idPelanggan").equalTo(idPelanggan).addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (com.google.firebase.database.DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            PenyewaanModel dataPemesanan = postSnapshot.getValue(PenyewaanModel.class);
                            penyewaanModel.add(dataPemesanan);
                            adapter = new TabStatus4Adapter(getActivity(), penyewaanModel);
                            //adding adapter to recyclerview
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                            ic_noOrder.setVisibility(View.GONE);
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        ic_noOrder.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {


        }
    }
}
