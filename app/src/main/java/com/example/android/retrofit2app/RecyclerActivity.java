package com.example.android.retrofit2app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.android.retrofit2app.entities.ParcelableUser;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    ArrayList<ParcelableUser> userList;
    UserRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(getIntent().getExtras() != null) {
            userList = getIntent().getExtras().getParcelableArrayList(MainActivity.USER_LIST_KEY);
        }
        else {
            userList = new ArrayList<>();
        }

        Button backToMainBtn = (Button) findViewById(R.id.btnBackToMain);
        backToMainBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerAdapter = new UserRecyclerAdapter(userList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackToMain:
                Intent i = new Intent(RecyclerActivity.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList(MainActivity.USER_LIST_KEY, userList);
                i.putExtras(b);
                startActivity(i);
                break;
        }
    }
}
