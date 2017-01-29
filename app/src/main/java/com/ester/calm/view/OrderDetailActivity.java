package com.ester.calm.view;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ester.calm.R;
import com.ester.calm.RequestInterface;
import com.ester.calm.model.Order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ester.calm.R.id.etJus;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener {
    EditText tanggal, et_jus;
    Calendar myCalendar;
    Button order;
    TextView tv_produk_pilihan, tv_username;
    String produk;
    String username1, username;
    String asrama;
    String no_kamar;
    String tanggal_booking;
    String jam_booking;
    String jus;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    Spinner sp_asrama, sp_no_kamar, sp_jam;
    SharedPreferences sp1;
    private Toolbar toolbar;
    String MyLOG = "Log";
    int id_produk1, id_produk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar)findViewById(R.id.progress);
        tv_username = (TextView)findViewById(R.id.username_sharedPref);
        sp1 = getSharedPreferences(MyLOG, MODE_PRIVATE);
        username1 = sp1.getString("username", null);
        tv_username.setText(username1);
        order = (Button) findViewById(R.id.btnOrder);

        Intent in = getIntent();
        produk = in.getStringExtra("produk");
        id_produk1 = in.getIntExtra("id_produk", 1);
        tv_produk_pilihan = (TextView)findViewById(R.id.tv_produk_pilihan);
        tv_produk_pilihan.setText(produk);

        sp_asrama = (Spinner)findViewById(R.id.spLokasiAsrama);
        sp_no_kamar = (Spinner)findViewById(R.id.spNoKamar);
        sp_jam = (Spinner)findViewById(R.id.spJamOrder);
        et_jus = (EditText) findViewById(etJus);
        tanggal = (EditText) findViewById(R.id.etTanggalOrder);
        myCalendar = Calendar.getInstance();

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(OrderDetailActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        progressDialog = new ProgressDialog(OrderDetailActivity.this);
        order.setOnClickListener(OrderDetailActivity.this);
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
                Intent intent = new Intent(OrderDetailActivity.this, OrderActivity.class);
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

    @Override
    public void onClick(View v) {
        username = tv_username.getText().toString();
        produk = tv_produk_pilihan.getText().toString();
        id_produk = id_produk1;
        asrama = sp_asrama.getSelectedItem().toString();
        no_kamar = sp_no_kamar.getSelectedItem().toString();
        jam_booking = sp_jam.getSelectedItem().toString();
        tanggal_booking = tanggal.getText().toString();
        jus = et_jus.getText().toString();
        Toast.makeText(this, username  +"  "+ produk  + "  " + id_produk + "  "+ asrama +"  " +no_kamar+ "  "+jus+ "  "+jam_booking+"  " +tanggal_booking, Toast.LENGTH_LONG).show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        order();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int month, int day){
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }
    };

    private void updateLabel(){
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tanggal.setText(sdf.format(myCalendar.getTime()));
    }

    public void order() {
        RequestInterface requestInterface = RequestInterface.retrofit.create(RequestInterface.class);
        Call<Order> call = requestInterface.order(username, id_produk, asrama, no_kamar, jus, tanggal_booking, jam_booking);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                progressDialog.dismiss();
                Toast.makeText(OrderDetailActivity.this, "Berhasil mengorder", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(OrderDetailActivity.this, OrderActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
                Log.e("Error", t.getMessage());
                Toast.makeText(OrderDetailActivity.this, "Ups... gagal order", Toast.LENGTH_LONG).show();
            }
        });
    }
}
