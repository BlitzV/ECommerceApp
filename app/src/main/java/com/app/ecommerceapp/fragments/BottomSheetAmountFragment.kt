package com.app.ecommerceapp.fragments


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.app.ecommerceapp.R
import com.app.ecommerceapp.activities.cart.CartProductsActivity
import com.app.ecommerceapp.helpers.Constants.Companion.PointURLAmountfragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_amount.*

/**
 * A simple [Fragment] subclass.
 */
class BottomSheetAmountFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): BottomSheetAmountFragment{
            val bottomSheetDialogFragment: BottomSheetAmountFragment = BottomSheetAmountFragment()

            return bottomSheetDialogFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddToCart.setOnClickListener {

            edtEnterAmount.error = null
            val requestQ = Volley.newRequestQueue(activity)

            if(edtEnterAmount!=null){
                val stringRequest = StringRequest(Request.Method.GET, PointURLAmountfragment+edtEnterAmount.text, Response.Listener {
                    val intent = Intent(activity, CartProductsActivity::class.java)
                    startActivity(intent)

                }, Response.ErrorListener {error ->
                    Toast.makeText(activity, "Failed: ${error.networkResponse} \n ${getString(R.string.failed_add_item)}", Toast.LENGTH_SHORT).show()
                })
                requestQ.add(stringRequest)
            } else {
                edtEnterAmount.error = getString(R.string.error_empty_field)
                edtEnterAmount.requestFocus()
            }
        }
    }
}
