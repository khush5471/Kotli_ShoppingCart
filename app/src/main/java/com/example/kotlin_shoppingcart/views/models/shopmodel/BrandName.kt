package com.example.kotlin_shoppingcart.views.models.shopmodel

data class BrandName(val id:Int,val brandName:String,var isSelected:Boolean){
    constructor() : this(-1, "",false
    )
}