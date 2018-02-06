package com.example.tae.androidassignment1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tae.androidassignment1.model.CustomerModel;
import com.example.tae.androidassignment1.model.ItemClickListener;

import java.util.ArrayList;

/* Steps to follow:
         * 1 Initialize the objects in the constructor
         * 2 Create ViewHolder Class
         *      2.1 MyViewHolder extend  RecyclerView.ViewHolder
         *      2.2 Create a constructor of ViewHolder class: Initialize row elements
        * 3 Create Override recyclerview adapter methods
        */

class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

   private Context applicationContext;
   private int row_customer;
   private ArrayList<CustomerModel> arrayList;

    public PersonAdapter(Context applicationContext, int row_customer, ArrayList<CustomerModel> arrayList) {
    this.applicationContext = applicationContext;
    this.row_customer = row_customer;
    this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_customer,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mName.setText((arrayList.get(position).getnName()));
        holder.mEmail.setText((arrayList.get(position).getnEmail()));
        holder.mPass.setText((arrayList.get(position).getnPassword()));
        holder.mAge.setText((arrayList.get(position).getnAge()));
        holder.mDOB.setText((arrayList.get(position).getnDatePicker()));
        holder.mCountry.setText((arrayList.get(position).getnCountry()));
        holder.mGender.setText((arrayList.get(position).getnGender()));
        holder.mPhoto.setTag((arrayList.get(position).getnProfilDir()));

       // holder.mPhoto.(arrayList.get(position).getnProfilDir()));





        //holder.getnPostalAddress.setText((arrayList.get(position).getnPostalAddress()));
        holder.callItemClick(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(applicationContext,
                        arrayList.get(position).getnEmail(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /*load the row layout*/

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        /*cache view reference*/
        private TextView mName, mEmail, mPass, mAge, mDOB, mCountry, mGender;
        private ImageView mPhoto;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            mName = itemView.findViewById(R.id.tvName);
            mEmail = itemView.findViewById(R.id.mEmail);
            mPass = itemView.findViewById(R.id.mPass);
            mAge = itemView.findViewById(R.id.mAge);
            mDOB = itemView.findViewById(R.id.mDOB);
            mCountry = itemView.findViewById(R.id.mCountry);
            mGender = itemView.findViewById(R.id.mGender);
            mPhoto = itemView.findViewById(R.id.mPhoto);

            itemView.setOnClickListener(this);
        }

        public void callItemClick (ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onClick(view, getPosition());
        }

    }
}
