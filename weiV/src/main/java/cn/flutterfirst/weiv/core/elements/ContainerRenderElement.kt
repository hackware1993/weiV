package cn.flutterfirst.weiv.core.elements

import android.annotation.SuppressLint
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

    companion object {
        val dummyPaddingWidget = PaddingWidget()

        // Will never be mounted
        @SuppressLint("StaticFieldLeak")
        val dummyPaddingElement = PaddingElement()
    }

    var keyMap = HashMap<Key, Int>()

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
                childElement.view.tag = parentView.childCount
                parentView.addView(childElement.view)
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
        var childCountNotChanged: Boolean? = null
        if (oldChildWidgets.size < newChildWidgets.size) {
            repeat(newChildWidgets.size - oldChildWidgets.size) {
                oldChildWidgets.add(dummyPaddingWidget)
                childElements.add(dummyPaddingElement)
            }
        } else if (oldChildWidgets.size > newChildWidgets.size) {
            repeat(oldChildWidgets.size - newChildWidgets.size) {
                newChildWidgets.add(dummyPaddingWidget)
            }
        } else {
            childCountNotChanged = true
        }

        // Rearrange widgets by key
        val newKeyMap = HashMap<Key, Int>()
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
                    keyMap[newChildWidget.key!!] = i
                    if (tempWidget.key != null) {
                        keyMap[tempWidget.key!!] = oldIndex
                    }
                }
                val value = newKeyMap[newChildWidget.key]
                if (value != null) {
                    throw IllegalStateException("Duplicate keys are not allowed between sibling widgets")
                }
                if (childCountNotChanged == true) {
                    newKeyMap[newChildWidget.key!!] = i
                } else {
                    newKeyMap[newChildWidget.key!!] = -1
                }
            }
        }

        var newViewCount = 0
        var oldChildWidget: Widget<*>
        for (i in 0 until newChildWidgets.size) {
            oldChildWidget = oldChildWidgets[i]
            newChildWidget = newChildWidgets[i]
            if (newChildWidget is LeafRenderWidget<*, *>) {
                newChildWidget.internalExtra = newViewCount
                newViewCount++
            }
            if (Widget.canUpdate(oldChildWidget, newChildWidget)) { // Update
                if (oldChildWidget != newChildWidget) {
                    childElements[i].update(newChildWidget)
                }
                var childView: View? = null
                if (childElements[i] is LeafRenderElement<*, *>) {
                    childView = (childElements[i] as LeafRenderElement<*, *>).view
                }
                val oldViewIndex = indexOfChild(childView)
                val newViewIndex = newChildWidget.internalExtra as Int
                if (oldViewIndex != -1 && oldViewIndex != newViewIndex) {
                    parentView.removeView(childView)
                    parentView.addView(childView, newViewIndex)
                    childView!!.tag = newViewIndex
                }
            } else {
                if (oldChildWidget is PaddingWidget) { // Add
                    val newElement = newChildWidget.createElement()
                    newElement.assignParent(this)
                    newElement.mount(context)
                    childElements[i] = newElement
                    if (newElement is LeafRenderElement<*, *>) {
                        parentView.addView(newElement.view, newChildWidget.internalExtra as Int)
                        newElement.view.tag = newChildWidget.internalExtra as Int
                    }
                } else if (newChildWidget is PaddingWidget) {  // Delete
                    childElements[i].unmount()
                    var childView: View? = null
                    if (childElements[i] is LeafRenderElement<*, *>) {
                        childView = (childElements[i] as LeafRenderElement<*, *>).view
                    }
                    val oldViewIndex = indexOfChild(childView)
                    if (oldViewIndex != -1) {
                        parentView.removeView(childView)
                    }
                } else {    // Replace
                    childElements[i].unmount()
                    var childView: View? = null
                    if (childElements[i] is LeafRenderElement<*, *>) {
                        childView = (childElements[i] as LeafRenderElement<*, *>).view
                    }
                    val newElement = newChildWidget.createElement()
                    newElement.assignParent(this)
                    newElement.mount(context)
                    childElements[i] = newElement
                    val oldViewIndex = indexOfChild(childView)
                    if (newElement is LeafRenderElement<*, *>) {
                        if (oldViewIndex != -1) {
                            parentView.removeView(childView)
                            parentView.addView(newElement.view, newChildWidget.internalExtra as Int)
                            newElement.view.tag = newChildWidget.internalExtra as Int
                        } else {
                            parentView.addView(newElement.view, newChildWidget.internalExtra as Int)
                            newElement.view.tag = newChildWidget.internalExtra as Int
                        }
                    } else {
                        if (oldViewIndex != -1) {
                            parentView.removeView(childView)
                        }
                    }
                }
            }
        }

        // Clean padding objects
        if (childCountNotChanged != true) {
            for (i in newChildWidgets.size - 1 downTo 0) {
                if (newChildWidgets[i] is PaddingWidget) {
                    newChildWidgets.removeAt(i)
                    childElements.removeAt(i)
                }
            }
            keyMap.clear()
            for (i in 0 until newChildWidgets.size) {
                if (newChildWidgets[i].key != null) {
                    keyMap[newChildWidgets[i].key!!] = i
                    if (keyMap.size == newKeyMap.size) {
                        break
                    }
                }
            }
        } else {
            keyMap = newKeyMap
        }
    }

    fun indexOfChild(view: View?): Int {
        return (view?.tag as Int?) ?: -1
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

    class PaddingElement : Element(dummyPaddingWidget)

    class PaddingWidget : Widget<PaddingWidget>() {
        override fun createElement(): Element {
            return dummyPaddingElement
        }
    }
}