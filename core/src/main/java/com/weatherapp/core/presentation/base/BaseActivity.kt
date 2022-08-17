package com.weatherapp.core.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.weatherapp.core.plugins.BasePlugin
import com.weatherapp.core.plugins.LifecycleEvent
import com.weatherapp.core.presentation.Navigator
import com.weatherapp.core.presentation.UiAction
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity, Navigator {

    constructor() : super()
    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    private val plugins = mutableListOf<BasePlugin>()

    protected lateinit var navigator: NavController
    abstract override fun getNavController(): NavController
    abstract override fun navigateTo(action: UiAction)

    fun navigateTo(action: String, bundle: Bundle? = null){
        navigateTo(UiAction(action, bundle))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initPlugins()
        dispatchEventToPlugins(LifecycleEvent.BeforeOnCreate(savedInstanceState))
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        dispatchEventToPlugins(LifecycleEvent.OnCreate(savedInstanceState))
        onBindViewModel()
    }

    abstract fun onBindViewModel()

    abstract fun onSetupLayout()

    // region Plugins
    @CallSuper
    protected open fun initPlugins() {
        // TODO Если что то надо то тут надо добавить плагин
    }

    protected fun addPlugin(plugin: BasePlugin) {
        plugins.add(plugin)
    }

    private fun dispatchEventToPlugins(event: LifecycleEvent) {
        plugins.forEach { it.onLifecycleEvent(event) }
    }

    private fun releasePlugins() {
        plugins.clear()
    }
    // endregion

    override fun onResume() {
        dispatchEventToPlugins(LifecycleEvent.OnResume)
        super.onResume()
    }

    override fun onPause() {
        dispatchEventToPlugins(LifecycleEvent.OnPause)
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        dispatchEventToPlugins(LifecycleEvent.OnStart)
        navigator = getNavController()
        onSetupLayout()
    }

    override fun onDestroy() {
        dispatchEventToPlugins(LifecycleEvent.OnDestroy)
        releasePlugins()
        super.onDestroy()
    }
}