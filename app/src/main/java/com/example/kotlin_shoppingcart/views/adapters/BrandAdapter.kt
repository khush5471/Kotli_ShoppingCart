package com.example.kotlin_shoppingcart.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shoppingcart.R

class BrandAdapter(val mContext:Context) :RecyclerView.Adapter<BrandAdapter.MyViewHolder>() {


   inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_brand,parent,false))
    }

    override fun getItemCount(): Int {
        return 15;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }
}