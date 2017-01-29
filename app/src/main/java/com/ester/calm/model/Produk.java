package com.ester.calm.model;

/**
 * Created by Ester on 24/11/2016.
 */

public class Produk {
    private int id_produk;
    private String nama_produk;
    private int harga;
    private String gambar;
    private String deskripsi;

    public Produk(String nama_produk, int harga, String gambar, String deskripsi){
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
    }

    public int getId_produk(){
        return id_produk;
    }

    public String getNama_produk(){
        return nama_produk;
    }

    public int getHarga_produk(){
        return harga;
    }

    public void setId_produk(int id_produk){
        this.id_produk = id_produk;
    }

    public void setNama_produk(String nama_produk){
        this.nama_produk = nama_produk;
    }

    public void setHarga_produk(int harga) {
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
