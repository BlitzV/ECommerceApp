package com.app.ecommerceapp.activities.signup

class SignUpPresenter: SignUpMVP.Presenter {

    private var view: SignUpMVP.View? = null

    override fun setView(view: SignUpMVP.View) {
        this.view = view
    }

    override fun dropView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}