package com.example.kotlin_shoppingcart.views.fragments.shop

import android.util.Log
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandName
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandProductItems
import com.example.kotlin_shoppingcart.views.utils.FirebaseHandler
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object ShopRepository {


    fun getBrandList(brandList:(ArrayList<BrandName>)->Unit){

        FirebaseHandler.getFirebaseDatabaseReference("USERS").child("BrandCategories").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

                Log.e("Error","okok")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val mList=ArrayList<BrandName>()
                val data=snapshot.getValue(BrandName::class.java)

                data?.let {
                    for (vaa in snapshot.children){


                        val brandData=vaa.getValue(BrandName::class.java)
                        brandData?.let {

                            mList.add(it)
                        }

                    }
                }

                brandList(mList)
            }
        })

    }

    fun getBrandProducts(productList:(ArrayList<BrandProductItems>)->Unit,brandName:String){


        FirebaseHandler.getFirebaseDatabaseReference("USERS")
            .child("Brands")
            .child(brandName)
            .child("mListBrandData")
            .addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val mList=ArrayList<BrandProductItems>()


                snapshot.children.let {

                    for (child in it){
                        val data=child.getValue(BrandProductItems::class.java)
                        data?.let { it1 -> mList.add(it1) }

                    }
                }
                productList(mList)
            }
        })

    }


}