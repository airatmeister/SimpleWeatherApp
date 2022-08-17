package com.weatherapp.core.plugins.viewbinding

import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.weatherapp.core.presentation.base.BaseFragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingProperty<F : BaseFragment, T : ViewBinding>(
    private val viewBinder: (F) -> T,
    private val bindingPlugin: ViewBindingPlugin
) : ReadOnlyProperty<F, T> {

    private var viewBinding: T? = null

    @MainThread
    override fun getValue(thisRef: F, property: KProperty<*>): T {
        viewBinding?.let { return it }

        val viewBinding = viewBinder(thisRef)
        bindingPlugin.addClearListener(object : ViewBindingPlugin.ClearBinding {
            override fun clear() {
                this@FragmentViewBindingProperty.viewBinding = null
            }
        })
        this.viewBinding = viewBinding
        return viewBinding
    }
}

fun <F : BaseFragment, T : ViewBinding> BaseFragment.viewBinding(viewBinder: (F) -> T): FragmentViewBindingProperty<F, T> {
    return FragmentViewBindingProperty(
        viewBinder = viewBinder,
        bindingPlugin = this.bindingPlugin
    )
}

inline fun <F : BaseFragment, T : ViewBinding> BaseFragment.viewBinding(
    crossinline vbFactory: (View) -> T,
    crossinline viewProvider: (F) -> View = Fragment::requireView
): FragmentViewBindingProperty<F, T> {
    return viewBinding { fragment: F -> vbFactory(viewProvider(fragment)) }
}