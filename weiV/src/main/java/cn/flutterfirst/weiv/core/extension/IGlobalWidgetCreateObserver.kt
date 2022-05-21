package cn.flutterfirst.weiv.core.extension

import cn.flutterfirst.weiv.core.widgets.Widget

interface IGlobalWidgetCreateObserver {
    fun onWidgetCreate(type: String, widget: Widget<*>): Widget<*>
}