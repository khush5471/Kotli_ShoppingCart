package com.example.kotlin_shoppingcart.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shoppingcart.R
import kotlinx.android.synthetic.main.item_brand_products.view.*

class ProductsAdapter(val mContext:Context) :RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_brand_products,parent,false))
    }

    override fun getItemCount(): Int {
        return 15;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){


    }
}