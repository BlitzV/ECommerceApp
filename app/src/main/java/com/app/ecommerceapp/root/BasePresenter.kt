package com.app.ecommerceapp.root

interface BasePresenter<T> {

    fun setView(view: T)

    fun dropView()
}