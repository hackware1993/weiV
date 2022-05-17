package cn.flutterfirst.weiv.core.elements

import cn.flutterfirst.weiv.core.widgets.Widget

abstract class Element(open var widget: Widget) {

    abstract fun build(): Widget?

    open fun update(widget: Widget?) {
    }

    open fun mount() {
    }

    open fun unmount() {
    }
}