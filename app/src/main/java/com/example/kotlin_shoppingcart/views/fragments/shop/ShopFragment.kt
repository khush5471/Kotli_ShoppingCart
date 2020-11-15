package com.example.kotlin_shoppingcart.views.fragments.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment

class ShopFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop,container,false)
    }
}