package com.example.kotlin_shoppingcart.views.fragments.cart

import android.util.Log
import com.example.kotlin_shoppingcart.views.apihelper.ApiHelper
import com.example.kotlin_shoppingcart.views.apihelper.model.usercart.CartDataResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object CartRepository {

    fun getUserCart(
        request: String,
        successHandler: (List<CartDataResponse>) -> Unit,

        connectionError: (Throwable?) -> Unit

    ) {
//        weather?q=montreal&appid=1de6a975068f98520e6710c07429c7bd
        ApiHelper.getService().getUserCart(request).enqueue(object :
            Callback<ArrayList<Object>> {
            override fun onFailure(call: Call<ArrayList<Object>>, t: Throwable) {
                Log.e("Respose Eror is", "okok " + t.toString() + " ")
                connectionError(t)
            }

            override fun onResponse(
                call: Call<ArrayList<Object>>,
                response: Response<ArrayList<Object>>
            ) {
                Log.e("Respose is", ""+response.body()+"" )


                response.body()?.let {
//                    val convertedToString: String =
//                        java.lang.String.valueOf(Object) //method 1



                    val mCartList = ArrayList<CartDataResponse>()

                    if (!it.isEmpty()) {
                        for (item in it) {
                            val jsonObj = JSONObject(item.toString())

//                            val g = Gson()
//                            val obj=g.fromJson(item.toString(),CartDataResponse::class.java)

//                            val jsonObj = JSONObject(java.lang.String.valueOf(item))

                            mCartList.add(
                                CartDataResponse(
                                    jsonObj.getString("image"),
                                    jsonObj.getString("code"),
                                    jsonObj.get("quantity").toString(),
                                    jsonObj.getString("price"),
                                    jsonObj.getString("name")
                                )
                            )


//                            mCartList.add(
//                                CartDataResponse(
//                                    obj.image,
//                                    obj.code,
//                                    obj.quantity.toString(),
//                                    obj.price,
//                                    obj.name
//                                )
//                            )
                        }
                    }
//                    successHandler(mCartList)

                }
            }
        })
    }
}