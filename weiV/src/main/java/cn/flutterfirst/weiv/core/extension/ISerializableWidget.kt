package cn.flutterfirst.weiv.core.extension

import cn.flutterfirst.weiv.core.widgets.Widget
import org.json.JSONObject

interface ISerializableWidget<W : Widget<W>> {
    fun fromJson(jsonObj: JSONObject, param: Map<String, Any?>): W

    fun parseChildLayoutParam(childWidget: Widget<*>, layoutParam: Map<String, Any?>) {
    }

    fun parseExtra(extraString: String?) {
    }
}