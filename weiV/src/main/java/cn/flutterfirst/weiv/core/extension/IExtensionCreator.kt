package cn.flutterfirst.weiv.core.extension

import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget

fun interface IExtensionCreator<T : LeafRenderWidget<*>> {
    fun createWidget(vararg params: Any?): T
}