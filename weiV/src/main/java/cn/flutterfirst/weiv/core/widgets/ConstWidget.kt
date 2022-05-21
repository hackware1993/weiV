package cn.flutterfirst.weiv.core.widgets

import android.content.Context
import android.widget.FrameLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.elements.ConstRenderElement
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam

class ConstWidget(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    childWidgets: ArrayList<Widget<*>>? = null,
) : LeafRenderWidget<FrameLayout, ConstWidget>(key, layoutParam, childWidgets) {

    override fun createView(context: Context) = FrameLayout(context)

    override fun doParameter(view: FrameLayout, first: Boolean): FrameLayout {
        return view
    }

    override fun createElement(): Element {
        return ConstRenderElement(this)
    }
}

@KotlinOnly
fun WeiV.Const(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    buildCount: Int,
    block: WeiV.(widget: ConstWidget) -> Unit
): ConstWidget {
    if (buildCount == 0) {
        return addContainerRenderWidget(
            ConstWidget(
                key = key,
                layoutParam = layoutParam,
                childWidgets = ArrayList()
            ), block
        )
    } else {
        return addLeafRenderWidget(
            ConstWidget(
                key = key,
                layoutParam = layoutParam,
            )
        )
    }
}