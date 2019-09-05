package com.app.ecommerceapp.activities.products

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.app.ecommerceapp.R
import com.app.ecommerceapp.adapters.EProductAdapterItemView
import com.app.ecommerceapp.fragments.BottomSheetAmountFragment
import com.app.ecommerceapp.helpers.Constants.Companion.BRAND_SELECTED
import com.app.ecommerceapp.helpers.Constants.Companion.PRODUCT_URL
import com.app.ecommerceapp.helpers.DialogsHelper.Companion.DialogSimpleOkButton
import com.app.ecommerceapp.models.EProduct
import kotlinx.android.synthetic.main.activity_fetch_eproducts.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class FetchEProductsActivity : AppCompatActivity(), FetchEProductsMVP.View, EProductAdapterItemView.OnEventAction {

    @Inject
    lateinit var presenter: FetchEProductsMVP.Presenter

    var productList = ArrayList<EProduct>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)

        val selectedBrand: String = intent.getStringExtra(BRAND_SELECTED)
       // txtBrandName.text = "Products of $selectedBrand"
        txtBrandName.text = getString(R.string.concatenate_product,getString(R.string.product_of),selectedBrand)

        val requestQ = Volley.newRequestQueue(this@FetchEProductsActivity)
        val jsonAR = JsonArrayRequest(Request.Method.GET, PRODUCT_URL+selectedBrand, null, Response.Listener {response ->

            for (productJOIndex in 0.until(response.length())){
                productList.add(EProduct(response.getJSONObject(productJOIndex).getInt("id"),
                    response.getJSONObject(productJOIndex).getString("name"),
                    response.getJSONObject(productJOIndex).getInt("price"),
                    response.getJSONObject(productJOIndex).getString("picture")))
            }

            val pAdapter = EProductAdapterItemView(this@FetchEProductsActivity, productList,this)
            productsRV.layoutManager = LinearLayoutManager(this@FetchEProductsActivity)
            productsRV.adapter = pAdapter

        }, Response.ErrorListener {error ->
            DialogSimpleOkButton(this@FetchEProductsActivity,getString(R.string.failed_get_data),
                error.localizedMessage,DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            }).show()
        })

        requestQ.add(jsonAR)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun fetchContext(): Context {
        return this
    }

    override fun EventSelected(position: Int) {
        val bottomSheetAmountFragment = BottomSheetAmountFragment()
        bottomSheetAmountFragment.show(Objects.requireNonNull(this).supportFragmentManager, bottomSheetAmountFragment.tag)
    }
}
