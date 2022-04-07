package com.gb.applicationarchitecture

import android.os.Handler


class LoginContract {
    interface View {
        fun setSuccess()
        fun showProgress()
        fun hideProgress()
        fun setError(error: String)
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChanged()


    }
}