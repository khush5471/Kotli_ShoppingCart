package com.example.kotlin_shoppingcart.views.fragments.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_shoppingcart.views.apihelper.model.usercart.CartDataResponse

class CartViewModel : ViewModel() {

   var mGetUserCartList=MutableLiveData<List<CartDataResponse>>()

    fun getUserCart(request:String){
        CartRepository.getUserCart(request,{mGetUserCartList.value=it},{})
    }
}