package com.example.kotlin_shoppingcart.views.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.activities.BaseActivity
import com.example.kotlin_shoppingcart.views.utils.Constants
import com.example.kotlin_shoppingcart.views.utils.Constants.INTENT_EXTRAS

open class BaseFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun startActivityWithAnimation(activityContext: Activity?, activity: Class<out BaseActivity>, bundle: Bundle?) {
        activityContext?.let {
            val intent = Intent(activityContext, activity)
            if (bundle != null) {
                intent.putExtra(Constants.INTENT_EXTRAS, bundle)
            }
            activityContext.startActivity(intent)
            activityContext.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
//            activityContext.overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
//            activityContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }
    }
}