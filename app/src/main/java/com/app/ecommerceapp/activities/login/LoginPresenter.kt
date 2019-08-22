package com.app.ecommerceapp.activities.login

class LoginPresenter: LoginMVP.Presenter {

    private var view: LoginMVP.View? = null

    override fun setView(view: LoginMVP.View) {
        this.view = view
    }

    override fun dropView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}