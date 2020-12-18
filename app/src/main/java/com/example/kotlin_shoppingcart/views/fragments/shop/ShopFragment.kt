package com.example.kotlin_shoppingcart.views.fragments.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.activities.TransactionActivity
import com.example.kotlin_shoppingcart.views.adapters.BrandAdapter
import com.example.kotlin_shoppingcart.views.adapters.CartAdapter
import com.example.kotlin_shoppingcart.views.adapters.ProductsAdapter
import com.example.kotlin_shoppingcart.views.apihelper.ApiHelper
import com.example.kotlin_shoppingcart.views.apihelper.WheatherResponse
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginRequest
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginResponse
import com.example.kotlin_shoppingcart.views.apihelper.model.shopcart.DataItem
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandName
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandProductItems
import com.example.kotlin_shoppingcart.views.utils.Constants
import kotlinx.android.synthetic.main.fragment_shop.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopFragment : BaseFragment(), SelectBrand, ProductClickInterface, CartClickInterface {

    var mAdapterBrands: BrandAdapter? = null
    var mLayoutManager: LinearLayoutManager? = null

    var mAdapterProducts: ProductsAdapter? = null
    var mGridLayoutManager: GridLayoutManager? = null

    var mAdapterCart: CartAdapter? = null
    var mLayoutManagerCart: GridLayoutManager? = null

    var mViewModel: ShopViewModel? = null
    var mBrandList = ArrayList<BrandName>()
    var mBrandProductList = ArrayList<BrandProductItems>()
    var mCartList = ArrayList<DataItem>()
    var mSelectedBrandName = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {

            mAdapterBrands = BrandAdapter(it, mBrandList, this)
            mLayoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
            recyclerBrands.layoutManager = mLayoutManager
            recyclerBrands.adapter = mAdapterBrands

            mAdapterProducts = ProductsAdapter(it, mBrandProductList, this)
            mGridLayoutManager = GridLayoutManager(it, 2, GridLayoutManager.VERTICAL, false)
            recyclerBrandProducts.layoutManager = mGridLayoutManager
            recyclerBrandProducts.adapter = mAdapterProducts

            mAdapterCart = CartAdapter(it, mCartList, this)
            mLayoutManagerCart = GridLayoutManager(it, 2, GridLayoutManager.VERTICAL, false)
            recyclerCart.layoutManager = mLayoutManagerCart
            recyclerCart.adapter = mAdapterCart

            init()
            attachObserver()

//            kkk()
//            login()
        }

    }

    /*Initialize the views.*/
    fun init() {
        mViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)
//        mViewModel?.getBrandList()
        mViewModel?.getCartListFromServer()
    }


    /*Attaching observer to observe data*/
    fun attachObserver() {
        mViewModel?.mBrandList?.observe(this, Observer {
            it?.let {
                mBrandList.addAll(it)
                mBrandList.get(0).isSelected = true
                Log.e("size ", "is " + it.size)
                mAdapterBrands?.notifyDataSetChanged()
                mViewModel?.getBrandProducts(mBrandList.get(0).brandName)
            }
        })

        mViewModel?.mBrandProductList?.observe(this, Observer {
            it?.let {
                mBrandProductList.clear()
                mBrandProductList.addAll(it)
                mAdapterProducts?.notifyDataSetChanged()
            }
        })

        mViewModel?.mCartList?.observe(this, Observer {
            mCartList.clear()
            it?.data?.let {

                for (item in it) {
                    item?.let {
                        mCartList.add(it)
                    }

                }

            }
            mAdapterCart?.notifyDataSetChanged()

        })
    }

    override fun selectBrand(brand: BrandName) {
        mViewModel?.getBrandProducts(brand.brandName)
        val aa = { hello: Int, s: String -> hello * hello }
        val aaa: (String, Int) -> String = { ss: String, pp: Int -> ss + pp }
        aa(5, "")
    }

    fun kkk() {
//        weather?q=montreal&appid=1de6a975068f98520e6710c07429c7bd
        ApiHelper.getService().getTestData("montreal", "1de6a975068f98520e6710c07429c7bd")
            .enqueue(object : Callback<WheatherResponse> {
                override fun onFailure(call: Call<WheatherResponse>, t: Throwable) {
                    Log.e("Respose Eror is", "okok " + t.toString())

                }

                override fun onResponse(
                    call: Call<WheatherResponse>,
                    response: Response<WheatherResponse>
                ) {

                    Log.e("Respose is", "okok " + response.body()?.name)
                }
            })
    }

    fun login() {
//        weather?q=montreal&appid=1de6a975068f98520e6710c07429c7bd
        val request = LoginRequest("11111", "one@gmail.com")
        ApiHelper.getService().loginUser("one@gmail.com", "11111")
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("Respose Eror is", "okok " + t.toString() + " ")

                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {

                    Log.e("Respose is", "okok " + response.body()?.message)
                }
            })
    }

    override fun getClickedItem(item: BrandProductItems) {
        val bundle = Bundle()
        bundle.putInt(Constants.PARCEL_KEY, Constants.PRODUCT_DETAIL)
        bundle.putString(Constants.PARCEL_IMAGE, item.thumbnail)

        startActivityWithAnimation(activity, TransactionActivity::class.java, bundle)
    }

    override fun getClickedItem(item: DataItem) {
        val bundle = Bundle()
        bundle.putInt(Constants.PARCEL_KEY, Constants.PRODUCT_DETAIL)
        bundle.putParcelable(Constants.PARCEL_IMAGE, item)

        startActivityWithAnimation(activity, TransactionActivity::class.java, bundle)
    }
}