package com.example.kotlin_shoppingcart.views.apihelper.model.signup

import com.google.gson.annotations.SerializedName

data class SignupRequests(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)
