package com.gb.applicationarchitecture

import android.app.Application
import android.content.Context
import com.gb.applicationarchitecture.data.MockLoginApiImplementation
import com.gb.applicationarchitecture.domain.LoginApi

class App : Application() {
    val api: LoginApi by lazy {  MockLoginApiImplementation() }
}

val Context.app :App
    get() {
        return applicationContext as App
    }