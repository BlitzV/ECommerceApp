package com.app.ecommerceapp.activities.cart

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.app.ecommerceapp.R
import com.app.ecommerceapp.activities.finalizeshop.FinalizeShoppingActivity
import com.app.ecommerceapp.activities.home.HomeActivity
import com.app.ecommerceapp.helpers.Constants.Companion.DELETE_URL
import com.app.ecommerceapp.helpers.Constants.Companion.LATERST_INVOICE
import com.app.ecommerceapp.helpers.Constants.Companion.VERIFY_ORDER_URL
import com.app.ecommerceapp.helpers.DialogsHelper.Companion.DialogSimpleOkButton
import dagger.android.AndroidInjection
import javax.inject.Inject

class CartProductsActivity : AppCompatActivity(), CartProductMVP.View {

    @Inject
    lateinit var presenter: CartProductMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun fetchContext(): Context {
        return this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var intent: Intent

        when {
            item?.itemId == R.id.continueShoppingItem -> {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

            }

            item?.itemId == R.id.declineOrderItem -> {

                val requestQ = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(Request.Method.GET, DELETE_URL, Response.Listener {

                    intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                }, Response.ErrorListener {error ->
                    DialogSimpleOkButton(this, getString(R.string.failed_get_data), error.localizedMessage, DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    }).show()
                })

                requestQ.add(stringRequest)
            }

            item?.itemId == R.id.verifyOrderItem -> {
                val requestQ = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(Request.Method.GET, VERIFY_ORDER_URL, Response.Listener {response ->
                    intent = Intent(this, FinalizeShoppingActivity::class.java)
                    Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
                    intent.putExtra(LATERST_INVOICE, response)
                    startActivity(intent)

                }, Response.ErrorListener {error ->
                    DialogSimpleOkButton(this, getString(R.string.failed_get_data), error.localizedMessage, DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    }).show()
                })

                requestQ.add(stringRequest)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
