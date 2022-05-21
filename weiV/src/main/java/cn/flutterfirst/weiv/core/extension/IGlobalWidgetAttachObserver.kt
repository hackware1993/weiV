package cn.flutterfirst.weiv.core.extension

import cn.flutterfirst.weiv.core.widgets.Widget

interface IGlobalWidgetAttachObserver {
    fun onWidgetAttach(widget: Widget<*>): Widget<*>
}