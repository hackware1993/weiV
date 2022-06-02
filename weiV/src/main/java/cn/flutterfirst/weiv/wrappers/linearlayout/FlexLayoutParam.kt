package cn.flutterfirst.weiv.wrappers.linearlayout

import cn.flutterfirst.weiv.core.others.LayoutParam

class FlexLayoutParam(
    width: Int = wrapContent,
    height: Int = wrapContent,
    leftMargin: Int = 0,
    topMargin: Int = 0,
    rightMargin: Int = 0,
    bottomMargin: Int = 0,
    var gravity: Int = -1,
    var weight: Float = 0f,
) : LayoutParam<FlexLayoutParam>(width, height, leftMargin, topMargin, rightMargin, bottomMargin) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FlexLayoutParam) return false
        if (!super.equals(other)) return false
        if (gravity != other.gravity) return false
        if (weight != other.weight) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + gravity
        result = 31 * result + weight.hashCode()
        return result
    }
}