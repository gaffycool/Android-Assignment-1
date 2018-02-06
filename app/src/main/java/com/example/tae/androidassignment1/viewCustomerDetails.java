package com.example.tae.androidassignment1;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.example.tae.androidassignment1.controller.RealmHelper;

import java.util.ArrayList;

import io.realm.Realm;
import com.example.tae.androidassignment1.model.CustomerModel;

public class viewCustomerDetails extends ListActivity {

    private Realm realm;
    private RealmHelper realmHelper;
    private ArrayList<CustomerModel> arrayList;
    ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_view_customer_details);

        getProfileActivity();
    }

    public void getProfileActivity()
    {
        realm= Realm.getDefaultInstance();
        realmHelper= new RealmHelper(realm);

        arrayList= new ArrayList<>();
        arrayList= realmHelper.getCustomers();

        stringArrayList= new ArrayList<>();

        for(int i=0; i< arrayList.size(); i++ ){

            stringArrayList.add(arrayList.get(i).getnName());
            stringArrayList.add(arrayList.get(i).getnEmail());
            stringArrayList.add(arrayList.get(i).getnPassword());
            stringArrayList.add(arrayList.get(i).getnProfilDir());
            stringArrayList.add(arrayList.get(i).getnAge());
            stringArrayList.add(arrayList.get(i).getnDatePicker());
            stringArrayList.add(arrayList.get(i).getnCountry());
            stringArrayList.add(arrayList.get(i).getnGender());
            stringArrayList.add(arrayList.get(i).getnPostalAddress());

        }

        /**
         * Get Model
         * Put the model in the Adapter
         * Assign the adapter to the view
         */

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(
                viewCustomerDetails.this,
                android.R.layout.simple_list_item_1,
                stringArrayList);

        getListView().setAdapter(arrayAdapter);
    }
}
