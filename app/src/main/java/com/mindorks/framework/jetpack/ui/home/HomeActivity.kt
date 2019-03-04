package com.mindorks.framework.jetpack.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.databinding.ActivityHomeBinding

/**
 * Created by jyotidubey on 2019-02-27.
 */
class HomeActivity: AppCompatActivity(){

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding =  DataBindingUtil.setContentView(this,
            R.layout.activity_home)
        drawerLayout = binding.drawerLayout

        navController = Navigation.findNavController(this, R.id.drawer_nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Set up navigation menu
        binding.navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}