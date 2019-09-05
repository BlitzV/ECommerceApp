package com.app.ecommerceapp.activities.login

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
import com.app.ecommerceapp.activities.signup.SignUpActivity
import com.app.ecommerceapp.helpers.Constants.Companion.LOGIN_URL
import com.app.ecommerceapp.helpers.Constants.Companion.LOGIN_URL_PASS
import com.app.ecommerceapp.helpers.DialogsHelper.Companion.DialogSimpleOkButton
import com.app.ecommerceapp.models.Person
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginMVP.View {

    @Inject
    lateinit var presenter: LoginMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btnLogin.setOnClickListener {
            val loginUrl = LOGIN_URL+activity_main_edtEmail+LOGIN_URL_PASS+activity_main_edtPassword

            val requestQ = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET, loginUrl, Response.Listener { response ->

                if(response == "The user does exist"){
                    Person.email = activity_main_edtEmail.text.toString()
                    Toast.makeText(this,response, Toast.LENGTH_SHORT).show()

                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                } else {
                    DialogSimpleOkButton(this, getString(R.string.failed_get_data),response, DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    }).show()
                }

            }, Response.ErrorListener { error ->
                DialogSimpleOkButton(this, getString(R.string.failed_get_data),error.localizedMessage, DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                }).show()
            })

            requestQ.add(stringRequest)
        }

        activity_main_btnSignUp.setOnClickListener {

            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
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
