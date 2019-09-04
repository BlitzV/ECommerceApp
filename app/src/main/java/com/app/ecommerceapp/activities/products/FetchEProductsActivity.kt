package com.app.ecommerceapp.activities.products

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.app.ecommerceapp.R
import com.app.ecommerceapp.adapters.EProductAdapterItemView
import com.app.ecommerceapp.helpers.Constants.Companion.BRAND_SELECTED
import com.app.ecommerceapp.helpers.Constants.Companion.PRODUCT_URL
import com.app.ecommerceapp.models.EProduct
import kotlinx.android.synthetic.main.activity_fetch_eproducts.*

class FetchEProductsActivity : AppCompatActivity(), EProductAdapterItemView.OnEventAction {

    var productList = ArrayList<EProduct>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)

        val selectedBrand: String = intent.getStringExtra(BRAND_SELECTED)
       // txtBrandName.text = "Products of $selectedBrand"
        txtBrandName.text = getString(R.string.concatenate_product,getString(R.string.product_of),selectedBrand)

        val requestQ = Volley.newRequestQueue(this@FetchEProductsActivity)
        var jsonAR = JsonArrayRequest(Request.Method.GET, PRODUCT_URL+selectedBrand, null, Response.Listener {response ->

            for (productJOIndex in 0.until(response.length())){
                productList.add(EProduct(response.getJSONObject(productJOIndex).getInt("id"),
                    response.getJSONObject(productJOIndex).getString("name"),
                    response.getJSONObject(productJOIndex).getInt("price"),
                    response.getJSONObject(productJOIndex).getString("picture")))
            }

            val pAdapter = EProductAdapterItemView(this@FetchEProductsActivity, productList,this)
            productsRV.layoutManager = LinearLayoutManager(this@FetchEProductsActivity)
            productsRV.adapter = pAdapter

        }, Response.ErrorListener {

        })
    }

    override fun EventSelected(position: Int) {

    }
}
