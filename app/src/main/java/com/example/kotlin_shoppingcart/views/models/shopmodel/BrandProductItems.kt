package com.example.kotlin_shoppingcart.views.models.shopmodel

data class BrandProductItems(val id:Int,val productName:String,val thumbnail:String,var description:String,val price:Int){
    constructor():this(-1,"","","",-1)
}