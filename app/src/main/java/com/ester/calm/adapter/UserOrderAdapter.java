package com.ester.calm.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ester.calm.R;
import com.ester.calm.RequestInterface;
import com.ester.calm.model.Order;
import com.ester.calm.response.OrderResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ester.calm.R.*;

/**
 * Created by Ester on 30/11/2016.
 */

public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order> userOrderData;
    private Order order;
    private boolean admin = true;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    Button btn_terima;
    private boolean confirm_status = false;
    int id_order_, id_order;

    public UserOrderAdapter(ArrayList<Order> userOrderData){
        this.userOrderData = userOrderData;
    }

    @Override
    public UserOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_user_order, viewGroup, false);
        return new UserOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String status = userOrderData.get(position).getStatus_order().toString();
        int id_order1 = Integer.parseInt(userOrderData.get(position).getId_order());
        holder.tv_produk.setText(userOrderData.get(position).getNama_produk());
        holder.tv_asrama.setText(userOrderData.get(position).getAsrama());
        holder.tv_no_kamar.setText("kamar " + userOrderData.get(position).getNo_kamar());
        holder.tv_jus.setText(userOrderData.get(position).getJus());
        holder.tv_status_order.setText(status);

        if(admin){
            String status_order = holder.tv_status_order.getText().toString();
            if(status_order=="menunggu") {
                holder.tv_status_order.setBackgroundColor(Color.MAGENTA);
                holder.btn_terima.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        id_order_ = Integer.parseInt(userOrderData.get(position).getId_order());
                        btn_terima.setVisibility(View.INVISIBLE);
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                        confirm();
                        Log.d("ID ORDER ", String.valueOf(id_order_));
                    }
                });
            }
            if(status_order=="diterima"){
                holder.btn_terima.setVisibility(View.INVISIBLE);
            }
        }
        if(!admin){
            holder.btn_terima.setVisibility(View.INVISIBLE);
        }
    }

    private void confirm() {
        id_order = id_order_;
        final RequestInterface request = RequestInterface.retrofit.create(RequestInterface.class);
        Call<OrderResponse> call = request.confirmOrder(id_order);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                confirm_status = true;
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount(){
        return userOrderData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_produk, tv_asrama, tv_no_kamar, tv_jus, tv_status_order;
        View root;
        public Button btn_terima;

        public ViewHolder (View view){
            super(view);
            progressBar = (ProgressBar)view.findViewById(id.progress);
            progressDialog = new ProgressDialog(view.getContext());
            btn_terima = (Button)view.findViewById(id.btn_terima);
            tv_produk = (TextView)view.findViewById(id.tv_produk);
            tv_asrama = (TextView)view.findViewById(id.tv_asrama);
            tv_no_kamar = (TextView)view.findViewById(id.tv_no_kamar);
            tv_jus = (TextView)view.findViewById(id.tv_jus);
            tv_status_order = (TextView)view.findViewById(id.tv_status_order);
            root = tv_status_order.getRootView();
        }
    }
}
