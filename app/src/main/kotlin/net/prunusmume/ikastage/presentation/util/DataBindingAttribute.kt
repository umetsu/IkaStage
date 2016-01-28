package net.prunusmume.ikastage.presentation.util

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.bumptech.glide.Glide

/**
 * Created by umetsu_kentaro on 16/01/28.
 */
object DataBindingAttribute {

    @BindingAdapter("bind:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }
}
