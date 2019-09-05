package com.app.ecommerceapp.activities.home

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.app.ecommerceapp.R
import com.app.ecommerceapp.activities.products.FetchEProductsActivity
import com.app.ecommerceapp.helpers.Constants.Companion.BRANDS_URL
import com.app.ecommerceapp.helpers.Constants.Companion.BRAND_SELECTED
import com.app.ecommerceapp.helpers.DialogsHelper.Companion.DialogSimpleOkButton
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeMVP.View {


    @Inject
    lateinit var presenter: HomeMVP.Presenter

    val brandsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val requestQ = Volley.newRequestQueue(this)

        val jsonAr = JsonArrayRequest(Request.Method.GET, BRANDS_URL, null, Response.Listener { response ->

            for (jsonObject in 0.until(response.length())) {
                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))
            }

            val brandsListAdapter = ArrayAdapter(this, R.layout.brand_item_text_view, brandsList)
            brandListView.adapter = brandsListAdapter

        }, Response.ErrorListener {error ->
            DialogSimpleOkButton(this, getString(R.string.failed_get_data), error.localizedMessage, DialogInterface.OnClickListener{dialog, _ ->
                dialog.cancel()
            }).show()
        })

        requestQ.add(jsonAr)

        brandListView.setOnItemClickListener { adapterView, view, i, l ->


            val tappedBrand = brandsList[i]
            val intent = Intent(this@HomeActivity, FetchEProductsActivity::class.java)

            intent.putExtra(BRAND_SELECTED, tappedBrand)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun fetchContext(): Context {
        return this
    }
}
