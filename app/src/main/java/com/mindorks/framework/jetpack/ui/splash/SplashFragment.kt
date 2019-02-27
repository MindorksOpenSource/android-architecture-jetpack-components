package com.mindorks.framework.jetpack.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.ui.splash.SplashFragmentDirections.Companion.showHomeScreen
import com.mindorks.framework.jetpack.ui.splash.SplashFragmentDirections.Companion.showLoginScreen

/**
 * Created by jyotidubey on 2019-02-27.
 */
class SplashFragment : Fragment(){
    private lateinit var viewModel : SplashViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        viewModel.isUserLoggedIn.observe(this, Observer { isUserLoggedIn ->
            var navDirections = showLoginScreen()
            if(isUserLoggedIn){
                navDirections = showHomeScreen()
            }
            view?.let {
                findNavController(it).navigate(navDirections)
            }
        })

    }
}