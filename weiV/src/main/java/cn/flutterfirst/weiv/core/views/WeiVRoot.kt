package cn.flutterfirst.weiv.core.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.elements.LeafRenderElement
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

open class WeiVRoot(context: Context) : FrameLayout(context) {
    lateinit var weiVRootWidget: LeafRenderWidget<*, *>
    lateinit var weiVRootElement: LeafRenderElement<*, *>
    lateinit var weiVRootView: View

    fun init(weiV: WeiV) {
        assert(weiV.currentWidgetContext.size == 1)
        weiVRootWidget = weiV.currentWidgetContext[0] as LeafRenderWidget<*, *>
        weiVRootElement = weiVRootWidget.createElement() as LeafRenderElement<*, *>
        weiVRootElement.mount(context)
        weiVRootView = weiVRootElement.view
        addView(
            weiVRootView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    fun update(weiV: WeiV) {
        assert(weiV.currentWidgetContext.size == 1)
        val newRootWidget = weiV.currentWidgetContext[0] as LeafRenderWidget<*, *>
        if (Widget.canUpdate(newRootWidget, weiVRootWidget)) {
            weiVRootElement.update(newRootWidget)
        } else {
            weiVRootElement.unmount()
            weiVRootElement = newRootWidget.createElement() as LeafRenderElement<*, *>
            weiVRootElement.mount(context)
            weiVRootView = weiVRootElement.view
            removeAllViews()
            addView(
                weiVRootView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        weiVRootWidget = newRootWidget
    }
}