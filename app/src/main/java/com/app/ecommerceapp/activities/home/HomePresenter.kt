package com.app.ecommerceapp.activities.home

class HomePresenter: HomeMVP.Presenter {

    private var view: HomeMVP.View? = null

    override fun setView(view: HomeMVP.View) {
        this.view = view
    }

    override fun dropView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}