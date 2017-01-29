package com.ester.calm.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ester.calm.R;
import com.ester.calm.RequestInterface;
import com.ester.calm.adapter.ProdukAdapter;
import com.ester.calm.model.Produk;
import com.ester.calm.response.ProdukResponse;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ester on 30/11/2016.
 */

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Produk> produkData;
    private ProdukAdapter adapter;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    Context context;
    String username;
    private Toolbar toolbar;
    String MyLOG = "MyLOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                break;
            case R.id.orderan_diterima:
                Intent intent = new Intent(OrderActivity.this, UserOrderActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SharedPreferences preferences = getSharedPreferences(MyLOG, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "Sudah logout", Toast.LENGTH_SHORT).show();
    }
*/
    private void initViews() {
        progressBar = (ProgressBar)findViewById(R.id.progress);
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        progressDialog = new ProgressDialog(OrderActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        loadJSON();
    }

    private void loadJSON() {
        final RequestInterface request = RequestInterface.retrofit.create(RequestInterface.class);
        Call<ProdukResponse> call = request.getProduk();
        call.enqueue(new Callback<ProdukResponse>() {
            @Override
            public void onResponse(Call<ProdukResponse> call, Response<ProdukResponse> response) {
                ProdukResponse produkResponse = response.body();
                produkData = new ArrayList<>(Arrays.asList(produkResponse.getProduk()));
                adapter = new ProdukAdapter(produkData,context);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ProdukResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Error" + t.getLocalizedMessage(), t.getMessage());
            }
        });
    }
}

