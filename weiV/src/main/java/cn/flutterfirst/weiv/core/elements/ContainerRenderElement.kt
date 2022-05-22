package cn.flutterfirst.weiv.core.elements

import android.content.Context
import android.view.View
import android.view.ViewGroup
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import java.util.*

open class ContainerRenderElement<V : View, W : ContainerRenderWidget<V, W>>(
    widget: ContainerRenderWidget<V, W>,
) : LeafRenderElement<V, W>(widget) {
    var childElements: LinkedList<Element> = LinkedList()
    var childViews: LinkedList<View> = LinkedList()
    var dummyWidget = DummyWidget()
    var dummyElement = DummyElement()
    var keyMap = HashMap<Key, Int?>()

    override fun mount(context: Context) {
        super.mount(context)
        for ((index, widget) in widget.childWidgets!!.withIndex()) {
            val childElement = widget.createElement()
            childElement.assignParent(this)
            childElement.mount(context)
            childElements.add(childElement)
            if (childElement is LeafRenderElement<*, *>) {
                childViews.add(childElement.view)
                (view as ViewGroup).addView(childElement.view)
            } else {
                childViews.add(DummyView(context))
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

        if (oldChildWidgets.size < newChildWidgets.size) {
            repeat(newChildWidgets.size - oldChildWidgets.size) {
                oldChildWidgets.add(DummyWidget())
                childElements.add(DummyElement())
                childViews.add(DummyView(context))
            }
        } else if (oldChildWidgets.size > newChildWidgets.size) {
            repeat(oldChildWidgets.size - newChildWidgets.size) {
                newChildWidgets.add(DummyWidget())
            }
        }

        var newChildWidget: Widget<*>
        val newKeyMap = HashMap<Key, Int?>()
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
                    if (oldChildWidgets[oldIndex].key != null) {
                        keyMap[oldChildWidgets[oldIndex].key!!] = oldIndex
                    }
                }
                newKeyMap[newChildWidget.key!!] = i
            }
        }
        keyMap = newKeyMap

        val dirtyWidgets = HashSet<Widget<*>>()
        val dirtyElements = HashSet<Element>()
        val dirtyViews = HashSet<View>()
        var oldChildWidget: Widget<*>
        for (i in 0 until newChildWidgets.size) {
            oldChildWidget = oldChildWidgets[i]
            newChildWidget = newChildWidgets[i]
            if (Widget.canUpdate(oldChildWidget, newChildWidget)) {
                childElements[i].update(newChildWidget)
            } else {
                if (oldChildWidget is ContainerRenderElement<*, *>.DummyWidget) {
                    val newElement = newChildWidget.createElement()
                    newElement.assignParent(this)
                    newElement.mount(context)
                    childElements[i] = newElement
                    if (newElement is LeafRenderElement<*, *>) {
                        childViews[i] = newElement.view
                        (view as ViewGroup).addView(newElement.view)
                    } else {
                        childViews[i] = DummyView(context)
                    }
                } else if (newChildWidget is ContainerRenderElement<*, *>.DummyWidget) {
                    dirtyWidgets.add(newChildWidget)
                    dirtyElements.add(childElements[i])
                    dirtyViews.add(childViews[i])
                    childElements[i].unmount()
                    val index = (view as ViewGroup).indexOfChild(childViews[i])
                    if (index != -1) {
                        (view as ViewGroup).removeViewAt(index)
                    }
                } else {
                    childElements[i].unmount()
                    val newElement = newChildWidget.createElement()
                    newElement.assignParent(this)
                    newElement.mount(context)
                    childElements[i] = newElement
                    val index = (view as ViewGroup).indexOfChild(childViews[i])
                    if (newElement is LeafRenderElement<*, *>) {
                        childViews[i] = newElement.view
                        if (index != -1) {
                            (view as ViewGroup).removeViewAt(index)
                            (view as ViewGroup).addView(newElement.view, index)
                        } else {
                            var prevViewIndex = -1
                            for (j in i - 1 downTo 0) {
                                prevViewIndex = (view as ViewGroup).indexOfChild(childViews[j])
                                if (prevViewIndex != -1) {
                                    break
                                }
                            }
                            (view as ViewGroup).addView(newElement.view, prevViewIndex + 1)
                        }
                    } else {
                        if (index != -1) {
                            (view as ViewGroup).removeViewAt(index)
                        }
                        childViews[i] = DummyView(context)
                    }

                }
            }
        }

//        newChildWidgets.removeAll(dirtyWidgets)
//        childElements.removeAll(dirtyElements)
//        childViews.removeAll(dirtyViews)
    }

    override fun unmount() {
        super.unmount()
        childElements.forEach {
            it.unmount()
        }
        if (view is ViewGroup) {
            (view as ViewGroup).removeAllViews()
        }
    }

    class DummyView(context: Context) : View(context)

    inner class DummyElement : Element(dummyWidget)

    inner class DummyWidget : Widget<DummyWidget>() {
        override fun createElement(): Element {
            return dummyElement
        }
    }
}