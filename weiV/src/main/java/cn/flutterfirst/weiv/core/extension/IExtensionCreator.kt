package cn.flutterfirst.weiv.core.extension

import cn.flutterfirst.weiv.core.widgets.Widget

fun interface IExtensionCreator<T : Widget<*>> {
    fun createWidget(vararg params: Any?): T
}