package com.mindorks.framework.jetpack.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.databinding.ActivityHomeBinding



/**
 * Created by jyotidubey on 2019-02-27.
 */
class HomeActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding =  DataBindingUtil.setContentView(this,
            R.layout.activity_home)
        setSupportActionBar(binding.toolbar)

        drawerLayout = binding.drawerLayout

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)

        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(navigationView, navController)

        binding.navigationView.setNavigationItemSelectedListener(this)
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment),drawerLayout);
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
        drawerLayout.closeDrawers()

        val id = menuItem.itemId
        when (id) {

            R.id.logout -> {
                navController.navigate(R.id.loginFragment)
            }
            R.id.feedFragment->{
                navController.navigate(R.id.feedFragment)
            }
            R.id.aboutFragment->{
                navController.navigate(R.id.aboutFragment)
            }
        }
        return true
    }

}