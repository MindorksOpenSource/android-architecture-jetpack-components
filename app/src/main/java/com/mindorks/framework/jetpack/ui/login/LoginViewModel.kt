package com.mindorks.framework.jetpack.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by jyotidubey on 2019-02-27.
 */
class LoginViewModel : ViewModel(){

    val progress = MutableLiveData<Boolean>()
    val loginSuccess = MutableLiveData<Boolean>()

    init {
        progress.value = false
    }
    fun onServerLoginClick(){
        progress.value = true
        loginSuccess.value = true
    }

    fun onGoogleLoginClick(){
    }


    fun onFbLoginClick(){
    }
}