package cn.flutterfirst.weiv.core.keys

open class ObjectKey<T>(var obj: T?) : Key() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ObjectKey<*>
        if (obj !== other.obj) return false
        return true
    }

    override fun hashCode(): Int {
        return obj?.hashCode() ?: 0
    }
}