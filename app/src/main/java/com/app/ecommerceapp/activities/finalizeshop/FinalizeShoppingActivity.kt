package com.app.ecommerceapp.activities.finalizeshop

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.app.ecommerceapp.R
import com.app.ecommerceapp.activities.ThankYouScreen
import com.app.ecommerceapp.helpers.Constants.Companion.CALCULATE_TOTAL_PRICE_URL
import com.app.ecommerceapp.helpers.Constants.Companion.LATERST_INVOICE
import com.app.ecommerceapp.helpers.DialogsHelper.Companion.DialogSimpleOkButton
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_finalize_shopping.*
import java.math.BigDecimal
import javax.inject.Inject

class FinalizeShoppingActivity : AppCompatActivity(), FinaliceShoppingMVP.View {

    @Inject
    lateinit var presenter: FinaliceShoppingMVP.Presenter

    var ttPrice: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalize_shopping)

        val requestQ = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(Request.Method.GET, CALCULATE_TOTAL_PRICE_URL+intent.getStringExtra(LATERST_INVOICE), Response.Listener { response ->
            btnPaymentProcessing.text = getString(R.string.pay_paypal,response)
            ttPrice = response.toLong()

        }, Response.ErrorListener {error ->
            DialogSimpleOkButton(this,getString(R.string.failed_get_data), error.localizedMessage, DialogInterface.OnClickListener{ dialog, _ ->
                dialog.cancel()
            }).show()
        })
        requestQ.add(stringRequest)

        val paypalConfig: PayPalConfiguration = PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(getString(R.string.paypalClientId))
        val ppService = Intent(this@FinalizeShoppingActivity, PayPalService::class.java)
        ppService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
        startService(ppService)

        btnPaymentProcessing.setOnClickListener {

            val ppProcessing = PayPalPayment(BigDecimal.valueOf(ttPrice),
                getString(R.string.currency_usd),getString(R.string.kotlin_store),
                PayPalPayment.PAYMENT_INTENT_SALE)

            val paypalPaymentIntent = Intent(this, PaymentActivity::class.java)
            paypalPaymentIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
            paypalPaymentIntent.putExtra(PaymentActivity.EXTRA_PAYMENT, ppProcessing)
            startActivityForResult(paypalPaymentIntent, 1000)
        }
    }

    override fun fetchContext(): Context {
        return this
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {

                val intent = Intent(this, ThankYouScreen::class.java)
                startActivity(intent)

            } else
                DialogSimpleOkButton(this, getString(R.string.sorry), getString(R.string.something_went_wrong),
                    DialogInterface.OnClickListener{dialog, which ->
                        dialog.cancel()
                    }).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        stopService(Intent(this, PayPalService::class.java))
    }
}
