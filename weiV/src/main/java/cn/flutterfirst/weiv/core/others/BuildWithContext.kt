package cn.flutterfirst.weiv.core.others

import cn.flutterfirst.weiv.core.widgets.Widget

interface BuildWithContext<T : Widget<*>> {
    fun build(context: T)
}