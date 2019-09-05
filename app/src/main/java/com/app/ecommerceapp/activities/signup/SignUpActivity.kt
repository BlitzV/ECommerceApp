package com.app.ecommerceapp.activities.signup

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
import com.app.ecommerceapp.activities.home.HomeActivity
import com.app.ecommerceapp.helpers.Constants.Companion.SIGN_UP_URL
import com.app.ecommerceapp.helpers.Constants.Companion.SIGN_UP_URL_PASS
import com.app.ecommerceapp.helpers.Constants.Companion.SIGN_UP_URL_USERNAME
import com.app.ecommerceapp.helpers.DialogsHelper.Companion.DialogSimpleOkButton
import com.app.ecommerceapp.models.Person
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_sign_up.*
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(), SignUpMVP.View {

    @Inject
    lateinit var presenter: SignUpMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sign_up_layout_btnLogin.setOnClickListener {

            if (sign_up_layout_edtPassword.text.toString() == sign_up_layout_edtConfirmPassword.text.toString()){

                val signUpURL = SIGN_UP_URL+sign_up_layout_edtEmail.text.toString()+
                        SIGN_UP_URL_USERNAME+sign_up_layout_edtUsername.text.toString()+
                        SIGN_UP_URL_PASS+sign_up_layout_edtPassword.text.toString()

                val requestQ = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL, Response.Listener { response ->
                    if (response == "A user with this Email Address already exists") {

                        DialogSimpleOkButton(this, getString(R.string.message),response,DialogInterface.OnClickListener { dialog, _ ->
                            dialog.cancel()
                        }).show()
                    } else {
                        Person.email = sign_up_layout_edtEmail.text.toString()

                        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()

                        val homeIntent = Intent(this, HomeActivity::class.java)
                        startActivity(homeIntent)
                    }
                }, Response.ErrorListener {error ->
                    DialogSimpleOkButton(this, getString(R.string.failed_get_data),error.localizedMessage,DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    }).show()
                })
                requestQ.add(stringRequest)
            } else {

                DialogSimpleOkButton(this, getString(R.string.message),getString(R.string.password_mismatch),DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                }).show()
            }
        }

        sign_up_layout_btnLogin.setOnClickListener {
            finish()
        }
    }

    override fun fetchContext(): Context {
        return this
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }
}
