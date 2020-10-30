package com.example.kotlin_shoppingcart.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kotlin_shoppingcart.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun addFragment(fragment: Fragment,addToBackStack:Boolean){

        val tag: String = fragment::class.java.simpleName
        val supportFragmentManager = supportFragmentManager
        val transaction: FragmentTransaction? = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            //   transaction?.setCustomAnimations(R.anim.anim_in, R.anim.anim_out, R.anim.anim_in_reverse, R.anim.anim_out_reverse)
            transaction?.addToBackStack(tag)
        }
        transaction?.add(R.id.container, fragment, tag)?.commitAllowingStateLoss()
    }
}