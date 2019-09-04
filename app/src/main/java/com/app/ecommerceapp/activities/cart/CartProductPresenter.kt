package com.app.ecommerceapp.activities.cart

class CartProductPresenter: CartProductMVP.Presenter {

    private var view: CartProductMVP.View? = null

    override fun setView(view: CartProductMVP.View) {
        this.view = view
    }

    override fun dropView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}