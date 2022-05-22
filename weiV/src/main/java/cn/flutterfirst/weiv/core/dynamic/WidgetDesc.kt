package cn.flutterfirst.weiv.core.dynamic

data class WidgetDesc(
    val key: String?,
    val name: String,
    val layoutParam: Map<String, Any?>,
    val param: Map<String, Any?>,
    val children: ArrayList<WidgetDesc>,
    var extra: Any?
)