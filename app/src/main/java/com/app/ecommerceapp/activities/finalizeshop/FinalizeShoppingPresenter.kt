package com.app.ecommerceapp.activities.finalizeshop

class FinalizeShoppingPresenter: FinaliceShoppingMVP.Presenter {

    private var view: FinaliceShoppingMVP.View? = null

    override fun setView(view: FinaliceShoppingMVP.View) {
        this.view = view
    }

    override fun dropView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}