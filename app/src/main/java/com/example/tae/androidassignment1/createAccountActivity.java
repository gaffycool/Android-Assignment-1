package com.example.tae.androidassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class createAccountActivity extends AppCompatActivity {

    private ImageView imgHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        imgHome = (ImageView) findViewById(R.id.arrowHome);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(createAccountActivity.this, MainActivity.class);
        startActivity(intent);

    }
}

