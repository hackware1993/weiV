package cn.flutterfirst.weiv.core.others

interface ParamChangedCallback<VIEW, PARAM> {
    fun onParamChanged(view: VIEW, param: PARAM?, first: Boolean)
}