package com.example.tae.androidassignment1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import android.graphics.BitmapFactory;
import android.app.Activity;
import io.realm.Realm;
import com.example.tae.androidassignment1.controller.RealmBackupRestore;
import com.example.tae.androidassignment1.controller.RealmHelper;

import com.example.tae.androidassignment1.model.CreateAccountModel;
import com.example.tae.androidassignment1.model.CustomerModel;

import static android.content.Intent.ACTION_OPEN_DOCUMENT;

public class customerProfileActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etName, etAge, postalAddress;
    private Button btnSave, btnChangePhoto, btnDatePicker;
    static int ACTION_REQUEST = 1;
    private Bitmap resultPhoto;
    ImageView imgProfile;
    private Spinner sCountry;
    String genderSelected, selectCountry, mCurrentPhotoPath;
    public String profileImgDir, ageS;
    int year_x, month_x, day_x;
    //static final int DIALOG_ID = 0;
    RadioGroup rgGender;
    Calendar mCurrentDate;
    Realm realm;
    RealmHelper realmHelper;
    RealmBackupRestore realmBackupRestore;
    byte[] photoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);

        btnSave = findViewById(R.id.btnSave);
        imgProfile = findViewById(R.id.imgProfile);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);
        sCountry = findViewById(R.id.sCountry);
        rgGender = findViewById(R.id.rgGender);
        postalAddress = findViewById(R.id.txtPostalAddress);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        getCreateAccountDetails();
        //init();
        initRealm();

        realmBackupRestore = new RealmBackupRestore(customerProfileActivity.this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                getGender(i);
            }
        });
        chooseCountry();

        btnChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
                //setPic();
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate();
            }
        });
    }

    public void initRealm() {
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper((realm));
    }

    public void getGender(int i) {
        switch (i) {
            case R.id.rbFemale:
                genderSelected = "Female";
                break;
            case R.id.rbMale:
                genderSelected = "Male";
                break;
            case R.id.rbNone:
                genderSelected = "Not-Specified";
                break;
        }
    }

    public void getCreateAccountDetails() {
        CreateAccountModel customers = getIntent().getParcelableExtra("customer");
        etEmail.setText(customers.mEmail);
        etPassword.setText(customers.mPassword);
    }

    public void saveDetails() {

        //setPic();
        CustomerModel customerModel =
                new CustomerModel(etName.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        photoData,
                        etAge.getText().toString(),
                        btnDatePicker.getText().toString(),
                        selectCountry,
                        genderSelected,
                        postalAddress.getText().toString());

        Realm.init(this);
        realmHelper.saveCustomer(customerModel);
        realmBackupRestore.backup();

        Intent intent = new Intent(customerProfileActivity.this, customerView.class);
        startActivity(intent);

    }

    public void showDate() {
        mCurrentDate = Calendar.getInstance();


        day_x = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month_x = mCurrentDate.get(Calendar.MONTH);
        year_x = mCurrentDate.get(Calendar.YEAR);

        month_x = month_x + 1;
        btnDatePicker.setText(day_x + "/" + month_x + "/" + year_x);
        DatePickerDialog datePickerDialog = new DatePickerDialog(customerProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                btnDatePicker.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                Calendar dob = Calendar.getInstance();
                dob.set(year, monthOfYear, dayOfMonth);

                int age = mCurrentDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
                Integer ageInt = new Integer(age);
                ageS = ageInt.toString();
                etAge.setText(ageS);

            }
        }, year_x, month_x, day_x);
        etAge.setText(ageS);
        datePickerDialog.show();
    }

    public void chooseCountry() {
        String[] countries = {"Not-Specified", "United Kingdom", "France", "Germany", "America",
                "South Africa", "United Kingdom", "France", "Germany", "America", "South Africa",
                "United Kingdom", "France", "Germany", "America", "South Africa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                countries);

        sCountry.setAdapter(adapter);
        sCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                selectCountry = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    private void pickImage() {
        {
            Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(photoIntent, RESULT_OK);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap resultPhoto = (Bitmap) extras.get("data");
            imgProfile.setImageBitmap(resultPhoto);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resultPhoto.compress(Bitmap.CompressFormat.PNG, 100, stream);
            photoData = stream.toByteArray();
        }
    }
}

