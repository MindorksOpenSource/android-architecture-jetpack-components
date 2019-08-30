package com.mindorks.framework.jetpack.ui.splash

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindorks.framework.jetpack.data.AppDataManager

/**
 * Created by jyotidubey on 2019-02-27.
 */
class SplashViewModel(context: Context?) : ViewModel() {
    private val dataManager = AppDataManager.getInstance(context)

    init {
        dataManager.seedQuestions()
            .flatMap { dataManager.seedOptions() }
            .subscribe({
            }, { })
    }


    val isUserLoggedIn = MutableLiveData<Boolean>()

    init {
        isUserLoggedIn.value = true
    }

}