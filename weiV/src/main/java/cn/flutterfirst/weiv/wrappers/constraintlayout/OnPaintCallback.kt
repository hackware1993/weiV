package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.graphics.Point
import android.graphics.Rect
import android.view.View

interface OnPaintCallback {
    fun onPaint(view: View, viewPort: Rect, anchorPoint: Point?, angle: Double?)
}