package cn.flutterfirst.weiv.core.widgets

import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.LayoutParam

abstract class Widget<W : Widget<W>>(
    var key: Key? = null,
    var layoutParam: LayoutParam<*>? = null,
    var childWidgets: ArrayList<Widget<*>>? = null,
    var extra: Any? = null
) {
    abstract fun createElement(): Element

    @JavaOnly
    fun wKey(key: Key?): W {
        this.key = key
        return this as W
    }

    @JavaOnly
    fun wLayoutParam(layoutParam: LayoutParam<*>?): W {
        this.layoutParam = layoutParam
        return this as W
    }

    @JavaOnly
    fun wExtra(extra: Any?): W {
        this.extra = extra
        return this as W
    }

    companion object {
        fun canUpdate(oldWidget: Widget<*>, newWidget: Widget<*>): Boolean {
            return oldWidget.javaClass == newWidget.javaClass && oldWidget.key == newWidget.key
        }
    }
}