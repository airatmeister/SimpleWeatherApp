package com.weatherapp.core.plugins

interface BasePlugin {
    fun onLifecycleEvent(event: LifecycleEvent)
}