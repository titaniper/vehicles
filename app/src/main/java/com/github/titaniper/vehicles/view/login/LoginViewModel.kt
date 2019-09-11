package com.github.titaniper.vehicles.view.login

import com.github.titaniper.vehicles.base.BaseViewModel
import com.github.titaniper.vehicles.repository.DataRepository
import androidx.lifecycle.MutableLiveData
import com.github.titaniper.vehicles.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class LoginViewModel(private val repository: DataRepository) : BaseViewModel() {
    val loginButtonClickedEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val idLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val autoLoginLiveData = MutableLiveData<Boolean>()

    fun clickLogin() {
        val id = idLiveData.value ?: ""
        val pw = passwordLiveData.value ?: ""
        if (id.isEmpty() || pw.isEmpty()) {
            // TODO: Alert
            return
        }
        repository.auth(id, pw)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (data != null) {
                        loginButtonClickedEvent.value = data!!.token
                    } else {
                        Timber.d( "error: $error")
                    }
                }
            }, {
                Timber.d( "response error, message : ${it.message}")
            })
    }
}