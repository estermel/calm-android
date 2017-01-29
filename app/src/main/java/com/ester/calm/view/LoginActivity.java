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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btn_login;
    private EditText et_username,et_password;
    private TextView tv_register;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    String username, password;
    SharedPreferences sp1;
    String MyLOG = "Log";
    int id_produk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        initViews();
        Intent intent = getIntent();
        sp1 = getSharedPreferences(MyLOG, MODE_PRIVATE);
        id_produk = sp1.getInt("id_produk", 1);
    }

    private void initViews(){
        progressBar = (ProgressBar)findViewById(R.id.progress);
        btn_login = (AppCompatButton)findViewById(R.id.btn_login);
        tv_register = (TextView)findViewById(R.id.tv_register);
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        progressDialog = new ProgressDialog(LoginActivity.this);
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_register:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                break;

            case R.id.btn_login:
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                if (!username.isEmpty() && !password.isEmpty()) {
                    Log.d("Debug", username  + " " + password);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    login();
                }
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(this, "Field masih kosong", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void login() {
        RequestInterface requestInterface = RequestInterface.retrofit.create(RequestInterface.class);
        Call<User> call = requestInterface.login(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                sp1 = getSharedPreferences(MyLOG, MODE_PRIVATE);
                SharedPreferences.Editor editor = sp1.edit();
                editor.putString("username", username);
                editor.commit();
                Toast.makeText(LoginActivity.this, username + ", selamat datang", Toast.LENGTH_LONG).show();
                Intent orderintent = new Intent(LoginActivity.this, OrderDetailActivity.class);
                orderintent.putExtra("username", username);
                orderintent.putExtra("id_produk", id_produk);
                startActivity(orderintent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
                Log.e("Error", t.getMessage());
                Toast.makeText(LoginActivity.this, "Maaf, gagal masuk", Toast.LENGTH_LONG).show();
            }
        });
    }
}
