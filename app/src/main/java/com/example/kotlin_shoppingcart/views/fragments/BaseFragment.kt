package com.example.kotlin_shoppingcart.views.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.fragment.app.Fragment
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.activities.BaseActivity
import com.example.kotlin_shoppingcart.views.utils.Constants

open class BaseFragment : Fragment(){
    private var mDialoge: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun attachFragment(fragment: BaseFragment, addToBackStack: Boolean) {

        val tag: String = fragment::class.java.simpleName
        activity?.let {
            Log.e("hello","world 2")
            val manager = it.supportFragmentManager
            val oldFragmentObject = manager?.findFragmentByTag(tag)
            val transaction = manager?.beginTransaction()
            transaction?.setCustomAnimations(R.anim.anim_in, R.anim.anim_out, R.anim.anim_in_reverse, R.anim.anim_out_reverse)
            if (oldFragmentObject != null) {
                manager.popBackStackImmediate(tag, 0)
            } else {
                if (addToBackStack) {
                    transaction?.addToBackStack(tag)
                }

                transaction?.add(R.id.container, fragment, tag)
                    ?.commitAllowingStateLoss()
            }
        }

        Log.e("hello","world 3")

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

    fun startActivityWithouAnimation(activityContext: Activity?, activity: Class<out BaseActivity>, bundle: Bundle?,isAnimate: Boolean) {
        activityContext?.let {
            val intent = Intent(activityContext, activity)
            if (bundle != null) {
                intent.putExtra(Constants.INTENT_EXTRAS, bundle)
            }
            activityContext.startActivity(intent)
//            activityContext.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
//            activityContext.overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
            if (isAnimate) {
                activityContext.overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
            }
        }
    }

    //shows the dialoge
    open fun showDialoge() {
        mDialoge = Dialog(context!!)
        mDialoge?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        mDialoge?.window
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //                mProgressDialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        mDialoge?.setContentView(R.layout.dialog_progress)
        mDialoge?.setCancelable(false)
        mDialoge?.show()
    }
    //shows the dialoge
    open fun hideDialoge() {
        mDialoge?.hide()
    }
}