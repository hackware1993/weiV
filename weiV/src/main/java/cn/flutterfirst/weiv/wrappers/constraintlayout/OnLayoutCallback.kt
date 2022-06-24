package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.graphics.Rect
import android.view.View

interface OnLayoutCallback {
    fun onLayout(view: View, rect: Rect)
}