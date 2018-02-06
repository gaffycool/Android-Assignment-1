package com.example.tae.androidassignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import com.example.tae.androidassignment1.controller.RealmBackupRestore;
import com.example.tae.androidassignment1.controller.RealmHelper;
import com.example.tae.androidassignment1.model.CustomerModel;
import io.realm.Realm;


public class customerView extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    RealmBackupRestore realmBackupRestore;
    private ArrayList<CustomerModel> arrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        initRealm();
        initRecyclerView();
    }

    public void initRecyclerView()
    {
        recyclerView = findViewById(R.id.rvProfile);
        //displaying the items how i should display
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      //  recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(new PersonAdapter(getApplicationContext(), R.layout.row_customer, arrayList));
    }

    public void initRealm()
    {
        realm= Realm.getDefaultInstance();
        realmHelper= new RealmHelper(realm);

        arrayList= new ArrayList<>();
        arrayList= realmHelper.getCustomers();
    }
}
