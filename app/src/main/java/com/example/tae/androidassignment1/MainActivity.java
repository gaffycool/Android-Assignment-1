package com.example.tae.androidassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAccount();
            }
        });
    }

    public void createAccount() {
        Intent intent = new Intent(MainActivity.this, createAccountActivity.class);
        startActivity(intent);

    }
}
