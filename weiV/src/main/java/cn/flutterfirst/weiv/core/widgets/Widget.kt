package cn.flutterfirst.weiv.core.widgets

import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.LayoutParam

abstract class Widget<T : Widget<T>>(
    var key: Key? = null,
    var layoutParam: LayoutParam<*>? = null,
    var childWidgets: ArrayList<Widget<*>>? = null,
    var extra: Any? = null
) {
    abstract fun createElement(): Element

    @JavaOnly
    fun wKey(key: Key?): T {
        this.key = key
        return this as T
    }

    @JavaOnly
    fun wLayoutParam(layoutParam: LayoutParam<*>?): T {
        this.layoutParam = layoutParam
        return this as T
    }

    @JavaOnly
    fun wExtra(extra: Any?): T {
        this.extra = extra
        return this as T
    }

    companion object {
        fun canUpdate(oldWidget: Widget<*>, newWidget: Widget<*>): Boolean {
            return oldWidget.javaClass == newWidget.javaClass && oldWidget.key == newWidget.key
        }
    }
}