package com.example.tae.androidassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tae.androidassignment1.model.CreateAccountModel;

public class createAccountActivity extends AppCompatActivity {

    private ImageView imgHome;
    private Button continueProfile;
    private EditText etEmail, etPassword, etRepeatPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        imgHome = findViewById(R.id.arrowHome);
        continueProfile = findViewById(R.id.btnNext);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRepeatPass = (EditText) findViewById(R.id.etRepeatPass);

        //onclick to go back to home
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();
            }
        });

        //to parse information to customer java using parcable
        continueProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePage();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(createAccountActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void profilePage()
    {
        CreateAccountModel createAccountModel = new CreateAccountModel(etEmail.getText().toString(),
                etPassword.getText().toString());


        Intent intent = new Intent(createAccountActivity.this, customerProfileActivity.class);
        intent.putExtra("customer", createAccountModel);
        startActivity(intent);
    }


}

