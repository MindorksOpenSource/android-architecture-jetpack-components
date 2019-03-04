package com.mindorks.framework.jetpack.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by jyotidubey on 2019-02-27.
 */
class SplashViewModel : ViewModel(){
    val isUserLoggedIn = MutableLiveData<Boolean>()
    init {
        isUserLoggedIn.value = true
    }

}