package com.example.tae.androidassignment1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by laFerrari on 03/02/2018.
 */

public class CustomerModel implements Parcelable {

    public String mName;
    public String mEmail;
    public String mPassword;
    public int mAge;

    public CustomerModel(String mName, String mEmail, String mPassword, int mAge) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mAge = mAge;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mEmail);
        dest.writeString(this.mPassword);
        dest.writeInt(this.mAge);
    }

    protected CustomerModel(Parcel in) {
        this.mName = in.readString();
        this.mEmail = in.readString();
        this.mPassword = in.readString();
        this.mAge = in.readInt();
    }

    public static final Creator<CustomerModel> CREATOR = new Creator<CustomerModel>() {
        @Override
        public CustomerModel createFromParcel(Parcel source) {
            return new CustomerModel(source);
        }
        @Override
        public CustomerModel[] newArray(int size) {
            return new CustomerModel[size];
        }
    };
}
