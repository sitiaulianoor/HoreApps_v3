package com.example.hore.rentalpelanggan.Base;

import java.text.NumberFormat;
import java.util.Locale;



public class BaseActivity {

    public static NumberFormat rupiah(){
        Locale localeID = new Locale("in", "ID");

        NumberFormat numberFormat = NumberFormat.getNumberInstance(localeID);

        return numberFormat;
    }
}
