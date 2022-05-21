package cn.flutterfirst.weiv.core.extension

import cn.flutterfirst.weiv.core.widgets.Widget

fun interface IExtensionCreator<W : Widget<*>> {
    fun createWidget(vararg params: Any?): W
}