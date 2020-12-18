package com.example.kotlin_shoppingcart.views.activities

import android.os.Bundle
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.fragments.viewProduct.ViewProductFragment
import com.example.kotlin_shoppingcart.views.utils.Constants

class TransactionActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mBundel = intent.getBundleExtra(Constants.INTENT_EXTRAS)
        goToFragmentOnBasisOfPayLoad(mBundel.getInt(Constants.PARCEL_KEY),mBundel)

    }

    fun goToFragmentOnBasisOfPayLoad(type:Int,bundle:Bundle){

        when(type){
            Constants.PRODUCT_DETAIL->{
                val fragment=ViewProductFragment()
                fragment.arguments=bundle
                addFragment(fragment,false)
            }
        }
    }
}