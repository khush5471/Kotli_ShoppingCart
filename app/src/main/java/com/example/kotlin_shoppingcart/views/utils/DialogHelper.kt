package com.example.kotlin_shoppingcart.views.utils


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin_shoppingcart.R

object DialogHelper {

    fun createAlertDialog(context: Context?, message: String?) {
        context?.let {
            val alertDialog = Dialog(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth)
            alertDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.setContentView(R.layout.dialog_alert_normal)
            alertDialog.setCancelable(false)
//            val imageCancel = alertDialog.findViewById<ImageView>(R.id.imageDismissAlertDialog)
            val txtInfoAlertDialog = alertDialog.findViewById<TextView>(R.id.txtInfoAlertDialog)
            val buttonOk = alertDialog.findViewById<Button>(R.id.button_ok)
//            imageCancel.setOnClickListener { alertDialog.dismiss() }
            buttonOk.setOnClickListener { alertDialog.dismiss() }
            txtInfoAlertDialog.text = message
            alertDialog.show()
        }
    }
}