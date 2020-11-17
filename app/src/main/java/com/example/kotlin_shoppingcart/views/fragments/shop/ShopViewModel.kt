package com.example.kotlin_shoppingcart.views.fragments.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandName
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandProductItems

class ShopViewModel : ViewModel() {


    val mBrandList=MutableLiveData<ArrayList<BrandName>>()
    val mBrandProductList=MutableLiveData<ArrayList<BrandProductItems>>()

    fun getBrandList(){

        ShopRepository.getBrandList { mBrandList.value=it }
    }

    fun getBrandProducts(brandName:String){
        ShopRepository.getBrandProducts ({ mBrandProductList.value=it},brandName)
    }
}