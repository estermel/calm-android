package com.ester.calm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ester on 23/11/2016.
 */

public class User {

    @SerializedName("id_user")
    @Expose
    private int idUser;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;


    public User (String nama, String username, String password) {
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    }
