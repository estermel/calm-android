package com.ester.calm.model;

/**
 * Created by Ester on 24/11/2016.
 */

public class Order {

    private String id_order;
    private String username;
    private String id_produk;
    private String nama_produk;
    private String asrama;
    private String no_kamar;
    private String jus;
    private String tanggal_booking;
    private String jam_booking;
    private String waktu_order;
    private String status_order;
    private String confirm_by;
    private String waktu_konfirmasi;

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }


    public String getConfirm_by() {
        return confirm_by;
    }

    public String getId_order() {
        return id_order;
    }

    public String getId_produk() {
        return id_produk;
    }


    public String getAsrama() {
        return asrama;
    }

    public String getJus() {
        return jus;
    }

    public String getNo_kamar() {
        return no_kamar;
    }

    public String getStatus_order() {
        return status_order;
    }

    public String getTanggal_booking() {
        return tanggal_booking;
    }

    public String getJam_booking() {
        return jam_booking;
    }

    public void setJam_booking(String jam_booking) {
        this.jam_booking = jam_booking;
    }

    public void setTanggal_booking(String tanggal_booking) {
        this.tanggal_booking = tanggal_booking;
    }

    public String getWaktu_konfirmasi() {
        return waktu_konfirmasi;
    }

    public String getWaktu_order() {
        return waktu_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public void setAsrama(String asrama) {
        this.asrama = asrama;
    }

    public void setConfirm_by(String confirm_by) {
        this.confirm_by = confirm_by;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public void setJus(String jus) {
        this.jus = jus;
    }

    public void setNo_kamar(String no_kamar) {
        this.no_kamar = no_kamar;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public void setWaktu_konfirmasi(String waktu_konfirmasi) {
        this.waktu_konfirmasi = waktu_konfirmasi;
    }

    public void setWaktu_order(String waktu_order) {
        this.waktu_order = waktu_order;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
