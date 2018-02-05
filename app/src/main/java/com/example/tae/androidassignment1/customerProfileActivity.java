package com.example.tae.androidassignment1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
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
import java.util.Calendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.app.Activity;

import com.example.tae.androidassignment1.model.CreateAccountModel;
import com.example.tae.androidassignment1.model.CustomerModel;

public class customerProfileActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etName, etAge, postalAddress;
    private Button btnSave, btnChangePhoto, btnDatePicker;
    private static final int REQUEST_CODE = 1;
    private Bitmap bitmap;
    ImageView imgProfile;
    private Spinner sCountry;
    String genderSelected, selectCountry, profileImgDir;
    int year_x, month_x, day_x;
    //static final int DIALOG_ID = 0;
    RadioGroup rgGender;
    Calendar mCurrentDate;


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

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                getGender(i);
            }
        });

        getCreateAccountDetails();
        chooseCountry();

        btnChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate();
            }
        });
    }

    public void saveDetails() {
        /*CustomerModel customerProfile = new CustomerModel(
                etName.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                Integer.parseInt(etAge.getText().toString()));

        Intent intent = new Intent(customerProfileActivity.this, viewCustomerDetails.class);
        intent.putExtra("customerProfile", customerProfile);
        startActivity(intent);*/

        // Toast.makeText(getApplicationContext(), "Profile has been saved! " + item, Toast.LENGTH_LONG).show();

    }

    public void getCreateAccountDetails() {
        CreateAccountModel customers = getIntent().getParcelableExtra("customer");
        etEmail.setText(customers.mEmail);
        etPassword.setText(customers.mPassword);
    }

    public void showDate()
    {
        mCurrentDate = Calendar.getInstance();
        day_x = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month_x = mCurrentDate.get(Calendar.MONTH);
        year_x = mCurrentDate.get(Calendar.YEAR);

        month_x = month_x+1;
        btnDatePicker.setText(day_x+"/"+month_x+"/"+year_x);
        DatePickerDialog datePickerDialog = new DatePickerDialog(customerProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear = monthOfYear+1;
            btnDatePicker.setText(dayOfMonth+"/" + monthOfYear + "/" + year);
            }
        }, year_x, month_x, day_x);
        datePickerDialog.show();
    }

    public void getGender(int i)
    {
        switch(i){
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


    public void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {

                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                    Uri uri = data.getData();
                    //Toast.makeText(getApplicationContext(), "" + uri.toString() , Toast.LENGTH_LONG).show();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                imgProfile.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
    }
}