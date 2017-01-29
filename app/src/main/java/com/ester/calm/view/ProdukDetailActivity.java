package com.ester.calm.view;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ester.calm.R;
import com.ester.calm.RequestInterface;

public class ProdukDetailActivity extends AppCompatActivity {

    String nama, deskripsi, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        deskripsi = intent.getStringExtra("deskripsi");

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(nama);

        loadBackdrop();
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);

        Intent intent=getIntent();
        String img = intent.getStringExtra("image");
        String url = RequestInterface.retrofit.baseUrl() + img;
        Glide.with(this).load(url).centerCrop().into(imageView);

    }

}
