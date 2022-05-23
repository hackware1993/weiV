package cn.flutterfirst.weiv.core.elements

import android.content.Context
import android.view.View
import android.view.ViewGroup
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import java.util.*

open class ContainerRenderElement<V : View, W : ContainerRenderWidget<V, W>>(
    widget: ContainerRenderWidget<V, W>,
) : LeafRenderElement<V, W>(widget) {
    var childElements: LinkedList<Element> = LinkedList()
    var childViews: LinkedList<View> = LinkedList()
    var dummyPaddingWidget = PaddingWidget()
    var dummyPaddingElement = PaddingElement()
    var keyMap = HashMap<Key, Int?>()

    lateinit var parentView: ViewGroup

    override fun mount(context: Context) {
        super.mount(context)
        if (view is ViewGroup) {
            parentView = (view as ViewGroup)
        }
        for ((index, widget) in widget.childWidgets!!.withIndex()) {
            val childElement = widget.createElement()
            childElement.assignParent(this)
            childElement.mount(context)
            childElements.add(childElement)
            if (childElement is LeafRenderElement<*, *>) {
                childViews.add(childElement.view)
                parentView.addView(childElement.view)
            } else {
                childViews.add(PaddingView(context))
            }
            if (widget.key != null) {
                val value = keyMap[widget.key]
                if (value != null) {
                    throw IllegalStateException("Duplicate keys are not allowed between sibling widgets")
                }
                keyMap[widget.key!!] = index
            }
        }
    }

    override fun update(newWidget: Widget<*>) {
        val oldChildWidgets = widget.childWidgets!!
        super.update(newWidget)
        val newChildWidgets = widget.childWidgets!!

        // Add padding objects
        if (oldChildWidgets.size < newChildWidgets.size) {
            repeat(newChildWidgets.size - oldChildWidgets.size) {
                oldChildWidgets.add(PaddingWidget())
                childElements.add(PaddingElement())
                childViews.add(PaddingView(context))
            }
        } else if (oldChildWidgets.size > newChildWidgets.size) {
            repeat(oldChildWidgets.size - newChildWidgets.size) {
                newChildWidgets.add(PaddingWidget())
            }
        }

        // Rearrange widgets by key
        var newChildWidget: Widget<*>
        for (i in 0 until newChildWidgets.size) {
            newChildWidget = newChildWidgets[i]
            if (newChildWidget.key != null) {
                val oldIndex = keyMap[newChildWidget.key]
                if (oldIndex != null) {
                    val tempWidget = oldChildWidgets[i]
                    oldChildWidgets[i] = oldChildWidgets[oldIndex]
                    oldChildWidgets[oldIndex] = tempWidget
                    val tempElement = childElements[i]
                    childElements[i] = childElements[oldIndex]
                    childElements[oldIndex] = tempElement
                    val tempView = childViews[i]
                    childViews[i] = childViews[oldIndex]
                    childViews[oldIndex] = tempView
                    keyMap[newChildWidget.key!!] = i
                    if (tempWidget.key != null) {
                        keyMap[tempWidget.key!!] = oldIndex
                    }
                }
            }
        }

        val newViewIndexMap = HashMap<View, Int>()
        var newViewCount = 0
        var oldChildWidget: Widget<*>
        for (i in 0 until newChildWidgets.size) {
            oldChildWidget = oldChildWidgets[i]
            newChildWidget = newChildWidgets[i]
            if (newChildWidget is LeafRenderWidget<*, *>) {
                if (oldChildWidget is LeafRenderWidget<*, *>) {
                    newViewIndexMap[childViews[i]] = newViewCount
                }
                newViewCount++
            }
            if (Widget.canUpdate(oldChildWidget, newChildWidget)) {
                if (oldChildWidget != newChildWidget) {
                    childElements[i].update(newChildWidget)
                }
                val childView = childViews[i]
                val oldViewIndex = indexOfChild(childView)
                val newViewIndex = newViewIndexMap[childView]
                if (oldViewIndex != -1 && oldViewIndex != newViewIndex) {
                    parentView.removeViewAt(oldViewIndex)
                    parentView.addView(childView, newViewIndex!!)
                }
            } else {
                if (oldChildWidget is ContainerRenderElement<*, *>.PaddingWidget) {
                    val newElement = newChildWidget.createElement()
                    newElement.assignParent(this)
                    newElement.mount(context)
                    childElements[i] = newElement
                    if (newElement is LeafRenderElement<*, *>) {
                        childViews[i] = newElement.view
                        parentView.addView(newElement.view)
                    } else {
                        childViews[i] = PaddingView(context)
                    }
                } else if (newChildWidget is ContainerRenderElement<*, *>.PaddingWidget) {
                    childElements[i].unmount()
                    val oldViewIndex = indexOfChild(childViews[i])
                    if (oldViewIndex != -1) {
                        parentView.removeViewAt(oldViewIndex)
                    }
                } else {
                    childElements[i].unmount()
                    val newElement = newChildWidget.createElement()
                    newElement.assignParent(this)
                    newElement.mount(context)
                    childElements[i] = newElement
                    val oldViewIndex = indexOfChild(childViews[i])
                    if (newElement is LeafRenderElement<*, *>) {
                        if (oldViewIndex != -1) {
                            parentView.removeViewAt(oldViewIndex)
                            parentView.addView(newElement.view, oldViewIndex)
                            childViews[i] = newElement.view
                        } else {
                            parentView.addView(newElement.view)
                            childViews[i] = newElement.view
                        }
                    } else {
                        if (oldViewIndex != -1) {
                            parentView.removeViewAt(oldViewIndex)
                        }
                        childViews[i] = PaddingView(context)
                    }

                }
            }
        }

        // Clean padding objects
        for (i in newChildWidgets.size - 1 downTo 0) {
            if (newChildWidgets[i] is ContainerRenderElement<*, *>.PaddingWidget) {
                newChildWidgets.removeAt(i)
                childElements.removeAt(i)
                childViews.removeAt(i)
            }
        }

        keyMap.clear()
        for (i in 0 until newChildWidgets.size) {
            if (newChildWidgets[i].key != null) {
                keyMap[newChildWidgets[i].key!!] = i
            }
        }
    }

    // To optimize
    fun indexOfChild(view: View): Int {
        if (view is PaddingView) {
            return -1
        }
        if (this.view is ViewGroup) {
            return parentView.indexOfChild(view)
        }
        return -1
    }

    override fun unmount() {
        super.unmount()
        childElements.forEach {
            it.unmount()
        }
        if (view is ViewGroup) {
            parentView.removeAllViews()
        }
    }

    class PaddingView(context: Context) : View(context)

    inner class PaddingElement : Element(dummyPaddingWidget)

    inner class PaddingWidget : Widget<PaddingWidget>() {
        override fun createElement(): Element {
            return dummyPaddingElement
        }
    }
}