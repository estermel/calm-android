package com.ester.calm.response;

import com.ester.calm.model.Produk;

/**
 * Created by Ester on 30/11/2016.
 */

public class ProdukResponse {

    private String error;

    private Produk[] produk;

    public String getError() {
        return error;
    }

    public Produk[] getProduk() {
        return produk;
    }
}
