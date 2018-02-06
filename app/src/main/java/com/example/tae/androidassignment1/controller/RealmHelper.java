package com.example.tae.androidassignment1.controller;

import java.util.ArrayList;
import com.example.tae.androidassignment1.model.CustomerModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by TAE on 02-Feb-18.
 */

public class RealmHelper {

    Realm realm;


    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveCustomer(final CustomerModel customerModel)

            //Async
    {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(customerModel);
                }
            });
    }

    public ArrayList<CustomerModel> getCustomers()
    {
        ArrayList<CustomerModel> customerModelArrayList = new ArrayList<>();
        RealmResults<CustomerModel> realmResults = realm.where(CustomerModel.class).findAll();

        for(CustomerModel customerModel: realmResults){
            customerModelArrayList.add(customerModel);
        }
        return customerModelArrayList;
    }

}
