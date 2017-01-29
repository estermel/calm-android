package com.ester.calm.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ester.calm.R;
import com.ester.calm.RequestInterface;
import com.ester.calm.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_username,et_password,et_nama;
    private TextView tv_login;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    String nama, username, password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);
        initViews();
    }

    private void initViews(){
        progressBar = (ProgressBar)findViewById(R.id.progress);
        btn_register = (AppCompatButton)findViewById(R.id.btn_register);
        tv_login = (TextView)findViewById(R.id.tv_login);
        et_nama = (EditText)findViewById(R.id.et_nama);
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        progressDialog = new ProgressDialog(RegisterActivity.this);
        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_login:
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                break;

            case R.id.btn_register:
                nama = et_nama.getText().toString();
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                if (!nama.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                    Log.d("Debug", nama + username + password);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Sedang mendaftarkan...");
                    progressDialog.show();
                    register();
                } if(nama.isEmpty() || username.isEmpty() || password.isEmpty()){
                    Toast.makeText(this, "Field masih kosong", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void register() {
        RequestInterface requestInterface = RequestInterface.retrofit.create(RequestInterface.class);
        Call<User> call = requestInterface.register(nama, username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                sp = getSharedPreferences("Reg", 0);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("username", username);
                ed.commit();

                progressDialog.dismiss();

                Toast.makeText(RegisterActivity.this, username + ", kamu berhasil terdaftar", Toast.LENGTH_LONG).show();
                Intent orderintent = new Intent(RegisterActivity.this, OrderActivity.class);
                orderintent.putExtra("username", username);
                startActivity(orderintent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
                Log.e("Error", t.getMessage());
                Toast.makeText(RegisterActivity.this, "Maaf, gagal mendaftar", Toast.LENGTH_LONG).show();
            }
        });
    }
}
