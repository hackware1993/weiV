package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.graphics.Point

class CL {
    companion object {
        val zeroPoint = Point()
        val parent = ConstraintId("parent")
        val visible = CLVisibility.visible
        val invisible = CLVisibility.invisible
        val gone = CLVisibility.gone
    }
}