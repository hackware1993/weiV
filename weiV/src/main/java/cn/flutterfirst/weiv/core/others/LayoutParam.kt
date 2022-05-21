package cn.flutterfirst.weiv.core.others

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