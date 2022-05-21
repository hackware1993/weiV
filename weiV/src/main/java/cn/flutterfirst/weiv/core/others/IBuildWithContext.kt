package cn.flutterfirst.weiv.core.others

import cn.flutterfirst.weiv.core.widgets.Widget

interface IBuildWithContext<T : Widget<*>> {
    fun build(context: T)
}