package com.ester.calm.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.ester.calm.DBHelper;
import com.ester.calm.model.Produk;

import java.util.ArrayList;

/**
 * Created by Ester on 24/11/2016.
 */

public class ProdukController {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    public static final String TABLE_NAME="produk";
    public static final String ID_PRODUK="id_produk";
    public static final String NAMA_PRODUK="nama_produk";
    public static final String HARGA_PRODUK="harga_produk";
    public static final String GAMBAR = "gambar";
    public static final String DESKRIPSI = "deskripsi";

    public static final String CREATE_PRODUK = "CREATE TABLE "
            + TABLE_NAME + " (" +
            ID_PRODUK + " INTEGER PRIMARY KEY, " +
            NAMA_PRODUK + " VARCHAR(50), " +
            HARGA_PRODUK + " INTEGER(10), "+
            GAMBAR + " VARCHAR(100), +" +
            DESKRIPSI + " VARCHAR(50))";

    private String[] TABLE_COLUMNS = {ID_PRODUK, NAMA_PRODUK, HARGA_PRODUK, GAMBAR, DESKRIPSI};

    public ProdukController(Context context) {
        dbHelper = new DBHelper(context);}

    public void open() throws SQLiteException{
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.delete(TABLE_NAME,null,null);
    }

    public void insertData(int id_produk, String nama_produk, int harga_produk, String gambar, String deskripsi){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PRODUK, id_produk);
        contentValues.put(NAMA_PRODUK, nama_produk);
        contentValues.put(HARGA_PRODUK, harga_produk);
        contentValues.put(GAMBAR, gambar);
        contentValues.put(DESKRIPSI, deskripsi);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Produk> getData(){
        ArrayList<Produk> allData = new ArrayList<Produk>();
        Cursor cursor = null;

        cursor = db.query(TABLE_NAME, TABLE_COLUMNS, null, null, null, null, ID_PRODUK + " ASC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }

        cursor.close();
        return allData;
    }

    public ArrayList<Produk> getProduk(String nama_produk){
        ArrayList<Produk> allData = new ArrayList<Produk>();
        String sql = "";
        sql += "SELECT * FROM " + TABLE_NAME;
        sql += " WHERE " + NAMA_PRODUK + " LIKE '%" + nama_produk + "%'";
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return allData;
    }

    private Produk parseData (Cursor cursor){
        String nama_produk_ = null;
        String gambar_ = null;
        int harga_produk_ = 0;
        String deskripsi_ = null;
        Produk curData = new Produk(nama_produk_, harga_produk_, gambar_,deskripsi_);
        curData.setId_produk(cursor.getInt(0));
        curData.setNama_produk(cursor.getString(1));
        curData.setHarga_produk(cursor.getInt(2));
        curData.setGambar(cursor.getString(3));
        curData.setDeskripsi(cursor.getString(4));
        return curData;
    }
}
