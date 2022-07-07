package cn.flutterfirst.weiv.wrappers.constraintlayout

class RelativeConstraintId(var siblingIndexOffset: Int) : ConstraintId("$siblingIndexOffset") {
    override fun copy(): ConstraintId {
        return RelativeConstraintId(siblingIndexOffset)
    }
}