package cn.flutterfirst.weiv.wrappers.constraintlayout

open class ConstraintId(var id: String) {
    var leftMargin: Int? = null
    var leftGoneMargin: Int? = null
    var topMargin: Int? = null
    var topGoneMargin: Int? = null
    var rightMargin: Int? = null
    var rightGoneMargin: Int? = null
    var bottomMargin: Int? = null
    var bottomGoneMargin: Int? = null

    val left by lazy {
        ConstraintAlign(this, ConstraintAlignType.left).apply {
            margin = leftMargin
            goneMargin = leftGoneMargin
        }
    }

    val top by lazy {
        ConstraintAlign(this, ConstraintAlignType.top).apply {
            margin = topMargin
            goneMargin = topGoneMargin
        }
    }

    val right by lazy {
        ConstraintAlign(this, ConstraintAlignType.right).apply {
            margin = rightMargin
            goneMargin = rightGoneMargin
        }
    }

    val bottom by lazy {
        ConstraintAlign(this, ConstraintAlignType.bottom).apply {
            margin = bottomMargin
            goneMargin = bottomGoneMargin
        }
    }

    val baseline by lazy {
        ConstraintAlign(this, ConstraintAlignType.baseline).apply {
            margin = bottomMargin
            goneMargin = bottomGoneMargin
        }
    }

    val leftReverse by lazy {
        ConstraintAlign(this, ConstraintAlignType.left).apply {
            margin = rightMargin
            goneMargin = rightGoneMargin
        }
    }

    val topReverse by lazy {
        ConstraintAlign(this, ConstraintAlignType.top).apply {
            margin = bottomMargin
            goneMargin = bottomGoneMargin
        }
    }

    val rightReverse by lazy {
        ConstraintAlign(this, ConstraintAlignType.right).apply {
            margin = leftMargin
            goneMargin = leftGoneMargin
        }
    }

    val bottomReverse by lazy {
        ConstraintAlign(this, ConstraintAlignType.bottom).apply {
            margin = topMargin
            goneMargin = topGoneMargin
        }
    }

    val center by lazy {
        ConstraintAlign(this, ConstraintAlignType.center)
    }

    protected fun copy(): ConstraintId {
        return ConstraintId(id)
    }

    open fun isMarginSet(): Boolean {
        return leftMargin != null || topMargin != null || rightMargin != null || bottomMargin != null || leftGoneMargin != null || topGoneMargin != null || rightGoneMargin != null || bottomGoneMargin != null
    }

    fun leftMargin(margin: Int): ConstraintId {
        return if (isMarginSet()) {
            leftMargin = margin
            this
        } else {
            copy().apply {
                leftMargin = margin
            }
        }
    }

    fun topMargin(margin: Int): ConstraintId {
        return if (isMarginSet()) {
            topMargin = margin
            this
        } else {
            copy().apply {
                topMargin = margin
            }
        }
    }

    fun rightMargin(margin: Int): ConstraintId {
        return if (isMarginSet()) {
            rightMargin = margin
            this
        } else {
            copy().apply {
                rightMargin = margin
            }
        }
    }

    fun bottomMargin(margin: Int): ConstraintId {
        return if (isMarginSet()) {
            bottomMargin = margin
            this
        } else {
            copy().apply {
                bottomMargin = margin
            }
        }
    }

    fun leftGoneMargin(margin: Int): ConstraintId {
        return if (isMarginSet()) {
            leftGoneMargin = margin
            this
        } else {
            copy().apply {
                leftGoneMargin = margin
            }
        }
    }

    fun topGoneMargin(margin: Int): ConstraintId {
        return if (isMarginSet()) {
            topGoneMargin = margin
            this
        } else {
            copy().apply {
                topGoneMargin = margin
            }
        }
    }

    fun rightGoneMargin(margin: Int): ConstraintId {
        return if (isMarginSet()) {
            rightGoneMargin = margin
            this
        } else {
            copy().apply {
                rightGoneMargin = margin
            }
        }
    }

    fun bottomGoneMargin(margin: Int): ConstraintId {
        return if (isMarginSet()) {
            bottomGoneMargin = margin
            this
        } else {
            copy().apply {
                bottomGoneMargin = margin
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ConstraintId
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "ConstraintId(id='$id')"
    }
}