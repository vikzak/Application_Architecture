package com.gb.applicationarchitecture.ui.login

import android.os.Handler
import android.os.Looper
import com.gb.applicationarchitecture.R
import com.gb.applicationarchitecture.domain.LoginApi
import com.gb.applicationarchitecture.data.MockLoginApiImplementation


class LoginPresenter(
    private val api: LoginApi
) : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var currentResult: Boolean = false
    private var isSucces: Boolean = false
    private var onError: String = ""

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (!isSucces){
            view.setError(onError)
        } else {
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            val success = api.login(login, password)
            uiHandler.post {
                view?.hideProgress()
                if (!success) {
                    onError = "ошибка ввода данных"
                    view?.setError(onError)
                    isSucces = false
                } else {
                    view?.setSuccess()
                    isSucces = true
                    onError = ""
                    //currentResult = true
                }
            }
        }.start()
    }

    override fun onCredentialsChanged() {
        // в перспективе
    }

}