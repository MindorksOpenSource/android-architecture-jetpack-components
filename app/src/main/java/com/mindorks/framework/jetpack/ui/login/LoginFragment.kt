package com.mindorks.framework.jetpack.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.databinding.FragmentLoginBinding
import com.mindorks.framework.jetpack.ui.splash.SplashFragmentDirections.Companion.showHomeScreen
import com.mindorks.framework.jetpack.ui.splash.SplashFragmentDirections.Companion.showLoginScreen

/**
 * Created by jyotidubey on 2019-02-27.
 */
class LoginFragment: Fragment(){
    private lateinit var viewModel : LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = this.viewModel
        viewModel.loginSuccess.observe(this, Observer { isLoginSuccess ->
            if(isLoginSuccess){
                view?.let {
                    findNavController(it).navigate(R.id.show_home_screen)
                }
            }else{
                view?.let {
                    findNavController(it).navigate(R.id.show_login_screen)
                }
            }


        })


    }

}