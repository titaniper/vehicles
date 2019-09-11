package com.github.titaniper.vehicles.view.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.titaniper.vehicles.Constants
import com.github.titaniper.vehicles.R
import com.github.titaniper.vehicles.base.BaseActivity
import com.github.titaniper.vehicles.model.VehicleInfo
import com.github.titaniper.vehicles.databinding.MainActivityBinding
import com.github.titaniper.vehicles.view.adapter.VehicleItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import pyxis.uzuki.live.richutilskt.utils.RPreference

class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.main_activity
    override val viewModel: MainViewModel by viewModel()

    private val token : String by lazy { intent.getStringExtra(Constants.TOKEN_KEY) ?: "" }

    private val vehicleItemAdapter: VehicleItemAdapter =
        VehicleItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.viewModel = this.viewModel
        viewDataBinding.lifecycleOwner = this

        setUpView()
        setUpObservers()
        viewModel.init(token)
        viewModel.getVehicleSearch("")
    }

    private fun setUpView() {
        viewDataBinding.mainRecyclerView.adapter = vehicleItemAdapter
        viewDataBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        vehicleItemAdapter.setOnChangedFavoriteListener(object: VehicleItemAdapter.OnChangedFavoriteListener {
            override fun onChange(position: Int, vecicleInfo: VehicleInfo, newState: Boolean) {
                viewModel.onClickFavorite(token, vecicleInfo.vehicleIdx.toString(), newState)
            }
        })
    }

    private fun setUpObservers() {
        viewModel.vehicleSearchResponseLiveData.observe(this, Observer {
            it.data?.let {
                vehicleItemAdapter.addVehicles(it)
                vehicleItemAdapter.notifyDataSetChanged()
            }
        })

        viewModel.invalidTokenEvent.observe(this, Observer {
            // Invalid token?
            if (it == true) {
                // Logout
                RPreference.getInstance(this).put(Constants.TOKEN_KEY, "")
                RPreference.getInstance(this).put(Constants.AUTO_LOGIN, false)
                finish()
            }
        })
    }
}