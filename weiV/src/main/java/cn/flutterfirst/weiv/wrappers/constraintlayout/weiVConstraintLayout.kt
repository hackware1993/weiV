package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.others.setLayoutParam
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc

class weiVConstraintLayout(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    childWidgets: ArrayList<Widget<*>> = ArrayList(),
    extra: Any? = null
) : ContainerRenderWidget<ConstraintLayout, weiVConstraintLayout>(
    key,
    layoutParam,
    childWidgets,
    extra
) {

    override fun createView(context: Context) = ConstraintLayout(context)

    override fun doParameter(view: ConstraintLayout, first: Boolean): ConstraintLayout {
        return view
    }

    override fun processChildLayoutParam(
        parent: ConstraintLayout,
        child: View,
        childLayoutParam: LayoutParam<*>?
    ) {
        assert(childLayoutParam is ConstraintLayoutParam)
        child.setLayoutParam(childLayoutParam as ConstraintLayoutParam)
    }
}

var creator: IExtensionCreator<weiVConstraintLayout>? = null

@KotlinOnly
fun WeiV.ConstraintLayout(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    extra: Any? = null,
    block: WeiV.(widget: weiVConstraintLayout) -> Unit
): weiVConstraintLayout {
    if (creator == null) {
        creator = ExtensionMgr.getExtension(InternalWidgetDesc.CONSTRAINT_LAYOUT)
    }

    return addContainerRenderWidget(
        creator!!.createWidget(
            key,
            layoutParam,
            ArrayList<Widget<*>>(),
            extra
        ), block
    )
}