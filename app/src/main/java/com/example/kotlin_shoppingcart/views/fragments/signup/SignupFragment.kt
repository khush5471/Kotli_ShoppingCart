package com.example.kotlin_shoppingcart.views.fragments.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment

class SignupFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup,container,false)
    }
}