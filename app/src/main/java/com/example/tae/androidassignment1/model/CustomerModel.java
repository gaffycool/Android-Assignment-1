package com.example.tae.androidassignment1.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by laFerrari on 03/02/2018.
 */

public class CustomerModel extends RealmObject {

    String nName;
    String nEmail;
    String nPassword;
    byte[] nProfilDir;
    String nAge;
    String nDatePicker;
    String nCountry;
    String nGender;
    String nPostalAddress;

    public CustomerModel() {
        super();
    }

    public CustomerModel(String nName, String nEmail, String nPassword, byte[] nProfilDir, String nAge, String nDatePicker, String nCountry, String nGender, String nPostalAddress) {
        this.nName = nName;
        this.nEmail = nEmail;
        this.nPassword = nPassword;
        this.nProfilDir = nProfilDir;
        this.nAge = nAge;
        this.nDatePicker = nDatePicker;
        this.nCountry = nCountry;
        this.nGender = nGender;
        this.nPostalAddress = nPostalAddress;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getnEmail() {
        return nEmail;
    }

    public void setnEmail(String nEmail) {
        this.nEmail = nEmail;
    }

    public String getnPassword() {
        return nPassword;
    }

    public void setnPassword(String nPassword) {
        this.nPassword = nPassword;
    }

    public byte[] getnProfilDir() {
        return nProfilDir;
    }

    public void setnProfilDir(byte[] nProfilDir) {
        this.nProfilDir = nProfilDir;
    }

    public String getnAge() {
        return nAge;
    }

    public void setnAge(String nAge) {
        this.nAge = nAge;
    }

    public String getnDatePicker() {
        return nDatePicker;
    }

    public void setnDatePicker(String nDatePicker) {
        this.nDatePicker = nDatePicker;
    }

    public String getnCountry() {
        return nCountry;
    }

    public void setnCountry(String nCountry) {
        this.nCountry = nCountry;
    }

    public String getnGender() {
        return nGender;
    }

    public void setnGender(String nGender) {
        this.nGender = nGender;
    }

    public String getnPostalAddress() {
        return nPostalAddress;
    }

    public void setnPostalAddress(String nPostalAddress) {
        this.nPostalAddress = nPostalAddress;
    }
}

