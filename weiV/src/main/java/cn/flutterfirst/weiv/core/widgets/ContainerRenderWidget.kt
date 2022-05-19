package cn.flutterfirst.weiv.core.widgets

import android.view.ViewGroup
import cn.flutterfirst.weiv.core.elements.ContainerRenderElement
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key

abstract class ContainerRenderWidget<VIEW_GROUP : ViewGroup>(
    key: Key? = null,
    childWidgets: ArrayList<Widget>
) : LeafRenderWidget<VIEW_GROUP>(key, childWidgets) {

    override fun createElement(): Element {
        return ContainerRenderElement(this)
    }
}