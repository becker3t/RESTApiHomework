package com.example.android.retrofit2app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.retrofit2app.entities.ParcelableUser;
import com.example.android.retrofit2app.entities.User;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ParcelableUser> studentList;
    UserRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList = getIntent().getParcelableArrayListExtra(MainActivity.USER_LIST_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerAdapter = new UserRecyclerAdapter(studentList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(recyclerAdapter);
    }
}
