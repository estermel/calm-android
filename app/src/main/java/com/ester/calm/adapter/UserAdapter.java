package com.ester.calm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ester.calm.R;
import com.ester.calm.model.User;

import java.util.ArrayList;

/**
 * Created by Ester on 29/11/2016.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> userData;

    public UserAdapter(ArrayList<User> userData){
        this.userData = userData;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewHolder, int i){
        viewHolder.tv_name.setText(userData.get(i).getUsername());
    }

    @Override
    public int getItemCount(){
        return userData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        public ViewHolder (View view){
            super(view);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
        }
    }
}
