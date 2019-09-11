package com.github.titaniper.vehicles.view.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.github.titaniper.vehicles.Constants
import com.github.titaniper.vehicles.R

import com.github.titaniper.vehicles.base.BaseActivity
import com.github.titaniper.vehicles.databinding.LoginActivityBinding
import com.github.titaniper.vehicles.view.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import pyxis.uzuki.live.richutilskt.utils.RPreference

class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.login_activity
    override val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.viewModel = this.viewModel
        viewDataBinding.lifecycleOwner = this
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        if (RPreference.getInstance(this).getBoolean(Constants.AUTO_LOGIN, false)) {
            startMainActivity(RPreference.getInstance(this).getString(Constants.TOKEN_KEY, ""))
        }
    }

    private fun setUpObservers() {
        viewModel.loginButtonClickedEvent.observe(this, Observer {
            RPreference.getInstance(this).put(Constants.TOKEN_KEY, it)
            RPreference.getInstance(this).put(Constants.AUTO_LOGIN, viewModel.autoLoginLiveData.value ?: false)
            startMainActivity(it)
        })
    }

    private fun startMainActivity(token: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.TOKEN_KEY, token)
        startActivity(intent)
    }
}
