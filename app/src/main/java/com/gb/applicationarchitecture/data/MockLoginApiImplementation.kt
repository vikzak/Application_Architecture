package com.gb.applicationarchitecture.data

import com.gb.applicationarchitecture.domain.LoginApi

class MockLoginApiImplementation: LoginApi {
    override fun login(login: String, password: String): Boolean {
        Thread.sleep(3000)
        return login == password
    }

    override fun register(login: String, password: String, email: String): Boolean {
        Thread.sleep(3000)
        return login.isNotEmpty()
    }

    override fun logout(): Boolean {
        Thread.sleep(3000)
        return true
    }

    override fun forgotPassword(login: String): Boolean {
        Thread.sleep(2000)
        return false
    }
}