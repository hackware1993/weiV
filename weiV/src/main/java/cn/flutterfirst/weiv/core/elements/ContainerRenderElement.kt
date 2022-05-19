package cn.flutterfirst.weiv.core.elements

import android.content.Context
import android.view.View
import android.view.ViewGroup
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

open class ContainerRenderElement<VIEW_GROUP : ViewGroup>(
    widget: ContainerRenderWidget<VIEW_GROUP>,
) : LeafRenderElement<VIEW_GROUP>(widget) {
    var childElements: ArrayList<Element> = ArrayList()
    var childViews: ArrayList<View> = ArrayList()

    override fun mount(context: Context) {
        super.mount(context)
        widget.childWidgets!!.forEach {
            val childElement = it.createElement()
            childElement.assignParent(this)
            childElement.mount(context)
            childElements.add(childElement)
            if (childElement is LeafRenderElement<*>) {
                childViews.add(childElement.view)
                view.addView(childElement.view)
            } else {
                // Stateful element
            }
        }
    }

    override fun update(newWidget: Widget) {
        val oldChildWidgets = widget.childWidgets!!

        super.update(newWidget)

        val newChildWidgets = newWidget.childWidgets!!

        // TODO
        if (oldChildWidgets.size == newChildWidgets.size) {
            for (i in 0 until oldChildWidgets.size) {
                if (Widget.canUpdate(oldChildWidgets[i], newChildWidgets[i])) {
                    childElements[i].update(newChildWidgets[i])
                } else {
                    val childElement = newChildWidgets[i].createElement()
                    childElement.assignParent(this)
                    childElement.mount(context)

                    childElements.add(childElement)
                    if (childElement is LeafRenderElement<*>) {
                        childViews.add(childElement.view)
                        view.addView(childElement.view)
                    } else {
                        // Stateful element
                    }
                }
            }
        } else {

        }


    }

    override fun unmount() {
        super.unmount()
        childElements.forEach {
            it.unmount()
        }
        view.removeAllViews()
    }
}