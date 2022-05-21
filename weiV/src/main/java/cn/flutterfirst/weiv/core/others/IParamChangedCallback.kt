package cn.flutterfirst.weiv.core.others

interface IParamChangedCallback<V, P> {
    fun onParamChanged(view: V, param: P?, first: Boolean)
}