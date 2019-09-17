package com.github.titaniper.vehicles.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import timber.log.Timber

abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    lateinit var viewDataBinding: T
    abstract val layoutResourceId: Int
    abstract val viewModel: VM
    private var isSetBackButtonValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }
}