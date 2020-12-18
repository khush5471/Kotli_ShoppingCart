package com.example.kotlin_shoppingcart.views.fragments.shop

import com.example.kotlin_shoppingcart.views.apihelper.model.shopcart.DataItem
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandProductItems

interface CartClickInterface {

    fun getClickedItem(item: DataItem)
}