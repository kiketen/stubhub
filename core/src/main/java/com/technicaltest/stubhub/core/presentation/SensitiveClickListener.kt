package com.technicaltest.stubhub.core.presentation

import android.os.SystemClock
import android.view.View

class SensitiveClickListener(
    private var threshold: Int,
    private val onCLick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < threshold) {
            return
        }

        lastTimeClicked = SystemClock.elapsedRealtime()
        onCLick(view)
    }
}

fun View.setSensitiveClickListener(threshold: Int = 500, onClick: (View) -> Unit) {
    this.setOnClickListener(
        SensitiveClickListener(threshold) { onClick(it) }
    )
}