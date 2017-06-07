package com.example.android.retrofit2app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.retrofit2app.entities.Result;
import com.example.android.retrofit2app.entities.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String RETROFIT_BASE_URL ="https://randomuser.me/";

    private ArrayList<Result> resultArrayList;

    TextView nameTv;
    TextView addressTv;
    TextView emailTv;

    Button fetchUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTv = (TextView) findViewById(R.id.userNameTv);
        addressTv = (TextView) findViewById(R.id.userAddressTv);
        emailTv = (TextView) findViewById(R.id.userEmailTv);

        fetchUserBtn = (Button) findViewById(R.id.fetchUserBtn);
        fetchUserBtn.setOnClickListener(this);

        resultArrayList = new ArrayList<>();
    }

    private void startNetworkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<User> call = service.getExampleUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    User user = response.body();

                    for (Result r : user.getResults()) {
                        resultArrayList.add(r);
                        final String userName = getNameString(r);
                        final String userAddress = getAddressString(r);
                        final String userEmail = getEmailString(r);

                        setTextViews(userName, userAddress, userEmail);
                    }

                    Log.d(TAG, "onResponse: array size = " + resultArrayList.size());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private String getNameString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.user_name_label));
        builder.append(" ");
        builder.append(r.getName().getFirst());
        builder.append(" ");
        builder.append(r.getName().getLast());
        return builder.toString();
    }

    private String getAddressString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.user_address_label));
        builder.append(" ");
        builder.append(r.getLocation().getStreet());
        builder.append(", ");
        builder.append(r.getLocation().getCity());
        builder.append(", ");
        builder.append(r.getLocation().getState());
        builder.append(" ");
        builder.append(r.getLocation().getPostcode());
        return builder.toString();
    }

    private String getEmailString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.user_address_label));
        builder.append(" ");
        builder.append(r.getEmail());
        return builder.toString();
    }

    private void setTextViews(String name, String address, String email) {
        nameTv.setText(name);
        addressTv.setText(address);
        emailTv.setText(email);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fetchUserBtn:
                startNetworkCall();
                break;
        }
    }
}
