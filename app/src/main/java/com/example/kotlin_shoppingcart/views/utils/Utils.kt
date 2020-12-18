package com.example.kotlin_shoppingcart.views.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.util.Patterns
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.kotlin_shoppingcart.R

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


    fun isNetworkAvailable(context: Context?): Boolean {
        context?.let {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            if (netInfo != null && netInfo.isConnectedOrConnecting) {
                return true
            }
        }

        return false
    }

    fun showSnackBar(context: Context?, message: String?) {
        DialogHelper.createAlertDialog(context, message)
    }

    fun validEmail(email: String?): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun saveDataInPreference(
        context: Context,
        key: String?,
        value: String?
    ) {
        val sharedPref = context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun readDataFromPreference(context: Context, key: String?): String? {
        val sharedPref = context.getSharedPreferences(
            context.getString(
                R.string.preference_file_key
            ), Context.MODE_PRIVATE
        )
        return sharedPref.getString(key, "")
    }
}