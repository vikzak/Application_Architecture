package com.gb.applicationarchitecture

import android.os.Handler
import androidx.annotation.MainThread


class LoginContract {
    interface View {
        @MainThread
        fun setSuccess()

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()

        @MainThread
        fun setError(error: String)
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChanged()


    }
}