package cn.flutterfirst.weiv.core.others

import cn.flutterfirst.weiv.core.widgets.Widget

interface IBuildWithContext<W : Widget<*>> {
    fun build(context: W)
}