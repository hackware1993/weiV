package cn.flutterfirst.weiv.core.elements

import android.content.Context
import android.widget.FrameLayout
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.widgets.ConstWidget
import cn.flutterfirst.weiv.core.widgets.Widget

class ConstRenderElement(
    widget: ConstWidget,
) : LeafRenderElement<FrameLayout, ConstWidget>(widget) {
    var childElements: ArrayList<Element> = ArrayList()

    override fun mount(context: Context) {
        super.mount(context)
        widget.childWidgets!!.forEach {
            val childElement = it.createElement()
            childElement.assignParent(this)
            childElement.mount(context)
            childElements.add(childElement)
            if (childElement is LeafRenderElement<*, *>) {
                view.addView(childElement.view)
            }
        }
    }

    override fun update(newWidget: Widget<*>) {
        widget = ExtensionMgr.dispatchWidgetAttach(newWidget)
    }

    override fun unmount() {
        super.unmount()
        childElements.forEach {
            it.unmount()
        }
        view.removeAllViews()
    }
}