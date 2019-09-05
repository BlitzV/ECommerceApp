package com.app.ecommerceapp.activities.signup

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.ecommerceapp.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(), SignUpMVP.View {

    @Inject
    lateinit var presenter: SignUpMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    override fun fetchContext(): Context {
        return this
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }
}
