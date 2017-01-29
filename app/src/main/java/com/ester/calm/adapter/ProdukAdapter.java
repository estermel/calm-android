package com.ester.calm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ester.calm.R;
import com.ester.calm.RequestInterface;
import com.ester.calm.model.Produk;
import com.ester.calm.view.LoginActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

/**
 * Created by Ester on 30/11/2016.
 */

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder> {

    private ArrayList<Produk> produkData;
    Context context;
    String username;
    Intent intent;

    public ProdukAdapter(ArrayList<Produk> produkData, Context context){
        this.produkData = produkData;
        this.context = context;
    }

    @Override
    public ProdukAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_produk, viewGroup, false);
        return new ProdukAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProdukAdapter.ViewHolder viewHolder, final int i){
        String url = RequestInterface.retrofit.baseUrl() + produkData.get(i).getGambar();
        Glide.with(viewHolder.iv_gambar.getContext())
                .load(url)
                .fitCenter()
                .into(viewHolder.iv_gambar);

        int harga = produkData.get(i).getHarga_produk();
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();

        dfs.setGroupingSeparator('.');

        df.setGroupingSize(3);
        df.setGroupingUsed(true);
        df.setDecimalFormatSymbols(dfs);

        viewHolder.tv_name.setText(produkData.get(i).getNama_produk() + " - Rp" + df.format(harga));
        viewHolder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent, in;
                String nama_produk = produkData.get(i).getNama_produk();
                int id_produk = produkData.get(i).getId_produk();
                in = new Intent(v.getContext(), LoginActivity.class);
                in.putExtra(nama_produk,i);
                in.putExtra("produk", nama_produk);
                in.putExtra("id_produk", id_produk);
                v.getContext().startActivity(in);
            }

        });

        String desk = produkData.get(i).getDeskripsi();
        int maxlength = 300;
        String resultForLong = java.lang.String.format("%" + maxlength + "s", desk).trim();
        String res = desk.substring(0, Math.min(desk.length(), maxlength));
        viewHolder.tv_deskripsi.setText(res);
    }

    @Override
    public int getItemCount(){
        return produkData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name, tv_deskripsi;
        private Button order;
        private ImageView iv_gambar;

        public ViewHolder (View view){
            super(view);
            iv_gambar = (ImageView)view.findViewById(R.id.iv_gambar);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            order = (Button)view.findViewById(R.id.btn_order);
            tv_deskripsi = (TextView)view.findViewById(R.id.tv_deskripsi);
        }
    }
}
