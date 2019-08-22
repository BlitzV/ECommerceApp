package com.app.ecommerceapp.activities.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.ecommerceapp.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginMVP.View {

    @Inject
    lateinit var presenter: LoginMVP.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun fetchContext(): Context {
        return this
    }
}
