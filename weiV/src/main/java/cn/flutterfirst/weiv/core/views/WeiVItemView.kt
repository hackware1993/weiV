package cn.flutterfirst.weiv.core.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import cn.flutterfirst.weiv.core.elements.LeafRenderElement
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

class WeiVItemView(context: Context) : FrameLayout(context) {
    var weiVRootWidget: LeafRenderWidget<*, *>? = null
    lateinit var weiVRootElement: LeafRenderElement<*, *>
    lateinit var weiVRootView: View

    fun inflate(itemWidget: LeafRenderWidget<*, *>) {
        if (weiVRootWidget == null) {
            weiVRootWidget = itemWidget
            weiVRootElement = weiVRootWidget!!.createElement() as LeafRenderElement<*, *>
            weiVRootElement.mount(context)
            weiVRootView = weiVRootElement.view
            addView(
                weiVRootView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        } else {
            if (Widget.canUpdate(weiVRootWidget!!, itemWidget)) {
                weiVRootElement.update(itemWidget)
            } else {
                weiVRootElement.unmount()
                weiVRootElement = itemWidget.createElement() as LeafRenderElement<*, *>
                weiVRootElement.mount(context)
                weiVRootView = weiVRootElement.view
                removeAllViews()
                addView(
                    weiVRootView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            weiVRootWidget = itemWidget
        }
    }
}