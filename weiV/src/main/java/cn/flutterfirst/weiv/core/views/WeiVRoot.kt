package cn.flutterfirst.weiv.core.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.elements.LeafRenderElement
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

interface IWeiVRootHolder {
    fun build(buildCount: Int): WeiV
}

open class WeiVRoot(context: Context) : FrameLayout(context) {
    lateinit var weiVRootHolder: IWeiVRootHolder
    var buildCount = 0

    lateinit var weiVRootWidget: LeafRenderWidget<*, *>
    lateinit var weiVRootElement: LeafRenderElement<*, *>
    lateinit var weiVRootView: View

    companion object {
        val weiVRoots = ArrayList<WeiVRoot>()

        @JvmStatic
        fun buildAll() {
            weiVRoots.forEach {
                it.update(it.weiVRootHolder.build(it.buildCount))
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        weiVRoots.add(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        weiVRoots.remove(this)
    }

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
        buildCount++
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
        buildCount++
    }
}