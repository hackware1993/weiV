package cn.flutterfirst.weiv.core.widgets

import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key

abstract class Widget(var key: Key? = null, var childWidgets: ArrayList<Widget>? = null) {
    abstract fun createElement(): Element

    companion object {
        fun canUpdate(oldWidget: Widget, newWidget: Widget): Boolean {
            return oldWidget.javaClass == newWidget.javaClass && oldWidget.key == newWidget.key
        }
    }
}