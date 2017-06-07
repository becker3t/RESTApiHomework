package com.example.android.retrofit2app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.retrofit2app.entities.ParcelableUser;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<ParcelableUser> userList;
    ListView userListView;
    UserListAdapter userListAdapter;

    private Button backToMainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if(getIntent() != null) {
            userList = getIntent().getExtras().getParcelableArrayList(MainActivity.USER_LIST_KEY);
        }
        else {
            userList = new ArrayList<>();
        }

        userListView = (ListView) findViewById(R.id.listUsers);

        backToMainBtn = (Button) findViewById(R.id.btnBackToMain);
        backToMainBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userListAdapter = new UserListAdapter(userList, this);
        userListView.setAdapter(userListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBackToMain:
                Intent i = new Intent(ListActivity.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList(MainActivity.USER_LIST_KEY, userList);
                i.putExtras(b);
                startActivity(i);
                break;
        }
    }
}
