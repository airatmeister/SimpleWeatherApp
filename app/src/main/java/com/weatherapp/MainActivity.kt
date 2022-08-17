package com.weatherapp

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.weatherapp.core.plugins.viewbinding.viewBinding
import com.weatherapp.core.presentation.UiAction
import com.weatherapp.core.presentation.base.BaseActivity
import com.weatherapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun getNavController() = findNavController(R.id.nav_host_fragment_activity_main)

    override fun navigateTo(action: UiAction) {
        when (action.id){
            "weather_feature" -> navigator.navigate(R.id.navigation_weather)
            "favorite_feature" -> navigator.navigate(R.id.navigation_favorite)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onBindViewModel() = Unit

    override fun onSetupLayout() {
        setupActionBarWithNavController(
            navigator,
            AppBarConfiguration(
                setOf(
                    R.id.navigation_weather,
                    R.id.navigation_favorite
                )
            )
        )
        binding.navView.setupWithNavController(navigator)
    }
}