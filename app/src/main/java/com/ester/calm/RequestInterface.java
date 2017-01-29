package com.ester.calm;

/**
 * Created by Ester on 23/11/2016.
 */

import com.ester.calm.model.Order;
import com.ester.calm.model.User;
import com.ester.calm.response.OrderResponse;
import com.ester.calm.response.ProdukResponse;
import com.ester.calm.response.UserResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestInterface {

    @GET("/calm/akun")
    Call<UserResponse> getUsers();

    @FormUrlEncoded
    @POST("/calm/confirmOrder")
    Call<OrderResponse> confirmOrder(@Field("id_order") int id_order);

    @Headers("Content-Type: application/json")
    @GET("/calm/order")
    Call<OrderResponse> getOrder();

    @FormUrlEncoded
    @POST("/calm/login")
    Call<User> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/calm/logout")
    Call<User> logout(@Field("username") String username);

    @FormUrlEncoded
    @POST("/calm/order")
    Call<Order> order(@Field("username") String username, @Field("id_produk") int id_produk,
                      @Field("asrama") String asrama, @Field("no_kamar") String no_kamar,
                      @Field("jus") String jus, @Field("tanggal_booking") String tanggal_booking,
                      @Field("jam_booking") String jam_booking);

    @Headers("Content-Type: application/json")
    @GET("/calm/produk")
    Call<ProdukResponse> getProduk();

    @FormUrlEncoded
    @POST("/calm/rejectOrder")
    Call<OrderResponse> rejectOrder();

    @FormUrlEncoded
    @POST("/calm/register")
    Call<User> register(@Field("nama") String nama, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/calm/userOrder")
    Call<OrderResponse> getUserOrder(@Field("username") String username);

    @FormUrlEncoded
    @POST("/calm/userOrderDiterima")
    Call<OrderResponse> getUserOrderDiterima(@Field("username") String username);

    @FormUrlEncoded
    @POST("/calm/userOrderMenunggu")
    Call<OrderResponse> getUserOrderMenunggu(@Field("username") String username);


    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.69")//replace with your host
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
