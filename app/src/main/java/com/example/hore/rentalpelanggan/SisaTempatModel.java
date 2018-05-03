package com.example.hore.rentalpelanggan;



public class SisaTempatModel {
    String idCekSisa, tglSewa, tglKembali, idKendaraan;
    int sisaKendaraan;

    public SisaTempatModel(){}

    public SisaTempatModel(String idCekSisa, String tglSewa, String tglKembali, String idKendaraan, int sisaKendaraan) {
        this.idCekSisa = idCekSisa;
        this.tglSewa = tglSewa;
        this.tglKembali = tglKembali;
        this.idKendaraan = idKendaraan;
        this.sisaKendaraan = sisaKendaraan;
    }

    public String getIdCekSisa() {
        return idCekSisa;
    }

    public String getTglSewa() {
        return tglSewa;
    }

    public String getTglKembali() {
        return tglKembali;
    }

    public String getIdKendaraan() {
        return idKendaraan;
    }

    public int getSisaKendaraan() {
        return sisaKendaraan;
    }
}
