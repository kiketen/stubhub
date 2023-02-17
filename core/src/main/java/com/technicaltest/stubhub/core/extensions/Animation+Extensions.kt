package com.technicaltest.stubhub.core.extensions

import android.animation.Animator
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator

fun View.fadeIn(duration: Long) {
    if (!isVisible()) {
        alpha = 0f
        visible()
    }
    animate()
            .alpha(1f)
            .setDuration(duration)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
}

fun View.fadeOut(duration: Long) {
    animate()
            .alpha(0f)
            .setDuration(duration)
            .apply {
                setEndListener {
                    gone()
                }
            }
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
}


fun ViewPropertyAnimator.setEndListener(block: () -> Unit): ViewPropertyAnimator {
    this.setListener(object: Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator) {

        }

        override fun onAnimationCancel(animation: Animator) {

        }

        override fun onAnimationStart(animation: Animator) {

        }

        override fun onAnimationEnd(animation: Animator) {
            block()
            this@setEndListener.setListener(null)
        }
    })
    return this
}
