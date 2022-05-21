package cn.flutterfirst.weiv.core.others

interface IParamChangedCallback<VIEW, PARAM> {
    fun onParamChanged(view: VIEW, param: PARAM?, first: Boolean)
}