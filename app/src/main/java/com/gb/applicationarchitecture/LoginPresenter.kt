package com.gb.applicationarchitecture

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep


class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var currentResult: Boolean = false

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3000L)
            uiHandler.post {
                view?.hideProgress()
                if (checkCredentials(login, password)) {
                    view?.setError("ошибка ввода данных")
                } else {
                    view?.setSuccess()
                    currentResult = true
                }
            }
        }.start()
    }

    override fun onCredentialsChanged() {
       // в перспективе
    }

    fun checkCredentials(login: String, password: String): Boolean {
        return login != password
    }
}