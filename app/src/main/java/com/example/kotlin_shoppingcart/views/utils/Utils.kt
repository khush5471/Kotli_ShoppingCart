package com.example.kotlin_shoppingcart.views.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object Utils {

    fun downloadImageByGlide(
        context: Context,
        url: String?,
        imageView: ImageView,
        placeHolder: Drawable?
    ) {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(placeHolder)
            .into(imageView)


    }
}