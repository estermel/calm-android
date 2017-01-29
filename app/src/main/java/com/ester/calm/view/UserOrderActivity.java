package com.ester.calm.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.ester.calm.adapter.UserOrderAdapter;
import com.ester.calm.model.Order;
import com.ester.calm.model.User;
import com.ester.calm.response.OrderResponse;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Order> orderData;
    private ArrayList<User> users;
    private UserOrderAdapter adapter;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
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
            case R.id.home:
                Intent intent = new Intent(UserOrderActivity.this, OrderActivity.class);
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

    private void initViews() {
        progressBar = (ProgressBar)findViewById(R.id.progress);
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        progressDialog = new ProgressDialog(UserOrderActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        loadJSON();
    }

    private void loadJSON() {
        final RequestInterface request = RequestInterface.retrofit.create(RequestInterface.class);
        Call<OrderResponse> call = request.getOrder();
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                OrderResponse orderResponse = response.body();
                orderData = new ArrayList<>(Arrays.asList(orderResponse.getOrder()));
                adapter = new UserOrderAdapter(orderData);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(UserOrderActivity.this, "Gagal mengambil data order", Toast.LENGTH_LONG).show();
            }
        });
    }

}
