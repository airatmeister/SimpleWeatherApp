package com.weatherapp.core.plugins

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

sealed class LifecycleEvent {

    data class BeforeOnCreate(val savedInstanceState: Bundle?) : LifecycleEvent()
    data class OnCreate(val savedInstanceState: Bundle?) : LifecycleEvent()
    data class OnViewCreated(val view: View, val savedInstanceState: Bundle?) : LifecycleEvent()
    data class OnDestroyView(val fragment: Fragment) : LifecycleEvent()
    object OnResume : LifecycleEvent()
    object OnPause : LifecycleEvent()
    object OnStart : LifecycleEvent()
    data class OnStop(val fragment: Fragment) : LifecycleEvent()
    object OnDestroy : LifecycleEvent()
    data class OnSaveInstanceState(val outState: Bundle) : LifecycleEvent()
    class OnActivityResult(
        val requestCode: Int,
        val resultCode: Int,
        val data: Intent?
    ) : LifecycleEvent()
}