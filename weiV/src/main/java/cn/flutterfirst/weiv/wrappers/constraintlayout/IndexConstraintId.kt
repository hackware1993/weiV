package cn.flutterfirst.weiv.wrappers.constraintlayout

class IndexConstraintId(var siblingIndex: Int) : ConstraintId("parent.children[$siblingIndex]") {
    override fun copy(): ConstraintId {
        return IndexConstraintId(siblingIndex)
    }
}