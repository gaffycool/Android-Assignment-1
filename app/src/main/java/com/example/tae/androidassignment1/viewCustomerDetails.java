package com.example.tae.androidassignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tae.androidassignment1.model.CustomerModel;

public class viewCustomerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_details);

       // getProfileActivity();
    }

    public void getProfileActivity()
    {
       // CustomerModel customerProfile = getIntent().getParcelableExtra("customerProfile");
        //etEmail.setText(customers.mEmail);
        //etPassword.setText(customers.mPassword);

       // Toast.makeText(this, customerProfile.mName + ", " +
            //    customerProfile.mEmail + ", " +
             //   customerProfile.mPassword +  ", " +
             //   customerProfile.mAge,
            //    Toast.LENGTH_SHORT).show();
    }
}
