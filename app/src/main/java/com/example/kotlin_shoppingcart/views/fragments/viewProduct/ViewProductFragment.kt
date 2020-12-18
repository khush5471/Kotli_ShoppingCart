package com.example.kotlin_shoppingcart.views.fragments.viewProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.apihelper.model.shopcart.DataItem
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import com.example.kotlin_shoppingcart.views.utils.Constants
import com.example.kotlin_shoppingcart.views.utils.Utils
import kotlinx.android.synthetic.main.fragment_view_product.*

class ViewProductFragment : BaseFragment() {
    var productItem:DataItem?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_product,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.apply {
            arguments?.let {
                productItem=it.getParcelable(Constants.PARCEL_IMAGE)
//                Utils.downloadImageByGlide(this,it.getString(Constants.PARCEL_IMAGE),imgProduct,
//                    ContextCompat.getDrawable(this,R.drawable.bg_grey))
                Utils.downloadImageByGlide(this,"https://beststore.receptumelogic.com/images/"+productItem?.image,imgProduct,
                    ContextCompat.getDrawable(this,R.drawable.bg_grey))

                txtProductName.text=productItem?.name
                productPrice.text=productItem?.price
            }
        }

        imgBack.setOnClickListener {
            activity?.onBackPressed()
        }

    }
}