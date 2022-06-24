package cn.flutterfirst.weiv.wrappers.constraintlayout

class ConstraintAlign(id: ConstraintId?, type: ConstraintAlignType?) {
    var id: ConstraintId? = null
    var type: ConstraintAlignType? = null
    var margin: Int? = null
    var goneMargin: Int? = null
    var bias: Double? = null

    init {
        this.id = id
        this.type = type
    }

    fun margin(margin: Int): ConstraintAlign {
        return if (this.margin != null || this.goneMargin != null || this.bias != null) {
            this.margin = margin
            this
        } else {
            ConstraintAlign(id, type).apply {
                this.margin = margin
            }
        }
    }

    fun goneMargin(goneMargin: Int): ConstraintAlign {
        return if (this.margin != null || this.goneMargin != null || this.bias != null) {
            this.margin = goneMargin
            this
        } else {
            ConstraintAlign(id, type).apply {
                this.goneMargin = goneMargin
            }
        }
    }

    fun bias(bias: Double): ConstraintAlign {
        return if (this.margin != null || this.goneMargin != null || this.bias != null) {
            this.bias = bias
            this
        } else {
            ConstraintAlign(id, type).apply {
                this.bias = bias
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConstraintAlign) return false
        if (id != other.id) return false
        if (type != other.type) return false
        if (bias != other.bias) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (bias?.hashCode() ?: 0)
        return result
    }
}