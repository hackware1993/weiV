package cn.flutterfirst.weiv.core.extension

import cn.flutterfirst.weiv.core.widgets.Widget
import org.json.JSONObject

interface ISerializableWidget<T : Widget<T>> {
    fun fromJson(jsonObj: JSONObject, param: Map<String, Any?>): T

    fun parseChildLayoutParam(childWidget: Widget<*>, layoutParam: Map<String, Any?>) {
    }
}