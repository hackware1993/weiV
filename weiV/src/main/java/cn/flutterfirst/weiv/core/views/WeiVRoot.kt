package cn.flutterfirst.weiv.core.views

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.elements.ContainerRenderElement
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

open class WeiVRoot(context: Context) : FrameLayout(context) {
    lateinit var rootWidget: ContainerRenderWidget<*, *>
    lateinit var rootElement: ContainerRenderElement<*, *>
    lateinit var rootView: ViewGroup

    fun init(weiV: WeiV) {
        assert(weiV.currentWidgetContext.size == 1)
        assert(weiV.currentWidgetContext[0] is ContainerRenderWidget<*, *>)
        rootWidget = weiV.currentWidgetContext[0] as ContainerRenderWidget<*, *>
        rootElement = rootWidget.createElement() as ContainerRenderElement<*, *>
        rootElement.mount(context)
        rootView = rootElement.view
        addView(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    fun update(weiV: WeiV) {
        assert(weiV.currentWidgetContext.size == 1)
        assert(weiV.currentWidgetContext[0] is ContainerRenderWidget<*, *>)
        val newRootWidget = weiV.currentWidgetContext[0] as ContainerRenderWidget<*, *>
        if (Widget.canUpdate(newRootWidget, rootWidget)) {
            rootElement.update(newRootWidget)
        } else {
            rootElement.unmount()
            rootElement = newRootWidget.createElement() as ContainerRenderElement<*, *>
            rootElement.mount(context)
            rootView = rootElement.view
            removeAllViews()
            addView(
                rootView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        rootWidget = newRootWidget
    }
}