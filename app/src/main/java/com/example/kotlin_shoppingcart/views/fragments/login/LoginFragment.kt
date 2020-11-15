package com.example.kotlin_shoppingcart.views.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.activities.HolderActivity
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import com.example.kotlin_shoppingcart.views.fragments.signup.SignupFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignup.setOnClickListener { clickHandler(it)}
        buttonLogin.setOnClickListener{clickHandler(it)}
    }

    private fun clickHandler(view: View) {

        when(view.id){
            R.id.btnSignup->{Log.e("hello","world")
                attachFragment(SignupFragment(),true)}
            R.id.buttonLogin->{startActivityWithAnimation(activity,HolderActivity::class.java,null)}
        }
    }
}