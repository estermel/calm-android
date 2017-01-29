package com.ester.calm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ester.calm.controller.OrderController;
import com.ester.calm.controller.ProdukController;
import com.ester.calm.controller.UserController;

/**
 * Created by Ester on 24/11/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "calm.db";
    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);}
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(UserController.CREATE_USER);
            db.execSQL(ProdukController.CREATE_PRODUK);
            db.execSQL(OrderController.CREATE_ORDER);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
