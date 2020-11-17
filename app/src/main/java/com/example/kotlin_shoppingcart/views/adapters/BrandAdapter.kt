package com.example.kotlin_shoppingcart.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.fragments.shop.SelectBrand
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandName
import kotlinx.android.synthetic.main.item_brand.view.*

class BrandAdapter(
    val mContext: Context,
    var mBrandList: ArrayList<BrandName>,
    val mListner: SelectBrand
) : RecyclerView.Adapter<BrandAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_brand, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mBrandList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.txt_brandName.text = mBrandList.get(position).brandName
        if (mBrandList[position].isSelected) {
            holder.itemView.cardBrand.setCardBackgroundColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.blue
                )
            )
            holder.itemView.txt_brandName.setTextColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.white
                )
            )
        } else {
            holder.itemView.cardBrand.setCardBackgroundColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.white
                )
            )
            holder.itemView.txt_brandName.setTextColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.blue
                )
            )

        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        init {

            view.setOnClickListener {
                mListner.selectBrand(mBrandList.get(adapterPosition))
                for (item in mBrandList){
                    item.isSelected=false
                }
                mBrandList.get(adapterPosition).isSelected = true
                notifyDataSetChanged()
            }

        }
    }
}