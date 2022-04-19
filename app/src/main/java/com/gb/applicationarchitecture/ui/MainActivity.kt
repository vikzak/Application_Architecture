package com.gb.applicationarchitecture.ui

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.isVisible
import com.gb.applicationarchitecture.App
import com.gb.applicationarchitecture.app
import com.gb.applicationarchitecture.databinding.ActivityMainBinding
import com.gb.applicationarchitecture.ui.login.LoginContract
import com.gb.applicationarchitecture.ui.login.LoginPresenter

class MainActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: LoginContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = restorePresenter()
        presenter?.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun restorePresenter():  LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter(app.api)
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    @MainThread
    override fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.registrationButton.isVisible = false
        binding.forgotButton.isVisible = false
        binding.loginEditText.isVisible = false
        binding.passwordEditText.isVisible = false
        binding.root.setBackgroundColor(Color.MAGENTA)
    }

    @MainThread
    override fun showProgress() {
        binding.loginButton.isEnabled = false
        binding.registrationButton.isEnabled = false
        binding.forgotButton.isEnabled = false
        binding.loginEditText.isEnabled = false
        binding.passwordEditText.isEnabled = false
        binding.progressCircular.isVisible = true
        hideKeyboard(this)
    }

    @MainThread
    override fun hideProgress() {
        binding.loginButton.isEnabled = true
        binding.registrationButton.isEnabled = true
        binding.forgotButton.isEnabled = true
        binding.loginEditText.isEnabled = true
        binding.passwordEditText.isEnabled = true
        binding.progressCircular.isVisible = false
        //showKeyboard(this)
    }

    @MainThread
    override fun setError(error: String) {
        Toast.makeText(this, "APP ERROR: $error", Toast.LENGTH_SHORT).show()
    }


    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view: View? = activity.currentFocus
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun showKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

}