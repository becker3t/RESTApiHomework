package com.example.android.retrofit2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.retrofit2app.entities.ParcelableUser;

import java.util.ArrayList;

/**
 * Created by Android on 6/7/2017.
 */

public class UserListAdapter extends BaseAdapter {

    private ArrayList<ParcelableUser> studentList;
    private Context context;

    public UserListAdapter(ArrayList<ParcelableUser> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ParcelableUser currentStudent = (ParcelableUser) getItem(position);

        holder.userNameTv.setText(currentStudent.getName());
        holder.userAddressTv.setText(currentStudent.getAddress());
        holder.userEmailTv.setText(currentStudent.getEmail());

        return convertView;
    }

    private class ViewHolder {

        TextView userNameTv;
        TextView userAddressTv;
        TextView userEmailTv;

        public ViewHolder(View view) {
            userNameTv = (TextView) view.findViewById(R.id.userNameListTv);
            userAddressTv = (TextView) view.findViewById(R.id.userAddressListTv);
            userEmailTv = (TextView) view.findViewById(R.id.userEmailListTv);
        }
    }
}
