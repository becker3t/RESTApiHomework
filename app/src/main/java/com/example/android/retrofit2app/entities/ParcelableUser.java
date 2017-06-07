package com.example.android.retrofit2app.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android on 6/7/2017.
 */

public class ParcelableUser implements Parcelable{

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    private String name;
    private String address;
    private String email;

    public ParcelableUser(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    protected ParcelableUser(Parcel in) {
        name = in.readString();
        address = in.readString();
        email = in.readString();
    }

    public static final Creator<ParcelableUser> CREATOR = new Creator<ParcelableUser>() {
        @Override
        public ParcelableUser createFromParcel(Parcel in) {
            return new ParcelableUser(in);
        }

        @Override
        public ParcelableUser[] newArray(int size) {
            return new ParcelableUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(email);
    }
}
