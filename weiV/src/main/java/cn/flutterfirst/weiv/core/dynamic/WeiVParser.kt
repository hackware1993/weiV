package cn.flutterfirst.weiv.core.dynamic

import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.ISerializableWidget
import cn.flutterfirst.weiv.core.keys.StringKey
import cn.flutterfirst.weiv.core.widgets.Widget
import org.json.JSONArray
import org.json.JSONObject

object WeiVParser {

    @JvmStatic
    fun parse(json: String): Widget<*>? {
        val jsonObj = JSONObject(json)
        return parseObj(jsonObj)
    }

    @JvmStatic
    fun parseObj(jsonObj: JSONObject?): Widget<*>? {
        if (jsonObj == null) {
            return null
        }

        val name = jsonObj.optString("name")
        if (name.isNotEmpty()) {
            val widget = ExtensionMgr.createWidget(name)
            if (widget is ISerializableWidget<*>) {
                widget.extra = jsonObj

                var stringKey: StringKey? = null
                val keyString: String? = jsonObj.optString("key", null)
                if (keyString != null) {
                    stringKey = StringKey(keyString)
                }
                widget.key = stringKey
                parseMap(jsonObj, "layoutParam")

                val childrenWidgets: ArrayList<Widget<*>>?
                val children = jsonObj.optJSONArray("children")
                childrenWidgets = if (children != null) {
                    parseArr(children)
                } else {
                    null
                }
                childrenWidgets?.forEach {
                    widget.parseChildLayoutParam(
                        it,
                        parseMap(
                            it.extra as JSONObject?,
                            "layoutParam"
                        )
                    )
                }

                widget.childWidgets = childrenWidgets
                widget.fromJson(
                    jsonObj,
                    parseMap(jsonObj, "param")
                )
            }
            return widget
        }

        return null
    }

    @JvmStatic
    fun parseArr(jsonArr: JSONArray?): ArrayList<Widget<*>> {
        if (jsonArr == null) {
            return ArrayList()
        }
        val widgets = arrayListOf<Widget<*>>()
        for (i in 0 until jsonArr.length()) {
            val widget = parseObj(jsonArr.optJSONObject(i))
            if (widget != null) {
                widgets.add(widget)
            }
        }
        return widgets
    }

    @JvmStatic
    fun parseMap(jsonObj: JSONObject?, mapKey: String): Map<String, Any?> {
        if (jsonObj == null) {
            return HashMap()
        }
        val config = jsonObj.optJSONObject(mapKey)
        val configMap = HashMap<String, Any?>()
        if (config != null) {
            for (key in config.keys()) {
                configMap[key] = config.opt(key)
            }
        }
        return configMap
    }
}