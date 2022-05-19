package cn.flutterfirst.weiv.core.others

import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget

interface BuildWithContext<T : ContainerRenderWidget<*>> {
    fun build(context: T)
}