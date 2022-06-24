package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.others.getLayoutParam
import cn.flutterfirst.weiv.core.others.setLayoutParam
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc

class weiVConstraintLayout(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    childWidgets: ArrayList<Widget<*>> = ArrayList(),
    extra: Any? = null,
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
        childLayoutParam: LayoutParam<*>?,
    ) {
        assert(childLayoutParam == null || childLayoutParam is ConstraintLayoutParam)
        var newLayoutParam = childLayoutParam as ConstraintLayoutParam?
        if (newLayoutParam == null) {
            newLayoutParam = ConstraintLayoutParam()
            newLayoutParam.topLeftTo = CL.parent
        }
        val oldLayoutParam = child.getLayoutParam<ConstraintLayoutParam>()
        if (oldLayoutParam == null) {
            parent.markNeedsRecalculateConstraints()
        } else {
            var left = newLayoutParam.left
            var top = newLayoutParam.top
            var right = newLayoutParam.right
            var bottom = newLayoutParam.bottom
            var baseline = newLayoutParam.baseline
            var width = newLayoutParam.width
            var height = newLayoutParam.height
            if (newLayoutParam.size != null) {
                width = newLayoutParam.size!!
                height = newLayoutParam.size!!
            }

            if (newLayoutParam.topLeftTo != null) {
                left = newLayoutParam.topLeftTo!!.left
                top = newLayoutParam.topLeftTo!!.top
            }

            if (newLayoutParam.topCenterTo != null) {
                left = newLayoutParam.topCenterTo!!.left
                right = newLayoutParam.topCenterTo!!.right
                top = newLayoutParam.topCenterTo!!.top
            }

            if (newLayoutParam.topRightTo != null) {
                top = newLayoutParam.topRightTo!!.top
                right = newLayoutParam.topRightTo!!.right
            }

            if (newLayoutParam.centerLeftTo != null) {
                left = newLayoutParam.centerLeftTo!!.left
                top = newLayoutParam.centerLeftTo!!.top
                bottom = newLayoutParam.centerLeftTo!!.bottom
            }

            if (newLayoutParam.centerTo != null) {
                left = newLayoutParam.centerTo!!.left
                right = newLayoutParam.centerTo!!.right
                top = newLayoutParam.centerTo!!.top
                bottom = newLayoutParam.centerTo!!.bottom
            }

            if (newLayoutParam.centerRightTo != null) {
                right = newLayoutParam.centerRightTo!!.right
                top = newLayoutParam.centerRightTo!!.top
                bottom = newLayoutParam.centerRightTo!!.bottom
            }

            if (newLayoutParam.bottomLeftTo != null) {
                left = newLayoutParam.bottomLeftTo!!.left
                bottom = newLayoutParam.bottomLeftTo!!.bottom
            }

            if (newLayoutParam.bottomCenterTo != null) {
                left = newLayoutParam.bottomCenterTo!!.left
                right = newLayoutParam.bottomCenterTo!!.right
                bottom = newLayoutParam.bottomCenterTo!!.bottom
            }

            if (newLayoutParam.bottomRightTo != null) {
                right = newLayoutParam.bottomRightTo!!.right
                bottom = newLayoutParam.bottomRightTo!!.bottom
            }

            if (newLayoutParam.centerHorizontalTo != null) {
                left = newLayoutParam.centerHorizontalTo!!.left
                right = newLayoutParam.centerHorizontalTo!!.right
            }

            if (newLayoutParam.centerVerticalTo != null) {
                top = newLayoutParam.centerVerticalTo!!.top
                bottom = newLayoutParam.centerVerticalTo!!.bottom
            }

            if (newLayoutParam.outTopLeftTo != null) {
                right = newLayoutParam.outTopLeftTo!!.leftReverse
                bottom = newLayoutParam.outTopLeftTo!!.topReverse
            }

            if (newLayoutParam.outTopCenterTo != null) {
                left = newLayoutParam.outTopCenterTo!!.left
                right = newLayoutParam.outTopCenterTo!!.right
                bottom = newLayoutParam.outTopCenterTo!!.topReverse
            }

            if (newLayoutParam.outTopRightTo != null) {
                left = newLayoutParam.outTopRightTo!!.rightReverse
                bottom = newLayoutParam.outTopRightTo!!.topReverse
            }

            if (newLayoutParam.outCenterLeftTo != null) {
                top = newLayoutParam.outCenterLeftTo!!.top
                bottom = newLayoutParam.outCenterLeftTo!!.bottom
                right = newLayoutParam.outCenterLeftTo!!.leftReverse
            }

            if (newLayoutParam.outCenterRightTo != null) {
                top = newLayoutParam.outCenterRightTo!!.top
                bottom = newLayoutParam.outCenterRightTo!!.bottom
                left = newLayoutParam.outCenterRightTo!!.rightReverse
            }

            if (newLayoutParam.outBottomLeftTo != null) {
                right = newLayoutParam.outBottomLeftTo!!.leftReverse
                top = newLayoutParam.outBottomLeftTo!!.bottomReverse
            }

            if (newLayoutParam.outBottomCenterTo != null) {
                left = newLayoutParam.outBottomCenterTo!!.left
                right = newLayoutParam.outBottomCenterTo!!.right
                top = newLayoutParam.outBottomCenterTo!!.bottomReverse
            }

            if (newLayoutParam.outBottomRightTo != null) {
                left = newLayoutParam.outBottomRightTo!!.rightReverse
                top = newLayoutParam.outBottomRightTo!!.bottomReverse
            }

            if (newLayoutParam.centerTopLeftTo != null) {
                left = newLayoutParam.centerTopLeftTo!!.left
                right = newLayoutParam.centerTopLeftTo!!.leftReverse
                top = newLayoutParam.centerTopLeftTo!!.top
                bottom = newLayoutParam.centerTopLeftTo!!.topReverse
            }

            if (newLayoutParam.centerTopCenterTo != null) {
                left = newLayoutParam.centerTopCenterTo!!.left
                right = newLayoutParam.centerTopCenterTo!!.right
                top = newLayoutParam.centerTopCenterTo!!.top
                bottom = newLayoutParam.centerTopCenterTo!!.topReverse
            }

            if (newLayoutParam.centerTopRightTo != null) {
                left = newLayoutParam.centerTopRightTo!!.rightReverse
                right = newLayoutParam.centerTopRightTo!!.right
                top = newLayoutParam.centerTopRightTo!!.top
                bottom = newLayoutParam.centerTopRightTo!!.topReverse
            }

            if (newLayoutParam.centerCenterLeftTo != null) {
                left = newLayoutParam.centerCenterLeftTo!!.left
                right = newLayoutParam.centerCenterLeftTo!!.leftReverse
                top = newLayoutParam.centerCenterLeftTo!!.top
                bottom = newLayoutParam.centerCenterLeftTo!!.bottom
            }

            if (newLayoutParam.centerCenterRightTo != null) {
                left = newLayoutParam.centerCenterRightTo!!.rightReverse
                right = newLayoutParam.centerCenterRightTo!!.right
                top = newLayoutParam.centerCenterRightTo!!.top
                bottom = newLayoutParam.centerCenterRightTo!!.bottom
            }

            if (newLayoutParam.centerBottomLeftTo != null) {
                left = newLayoutParam.centerBottomLeftTo!!.left
                right = newLayoutParam.centerBottomLeftTo!!.leftReverse
                top = newLayoutParam.centerBottomLeftTo!!.bottomReverse
                bottom = newLayoutParam.centerBottomLeftTo!!.bottom
            }

            if (newLayoutParam.centerBottomCenterTo != null) {
                left = newLayoutParam.centerBottomCenterTo!!.left
                right = newLayoutParam.centerBottomCenterTo!!.right
                top = newLayoutParam.centerBottomCenterTo!!.bottomReverse
                bottom = newLayoutParam.centerBottomCenterTo!!.bottom
            }

            if (newLayoutParam.centerBottomRightTo != null) {
                left = newLayoutParam.centerBottomRightTo!!.rightReverse
                right = newLayoutParam.centerBottomRightTo!!.right
                top = newLayoutParam.centerBottomRightTo!!.bottomReverse
                bottom = newLayoutParam.centerBottomRightTo!!.bottom
            }

            val margin = newLayoutParam.margin
            val goneMargin = newLayoutParam.goneMargin
            if (left?.margin != null) {
                margin.addLeft(left.margin!!)
            }
        }
        child.setLayoutParam(newLayoutParam)
    }
}

var creator: IExtensionCreator<weiVConstraintLayout>? = null

@KotlinOnly
fun LeafRenderWidget<*, *>.applyConstraint(block: ConstraintLayoutParam.() -> Unit) {
    layoutParam = ConstraintLayoutParam()
    block(layoutParam as ConstraintLayoutParam)
}

@KotlinOnly
fun WeiV.ConstraintLayout(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    extra: Any? = null,
    block: WeiV.(widget: weiVConstraintLayout) -> Unit,
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