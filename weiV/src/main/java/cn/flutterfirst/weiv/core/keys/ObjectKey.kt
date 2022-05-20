package cn.flutterfirst.weiv.core.keys

open class ObjectKey<T>(var value: T?) : Key() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ObjectKey<*>
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }
}