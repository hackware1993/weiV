package cn.flutterfirst.weiv.core.others

import android.view.View

open class LayoutParam<P : LayoutParam<P>>(
    var width: Int = WRAP_CONTENT,
    var height: Int = WRAP_CONTENT
) {

    companion object {
        const val MATCH_PARENT = -1
        const val WRAP_CONTENT = -2
    }

    @JavaOnly
    fun wWidth(width: Int): P {
        this.width = width
        return this as P
    }

    @JavaOnly
    fun wHeight(height: Int): P {
        this.height = height
        return this as P
    }
}

fun <P : LayoutParam<P>> View.getLayoutParam(): P {
    return getTag(0x7f010000) as P
}

fun <P : LayoutParam<P>> View.setLayoutParam(p: P) {
    setTag(0x7f010000, p)
}