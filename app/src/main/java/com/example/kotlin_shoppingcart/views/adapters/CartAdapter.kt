package com.example.kotlin_shoppingcart.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.apihelper.model.shopcart.DataItem
import com.example.kotlin_shoppingcart.views.fragments.shop.CartClickInterface
import com.example.kotlin_shoppingcart.views.fragments.shop.ProductClickInterface
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandProductItems
import com.example.kotlin_shoppingcart.views.utils.Utils
import kotlinx.android.synthetic.main.item_brand_products.view.*

class CartAdapter(val mContext:Context, val mList:ArrayList<DataItem>, val mClick: CartClickInterface) :RecyclerView.Adapter<CartAdapter.MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_brand_products,parent,false))
    }

    override fun getItemCount(): Int {
        return mList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Utils.downloadImageByGlide(mContext,"https://beststore.receptumelogic.com/images/"+mList[position].image,holder.itemView.imgProduct,ContextCompat.getDrawable(mContext,R.drawable.bg_grey))
        holder.itemView.txtProductName.text=mList[position].name;
        holder.itemView.txt_price.text="$ ${mList[position].price}"
        holder.itemView.txtHelloWorld.text=mList[position].code
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        init {
            view.setOnClickListener{mClick.getClickedItem(mList.get(adapterPosition))}
        }

    }
}