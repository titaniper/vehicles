package com.github.titaniper.vehicles.view.main

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.titaniper.vehicles.base.BaseViewModel
import com.github.titaniper.vehicles.model.VehicleInfo
import com.github.titaniper.vehicles.repository.DataRepository
import com.github.titaniper.vehicles.model.response.Response
import com.github.titaniper.vehicles.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(private val repository: DataRepository) : BaseViewModel() {
    private val _vehicleSearchResponseLiveData = MutableLiveData<Response<List<VehicleInfo>>>()
    val vehicleSearchResponseLiveData: LiveData<Response<List<VehicleInfo>>>
        get() = _vehicleSearchResponseLiveData
    val searchLiveData = MutableLiveData<String>()
    var token = ""

    val invalidTokenEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun init(token: String) {
        this.token = token
    }

    fun getVehicleSearch(query: String) {
        if (TextUtils.isEmpty(token)) {
            invalidTokenEvent.value = true
            return
        }
        Timber.d("token $token")
        addDisposable(repository.getData(query, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (it != null) {
                            _vehicleSearchResponseLiveData.value = it
                        } else {
                            Timber.d("error")
                        }
                    }
                }, {
                    Timber.d("response error, message : ${it.message}")
                }))
    }

    fun onClickFavorite(token: String, id: String, status: Boolean) {
        addDisposable(repository.favorite(token, id, status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (error != null) {
                        Timber.d("error: $error")
                    } else {
                        Timber.d("data: $data")
                    }
                }
            }, {
                Timber.d("response error, message : ${it.message}")
            }))
    }

    fun onClickSearch() {
        getVehicleSearch(searchLiveData.value ?: "")
    }

    fun onClickLogout() {
        invalidTokenEvent.value = true
    }
}