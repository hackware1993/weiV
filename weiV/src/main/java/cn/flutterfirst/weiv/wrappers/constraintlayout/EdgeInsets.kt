package cn.flutterfirst.weiv.wrappers.constraintlayout

class EdgeInsets {
    var left: Int = 0
    var top: Int = 0
    var right: Int = 0
    var bottom: Int = 0

    companion object {
        val zero = EdgeInsets()

        fun all(value: Int): EdgeInsets {
            return EdgeInsets().apply {
                left = value
                top = value
                right = value
                bottom = value
            }
        }

        fun symmetric(horizontalValue: Int = 0, verticalValue: Int = 0): EdgeInsets {
            return EdgeInsets().apply {
                left = horizontalValue
                top = verticalValue
                right = horizontalValue
                bottom = verticalValue
            }
        }

        fun only(
            leftValue: Int = 0,
            topValue: Int = 0,
            rightValue: Int = 0,
            bottomValue: Int = 0,
        ): EdgeInsets {
            return EdgeInsets().apply {
                left = leftValue
                top = topValue
                right = rightValue
                bottom = bottomValue
            }
        }
    }

    fun add(insets: EdgeInsets) {
        left += insets.left
        top += insets.top
        right += insets.right
        bottom += insets.bottom
    }

    fun addLeft(value: Int) {
        left += value
    }

    fun addTop(value: Int) {
        top += value
    }

    fun addRight(value: Int) {
        right += value
    }

    fun addBottom(value: Int) {
        bottom += value
    }
}