package com.weatherapp.core.presentation.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.weatherapp.core.plugins.viewbinding.inflate

@Suppress("LeakingThis")
abstract class BaseViewHolder<VB : ViewBinding>(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) : RecyclerView.ViewHolder(parent.inflate(layoutId)) {
    protected val itemBinding: VB = createBinding(itemView)

    abstract fun createBinding(itemView: View): VB
}