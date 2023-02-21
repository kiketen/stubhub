package com.technicaltest.stubhub.core.extensions

import android.view.View

private const val VISIBILITY_ANIMATION_DURATION = 350L

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.switchVisibility(visible: Boolean) {
    this.animate().cancel()
    when {
        visible -> visible()
        !visible -> gone()
    }
}