package cn.flutterfirst.weiv.core.widgets

import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.elements.ContainerRenderElement
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.IBuild
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam

abstract class Widget<W : Widget<W>>(
    var key: Key? = null,
    var layoutParam: LayoutParam<*>? = null,
    var childWidgets: ArrayList<Widget<*>>? = null,
    var extra: Any? = null,
    var internalExtra: Any? = null,
) {
    abstract fun createElement(): Element

    @JavaOnly
    fun open(build: IBuild) {
        if (this is ContainerRenderWidget<*, *>) {
            val temp = WeiV.globalWidgetContext
            WeiV.globalWidgetContext = childWidgets
            build.build()
            WeiV.globalWidgetContext = temp
        }
    }

    @KotlinOnly
    fun open(block: () -> Unit) {
        if (this is ContainerRenderWidget<*, *>) {
            val temp = WeiV.globalWidgetContext
            WeiV.globalWidgetContext = childWidgets
            block()
            WeiV.globalWidgetContext = temp
        }
    }

    @KotlinOnly
    fun applySelfParams(block: W.() -> Unit): W {
        block(this as W)
        return this
    }

    @JavaOnly
    fun wKey(key: Key?): W {
        this.key = key
        return this as W
    }

    @JavaOnly
    fun wLayoutParam(layoutParam: LayoutParam<*>?): W {
        this.layoutParam = layoutParam
        return this as W
    }

    @JavaOnly
    fun wExtra(extra: Any?): W {
        this.extra = extra
        return this as W
    }

    companion object {
        fun canUpdate(oldWidget: Widget<*>, newWidget: Widget<*>): Boolean {
            if (oldWidget is ContainerRenderElement.PaddingWidget || newWidget is ContainerRenderElement.PaddingWidget) {
                return false
            }
            return oldWidget.javaClass == newWidget.javaClass && oldWidget.key == newWidget.key
        }
    }
}