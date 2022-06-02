package cn.flutterfirst.weiv.core.others

import android.view.View

open class LayoutParam<P : LayoutParam<P>>(
    var width: Int = wrapContent,
    var height: Int = wrapContent,
    var leftMargin: Int = 0,
    var topMargin: Int = 0,
    var rightMargin: Int = 0,
    var bottomMargin: Int = 0,
) {

    companion object {
        const val matchParent = -1
        const val wrapContent = -2
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

    @JavaOnly
    fun wLeftMargin(leftMargin: Int): P {
        this.leftMargin = leftMargin
        return this as P
    }

    @JavaOnly
    fun wTopMargin(topMargin: Int): P {
        this.topMargin = topMargin
        return this as P
    }

    @JavaOnly
    fun wRightMargin(rightMargin: Int): P {
        this.rightMargin = rightMargin
        return this as P
    }

    @JavaOnly
    fun wBottomMargin(bottomMargin: Int): P {
        this.bottomMargin = bottomMargin
        return this as P
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LayoutParam<*>) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (leftMargin != other.leftMargin) return false
        if (topMargin != other.topMargin) return false
        if (rightMargin != other.rightMargin) return false
        if (bottomMargin != other.bottomMargin) return false
        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + leftMargin
        result = 31 * result + topMargin
        result = 31 * result + rightMargin
        result = 31 * result + bottomMargin
        return result
    }
}

fun <P : LayoutParam<P>> View.getLayoutParam(): P? {
    return getTag(0x7f010000) as P?
}

fun <P : LayoutParam<P>> View.setLayoutParam(p: P?) {
    setTag(0x7f010000, p)
}