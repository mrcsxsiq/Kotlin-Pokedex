package dev.marcosfarias.pokedex.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Generation(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val image: Int
) {
    override fun toString() = "$id$title$image"
}
