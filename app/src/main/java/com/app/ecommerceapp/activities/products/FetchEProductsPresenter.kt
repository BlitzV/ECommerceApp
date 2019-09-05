package com.app.ecommerceapp.activities.products

class FetchEProductsPresenter: FetchEProductsMVP.Presenter {

    private var view: FetchEProductsMVP.View? = null

    override fun setView(view: FetchEProductsMVP.View) {
        this.view = view
    }

    override fun dropView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}