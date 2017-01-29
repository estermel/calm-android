package com.ester.calm.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.ester.calm.DBHelper;
import com.ester.calm.model.User;

/**
 * Created by Ester on 24/11/2016.
 */

public class UserController {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    public User user;

    public static final String TABLE_NAME = "user";
    public static final String ID_USER = "id_user";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAMA = "nama";
    public static final String STATUS= "status";

    public static final String CREATE_USER = "CREATE TABLE "+TABLE_NAME+" "+
            "("+ID_USER+" integer primary key, "+
            USERNAME+" VARCHAR(225), "+
            PASSWORD+" VARCHAR(225), "+
            USERNAME+" VARCHAR(225), "+
            STATUS+" integer)";


    private String[] TABLE_COLUMNS = {ID_USER,USERNAME,PASSWORD,NAMA,STATUS};

    public UserController(Context context) {
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

    public void insertData(int id_user, String username, String password, String nama, int status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_USER, id_user);
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(NAMA, nama);
        contentValues.put(STATUS,status);
        db.insert(TABLE_NAME, null, contentValues);
    }
/*
    public ArrayList<User> getData() {
        ArrayList<User> allData = new ArrayList<User>();
        Cursor cursor = null;

        cursor = db.query(TABLE_NAME, TABLE_COLUMNS, null, null, null, null, ID_USER+ " ASC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }

        cursor.close();
        return allData;
    }

    public ArrayList<User> getUser(String nama, String username, String password) {
        ArrayList<User> allData = new ArrayList<User>();
        String sql = "";
        sql += "SELECT * FROM " + TABLE_NAME;
        sql += " WHERE " + USERNAME + "='" + username + "' and " + PASSWORD + "= '" + password +"'" ;
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

    private User parseData(Cursor cursor){
        User curData = new User();
        curData.setId_user(cursor.getInt(0));
        curData.setUsername(cursor.getString(1));
        curData.setPassword(cursor.getString(2));
        curData.setUsername(cursor.getString(3));
        return curData;
    }
    */
}