package com.ester.calm.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ester.calm.DBHelper;

/**
 * Created by Ester on 24/11/2016.
 */

public class OrderController {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public static final String TABLE_NAME = "order";
    public static final String ID_ORDER = "id_order";
    public static final String ID_USER = "id_user";
    public static final String ID_PRODUK1 = "id_produk1";
    public static final String ID_PRODUK2 = "id_produk2";
    public static final String ID_PRODUK3 = "id_produk3";
    public static final String TOTALHARGA = "totalharga";
    public static final String TANGGAL_ORDER = "tanggal_order";
    public static final String STATUS = "status";

    public static final String CREATE_ORDER = "CREATE TABLE "+TABLE_NAME+" "+
            "("+ID_ORDER +" integer primary key, "+
            ID_USER+" INTERGER, "+
            ID_PRODUK1+" INTEGER, "+
            ID_PRODUK2+" INTEGER, "+
            ID_PRODUK3+ " INTEGER, " +
            TOTALHARGA+ " FLOAT, " +
            TANGGAL_ORDER+ " DATETIME, "+
            STATUS+" VARCHAR(50))";


    private String[] TABLE_COLUMNS = {ID_ORDER,ID_USER,ID_PRODUK1,ID_PRODUK2,ID_PRODUK3,TOTALHARGA,TANGGAL_ORDER,STATUS};

    public OrderController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void deleteData (){
        db.delete(TABLE_NAME, null, null);
    }

    public void insertData(int id_order, int id_user, int id_produk1, int id_produk2, int id_produk3, float total_harga, String tanggal_order, int status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_ORDER, id_order);
        contentValues.put(ID_USER, id_user);
        contentValues.put(ID_PRODUK1, id_produk1);
        contentValues.put(ID_PRODUK2, id_produk2);
        contentValues.put(ID_PRODUK3, id_produk3);
        contentValues.put(TOTALHARGA, total_harga);
        contentValues.put(TANGGAL_ORDER, tanggal_order);
        contentValues.put(STATUS,status);
        db.insert(TABLE_NAME, null, contentValues);
    }
/*
    public ArrayList<Order> getData() {
        ArrayList<Order> allData = new ArrayList<Order>();
        Cursor cursor = null;

        cursor = db.query(TABLE_NAME, TABLE_COLUMNS, null, null, null, null, ID_ORDER+ " ASC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }

        cursor.close();
        return allData;
    }
    public ArrayList<Order> getOrder(String id_order, String id_user) {
        ArrayList<Order> allData = new ArrayList<Order>();
        String sql = "";
        sql += "SELECT * FROM " + TABLE_NAME;
        sql += " WHERE " + ID_ORDER + "='" + id_order+ "' and " + ID_USER+ "= '" + id_user +"'" ;
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));

            cursor.moveToNext();
        }

        cursor.close();
        return allData;
    }


    private Order parseData(Cursor cursor){
        Order curData = new Order();
        curData.setId_order(cursor.getString(0));
        curData.setId_user(cursor.getString(1));
        return curData;
    }
*/

}
