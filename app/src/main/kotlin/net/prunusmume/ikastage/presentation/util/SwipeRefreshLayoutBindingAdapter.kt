package net.prunusmume.ikastage.presentation.util

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.support.v4.widget.SwipeRefreshLayout

/**
 * Created by umetsu_kentaro on 16/01/29.
 */
@BindingMethods(
        BindingMethod(type = SwipeRefreshLayout::class, attribute = "bind:onRefresh", method = "setOnRefreshListener")
)
object SwipeRefreshLayoutBindingAdapter {
}
