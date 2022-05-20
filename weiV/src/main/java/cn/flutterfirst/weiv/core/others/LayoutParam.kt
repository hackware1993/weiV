package cn.flutterfirst.weiv.core.others

open class LayoutParam<T : LayoutParam<T>> @JvmOverloads constructor(
    var width: Int = WRAP_CONTENT,
    var height: Int = WRAP_CONTENT
) {

    companion object {
        const val MATCH_PARENT = -1
        const val WRAP_CONTENT = -2
    }

    @JavaOnly
    fun wWidth(width: Int): T {
        this.width = width
        return this as T
    }

    @JavaOnly
    fun wHeight(height: Int): T {
        this.height = height
        return this as T
    }
}