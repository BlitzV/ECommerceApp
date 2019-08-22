package com.app.ecommerceapp.root

import android.content.Context

interface BaseView<T> {

    fun fetchContext(): Context
}