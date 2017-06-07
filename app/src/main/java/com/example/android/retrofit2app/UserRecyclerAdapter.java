package com.example.android.retrofit2app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.retrofit2app.entities.ParcelableUser;

import java.util.ArrayList;

/**
 * Created by Android on 6/7/2017.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{

    private ArrayList<ParcelableUser> userList;

    public UserRecyclerAdapter(ArrayList<ParcelableUser> userList) {
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.user_recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userNameTv.setText(userList.get(position).getName());
        holder.userAddressTv.setText(userList.get(position).getAddress());
        holder.userEmailTv.setText(userList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTv;
        TextView userAddressTv;
        TextView userEmailTv;

        public ViewHolder(View v) {
            super(v);
            userNameTv = (TextView) v.findViewById(R.id.userNameRecycleTv);
            userAddressTv = (TextView) v.findViewById(R.id.userAddressRecycleTv);
            userEmailTv = (TextView) v.findViewById(R.id.userEmailRecycleTv);
        }
    }
}
