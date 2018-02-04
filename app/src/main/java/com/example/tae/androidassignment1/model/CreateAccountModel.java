package com.example.tae.androidassignment1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by laFerrari on 03/02/2018.
 */

public class CreateAccountModel implements Parcelable {
    public String mEmail;
    public String mPassword;

    public CreateAccountModel(String mEmail, String mPassword) {
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mEmail);
        dest.writeString(this.mPassword);
    }

    protected CreateAccountModel(Parcel in) {
        this.mEmail = in.readString();
        this.mPassword = in.readString();
    }

    public static final Creator<CreateAccountModel> CREATOR = new Creator<CreateAccountModel>() {
        @Override
        public CreateAccountModel createFromParcel(Parcel source) {
            return new CreateAccountModel(source);
        }
        @Override
        public CreateAccountModel[] newArray(int size) {
            return new CreateAccountModel[size];
        }
    };
}