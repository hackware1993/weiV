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

    fun copy(): EdgeInsets {
        val edgeInsets = EdgeInsets()
        edgeInsets.left = left
        edgeInsets.top = top
        edgeInsets.right = right
        edgeInsets.bottom = bottom
        return edgeInsets
    }

    fun add(insets: EdgeInsets): EdgeInsets {
        val edgeInsets = EdgeInsets()
        edgeInsets.left = left + insets.left
        edgeInsets.top = top + insets.top
        edgeInsets.right = right + insets.right
        edgeInsets.bottom = bottom + insets.bottom
        return edgeInsets
    }

    fun addLeft(value: Int): EdgeInsets {
        return copy().apply {
            left += value
        }
    }

    fun addTop(value: Int): EdgeInsets {
        return copy().apply {
            top += value
        }
    }

    fun addRight(value: Int): EdgeInsets {
        return copy().apply {
            right += value
        }
    }

    fun addBottom(value: Int): EdgeInsets {
        return copy().apply {
            bottom += value
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as EdgeInsets
        if (left != other.left) return false
        if (top != other.top) return false
        if (right != other.right) return false
        if (bottom != other.bottom) return false
        return true
    }

    override fun hashCode(): Int {
        var result = left
        result = 31 * result + top
        result = 31 * result + right
        result = 31 * result + bottom
        return result
    }
}