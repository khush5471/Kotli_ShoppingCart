package com.example.kotlin_shoppingcart.views.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment

class CartFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return         return inflater.inflate(R.layout.fragment_cart,container,false)

    }
}