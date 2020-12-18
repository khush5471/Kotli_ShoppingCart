package com.example.kotlin_shoppingcart.views.apihelper

import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginRequest
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginResponse
import com.example.kotlin_shoppingcart.views.apihelper.model.shopcart.CartResponse
import com.example.kotlin_shoppingcart.views.apihelper.model.signup.SignupRequests
import com.example.kotlin_shoppingcart.views.apihelper.model.signup.SignupResponse
import retrofit2.Call
import retrofit2.http.*

interface WebService {


    @GET("weather")
    fun getTestData(@Query("q") city:String,@Query("appid") appId:String ): Call<WheatherResponse>

    @FormUrlEncoded
    @POST("login/loginapi.php")
    fun loginUser(@Field("email") email:String, @Field("password") password:String ): Call<LoginResponse>

//    fun loginUser(@Query("email") email:String,@Query("password") password:String ): Call<LoginResponse>

//    fun loginUser(@Body request:LoginRequest ): Call<LoginResponse>


    @FormUrlEncoded
    @POST("signup/signupapi.php")
    fun signupUser(@Field("email") email:String, @Field("password") password:String,@Field("name") name:String ): Call<SignupResponse>

//    @FormUrlEncoded
    @GET("products/productsapi.php")
    fun getCartList(): Call<CartResponse>


    @FormUrlEncoded
    @POST("products/addAndRemoveCartApi.php")
    fun addItemToCart(@Field("email") email:String, @Field("cart") password:ArrayList<String> ): Call<Object>


    @FormUrlEncoded
    @POST("cart/getUserCartApi.php")
    fun getUserCart(@Field("email") email:String): Call<ArrayList<Object>>


}