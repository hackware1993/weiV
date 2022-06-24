package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.others.LayoutParam.Companion.matchParent
import cn.flutterfirst.weiv.core.others.LayoutParam.Companion.wrapContent
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

            var margin = newLayoutParam.margin
            var goneMargin = newLayoutParam.goneMargin

            if (left != null) {
                if (left.margin != null) {
                    margin = margin.addLeft(left.margin!!)
                }
                if (left.goneMargin != null) {
                    goneMargin = goneMargin.addLeft(left.goneMargin!!)
                }
            }

            if (top != null) {
                if (top.margin != null) {
                    margin = margin.addTop(top.margin!!)
                }
                if (top.goneMargin != null) {
                    goneMargin = goneMargin.addTop(top.goneMargin!!)
                }
            }

            if (right != null) {
                if (right.margin != null) {
                    margin = margin.addRight(right.margin!!)
                }
                if (right.goneMargin != null) {
                    goneMargin = goneMargin.addRight(right.goneMargin!!)
                }
            }

            if (bottom != null) {
                if (bottom.margin != null) {
                    margin = margin.addBottom(bottom.margin!!)
                }
                if (bottom.goneMargin != null) {
                    goneMargin = goneMargin.addBottom(bottom.goneMargin!!)
                }
            }

            if (baseline != null) {
                if (baseline.margin != null) {
                    margin = margin.addBottom(baseline.margin!!)
                }
                if (baseline.goneMargin != null) {
                    goneMargin = goneMargin.addBottom(baseline.goneMargin!!)
                }
            }

            if (width == matchParent) {
                left = CL.parent.left
                right = CL.parent.right
            }

            if (height == matchParent) {
                top = CL.parent.top
                bottom = CL.parent.bottom
                baseline = null
            }

            var needsRecalculateConstraints = false
            var needsLayout = false
            var needsReorderPaintingOrder = false
            var needsPaint = false
            var needsReorderEventOrder = false

            if (oldLayoutParam.id != newLayoutParam.id) {
                needsRecalculateConstraints = true
                needsLayout = true
            }

            if (oldLayoutParam.width != newLayoutParam.width) {
                needsRecalculateConstraints = true
                if (getMinimalConstraintCount(oldLayoutParam.width) == getMinimalConstraintCount(
                        width)
                ) {
                    needsRecalculateConstraints = false
                }
                needsLayout = true
            }

            if (oldLayoutParam.height != newLayoutParam.height) {
                needsRecalculateConstraints = true
                if (getMinimalConstraintCount(oldLayoutParam.height) == getMinimalConstraintCount(
                        height)
                ) {
                    needsRecalculateConstraints = false
                }
                needsLayout = true
            }

            if (oldLayoutParam.visibility != newLayoutParam.visibility) {
                if (oldLayoutParam.visibility == View.GONE || newLayoutParam.visibility == View.GONE) {
                    needsLayout = true
                } else {
                    needsPaint = true
                }
            }

            if (oldLayoutParam.percentageMargin != newLayoutParam.percentageMargin) {
                needsLayout = true
            }

            if (oldLayoutParam.margin != margin) {
                needsLayout = true
            }

            if (oldLayoutParam.goneMargin != goneMargin) {
                needsLayout = true
            }

            if (oldLayoutParam.left != left) {
                needsRecalculateConstraints = true
                needsLayout = true
            }

            if (oldLayoutParam.right != right) {
                needsRecalculateConstraints = true
                needsLayout = true
            }

            if (oldLayoutParam.top != top) {
                needsRecalculateConstraints = true
                needsLayout = true
            }

            if (oldLayoutParam.bottom != bottom) {
                needsRecalculateConstraints = true
                needsLayout = true
            }

            if (oldLayoutParam.baseline != baseline) {
                needsRecalculateConstraints = true
                needsLayout = true
            }

            if (oldLayoutParam.zIndex != newLayoutParam.zIndex) {
                needsReorderPaintingOrder = true
                needsReorderEventOrder = true
                needsPaint = true
            }

            if (oldLayoutParam.translateConstraint != newLayoutParam.translateConstraint) {
                needsLayout = true
            }

            if (oldLayoutParam.widthPercent != newLayoutParam.widthPercent) {
                needsLayout = true
            }

            if (oldLayoutParam.heightPercent != newLayoutParam.heightPercent) {
                needsLayout = true
            }

            if (oldLayoutParam.widthPercentageAnchor != newLayoutParam.widthPercentageAnchor) {
                needsLayout = true
            }

            if (oldLayoutParam.heightPercentageAnchor != newLayoutParam.heightPercentageAnchor) {
                needsLayout = true
            }

            if (oldLayoutParam.horizontalBias != newLayoutParam.horizontalBias) {
                needsLayout = true
            }

            if (oldLayoutParam.verticalBias != newLayoutParam.verticalBias) {
                needsLayout = true
            }

            if (oldLayoutParam.percentageTranslate != newLayoutParam.percentageTranslate) {
                needsPaint = true
            }

            if (oldLayoutParam.minWidth != newLayoutParam.minWidth) {
                needsLayout = true
            }

            if (oldLayoutParam.maxWidth != newLayoutParam.maxWidth) {
                needsLayout = true
            }

            if (oldLayoutParam.minHeight != newLayoutParam.minHeight) {
                needsLayout = true
            }

            if (oldLayoutParam.maxHeight != newLayoutParam.maxHeight) {
                needsLayout = true
            }

            if (oldLayoutParam.widthHeightRatio != newLayoutParam.widthHeightRatio) {
                needsLayout = true
            }

            if (oldLayoutParam.ratioBaseOnWidth != newLayoutParam.ratioBaseOnWidth) {
                needsLayout = true
            }

            if (oldLayoutParam.eIndex != newLayoutParam.eIndex) {
                needsReorderEventOrder = true
            }

            if (needsLayout) {
                if (needsRecalculateConstraints) {
                    parent.markNeedsRecalculateConstraints()
                }
                parent.markNeedsLayout()
            } else {
                if (needsReorderPaintingOrder) {
                    parent.markNeedsReorderPaintingOrder()
                }
                if (needsReorderEventOrder) {
                    parent.markNeedsReorderEventOrder()
                }
                if (needsPaint) {
                    parent.markNeedsPaint()
                }
            }
        }
        child.setLayoutParam(newLayoutParam)
    }
}

fun getMinimalConstraintCount(size: Int): Int {
    return if (size == matchParent) {
        0
    } else if (size == wrapContent || size >= 0) {
        1
    } else {
        2
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