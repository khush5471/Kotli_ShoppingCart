package com.example.kotlin_shoppingcart.views.customizeViews

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

class EditTextCoustomize:androidx.appcompat.widget.AppCompatEditText{
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
//        val type = Typeface.createFromAsset(context.assets, Constants.MONTSERRAT_REGULAR)
//        typeface=type
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        val type = Typeface.createFromAsset(context.assets, Constants.MONTSERRAT_REGULAR)
//        typeface=type
    }
    constructor(context: Context) : super(context) {
//        val type = Typeface.createFromAsset(context.assets, Constants.MONTSERRAT_REGULAR)
//        typeface=type
    }

    override fun setHeight(pixels: Int) {
        super.setHeight(pixels)
    }

}