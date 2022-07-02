package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.graphics.Point
import cn.flutterfirst.weiv.core.others.LayoutParam

class ConstraintLayoutParam(
    width: Int = wrapContent,
    height: Int = wrapContent,
    var id: ConstraintId? = null,
    var size: Int? = null,
    var clickPadding: EdgeInsets = EdgeInsets.zero,
    var visibility: CLVisibility = CL.visible,
    var percentageMargin: Boolean = false,
    var margin: EdgeInsets = EdgeInsets.zero,
    var goneMargin: EdgeInsets = EdgeInsets.zero,
    var left: ConstraintAlign? = null,
    var top: ConstraintAlign? = null,
    var right: ConstraintAlign? = null,
    var bottom: ConstraintAlign? = null,
    var baseline: ConstraintAlign? = null,
    var zIndex: Int? = null,
    var translate: Point = CL.zeroPoint,
    var translateConstraint: Boolean = false,
    var widthPercent: Double = 1.0,
    var heightPercent: Double = 1.0,
    var widthPercentageAnchor: PercentageAnchor = PercentageAnchor.constraint,
    var heightPercentageAnchor: PercentageAnchor = PercentageAnchor.constraint,
    var horizontalBias: Double = 0.5,
    var verticalBias: Double = 0.5,
    var topLeftTo: ConstraintId? = null,
    var topCenterTo: ConstraintId? = null,
    var topRightTo: ConstraintId? = null,
    var centerLeftTo: ConstraintId? = null,
    var centerTo: ConstraintId? = null,
    var centerRightTo: ConstraintId? = null,
    var bottomLeftTo: ConstraintId? = null,
    var bottomCenterTo: ConstraintId? = null,
    var bottomRightTo: ConstraintId? = null,
    var centerHorizontalTo: ConstraintId? = null,
    var centerVerticalTo: ConstraintId? = null,
    var outTopLeftTo: ConstraintId? = null,
    var outTopCenterTo: ConstraintId? = null,
    var outTopRightTo: ConstraintId? = null,
    var outCenterLeftTo: ConstraintId? = null,
    var outCenterRightTo: ConstraintId? = null,
    var outBottomLeftTo: ConstraintId? = null,
    var outBottomCenterTo: ConstraintId? = null,
    var outBottomRightTo: ConstraintId? = null,
    var centerTopLeftTo: ConstraintId? = null,
    var centerTopCenterTo: ConstraintId? = null,
    var centerTopRightTo: ConstraintId? = null,
    var centerCenterLeftTo: ConstraintId? = null,
    var centerCenterRightTo: ConstraintId? = null,
    var centerBottomLeftTo: ConstraintId? = null,
    var centerBottomCenterTo: ConstraintId? = null,
    var centerBottomRightTo: ConstraintId? = null,
    var layoutCallback: OnLayoutCallback? = null,
    var paintCallback: OnPaintCallback? = null,
    var percentageTranslate: Boolean = false,
    var minWidth: Int = 0,
    var maxWidth: Int = matchParent,
    var minHeight: Int = 0,
    var maxHeight: Int = matchParent,
    var widthHeightRatio: Double? = null,
    var ratioBaseOnWidth: Boolean? = null,
    var eIndex: Int? = null,
) : LayoutParam<ConstraintLayoutParam>(width = width, height = height) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        other as ConstraintLayoutParam
        if (id != other.id) return false
        if (size != other.size) return false
        if (clickPadding != other.clickPadding) return false
        if (visibility != other.visibility) return false
        if (percentageMargin != other.percentageMargin) return false
        if (margin != other.margin) return false
        if (goneMargin != other.goneMargin) return false
        if (left != other.left) return false
        if (top != other.top) return false
        if (right != other.right) return false
        if (bottom != other.bottom) return false
        if (baseline != other.baseline) return false
        if (zIndex != other.zIndex) return false
        if (translate != other.translate) return false
        if (translateConstraint != other.translateConstraint) return false
        if (widthPercent != other.widthPercent) return false
        if (heightPercent != other.heightPercent) return false
        if (widthPercentageAnchor != other.widthPercentageAnchor) return false
        if (heightPercentageAnchor != other.heightPercentageAnchor) return false
        if (horizontalBias != other.horizontalBias) return false
        if (verticalBias != other.verticalBias) return false
        if (topLeftTo != other.topLeftTo) return false
        if (topCenterTo != other.topCenterTo) return false
        if (topRightTo != other.topRightTo) return false
        if (centerLeftTo != other.centerLeftTo) return false
        if (centerTo != other.centerTo) return false
        if (centerRightTo != other.centerRightTo) return false
        if (bottomLeftTo != other.bottomLeftTo) return false
        if (bottomCenterTo != other.bottomCenterTo) return false
        if (bottomRightTo != other.bottomRightTo) return false
        if (centerHorizontalTo != other.centerHorizontalTo) return false
        if (centerVerticalTo != other.centerVerticalTo) return false
        if (outTopLeftTo != other.outTopLeftTo) return false
        if (outTopCenterTo != other.outTopCenterTo) return false
        if (outTopRightTo != other.outTopRightTo) return false
        if (outCenterLeftTo != other.outCenterLeftTo) return false
        if (outCenterRightTo != other.outCenterRightTo) return false
        if (outBottomLeftTo != other.outBottomLeftTo) return false
        if (outBottomCenterTo != other.outBottomCenterTo) return false
        if (outBottomRightTo != other.outBottomRightTo) return false
        if (centerTopLeftTo != other.centerTopLeftTo) return false
        if (centerTopCenterTo != other.centerTopCenterTo) return false
        if (centerTopRightTo != other.centerTopRightTo) return false
        if (centerCenterLeftTo != other.centerCenterLeftTo) return false
        if (centerCenterRightTo != other.centerCenterRightTo) return false
        if (centerBottomLeftTo != other.centerBottomLeftTo) return false
        if (centerBottomCenterTo != other.centerBottomCenterTo) return false
        if (centerBottomRightTo != other.centerBottomRightTo) return false
        if (layoutCallback != other.layoutCallback) return false
        if (paintCallback != other.paintCallback) return false
        if (percentageTranslate != other.percentageTranslate) return false
        if (minWidth != other.minWidth) return false
        if (maxWidth != other.maxWidth) return false
        if (minHeight != other.minHeight) return false
        if (maxHeight != other.maxHeight) return false
        if (widthHeightRatio != other.widthHeightRatio) return false
        if (ratioBaseOnWidth != other.ratioBaseOnWidth) return false
        if (eIndex != other.eIndex) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (size ?: 0)
        result = 31 * result + clickPadding.hashCode()
        result = 31 * result + visibility.hashCode()
        result = 31 * result + percentageMargin.hashCode()
        result = 31 * result + margin.hashCode()
        result = 31 * result + goneMargin.hashCode()
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (top?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        result = 31 * result + (bottom?.hashCode() ?: 0)
        result = 31 * result + (baseline?.hashCode() ?: 0)
        result = 31 * result + (zIndex ?: 0)
        result = 31 * result + translate.hashCode()
        result = 31 * result + translateConstraint.hashCode()
        result = 31 * result + widthPercent.hashCode()
        result = 31 * result + heightPercent.hashCode()
        result = 31 * result + widthPercentageAnchor.hashCode()
        result = 31 * result + heightPercentageAnchor.hashCode()
        result = 31 * result + horizontalBias.hashCode()
        result = 31 * result + verticalBias.hashCode()
        result = 31 * result + (topLeftTo?.hashCode() ?: 0)
        result = 31 * result + (topCenterTo?.hashCode() ?: 0)
        result = 31 * result + (topRightTo?.hashCode() ?: 0)
        result = 31 * result + (centerLeftTo?.hashCode() ?: 0)
        result = 31 * result + (centerTo?.hashCode() ?: 0)
        result = 31 * result + (centerRightTo?.hashCode() ?: 0)
        result = 31 * result + (bottomLeftTo?.hashCode() ?: 0)
        result = 31 * result + (bottomCenterTo?.hashCode() ?: 0)
        result = 31 * result + (bottomRightTo?.hashCode() ?: 0)
        result = 31 * result + (centerHorizontalTo?.hashCode() ?: 0)
        result = 31 * result + (centerVerticalTo?.hashCode() ?: 0)
        result = 31 * result + (outTopLeftTo?.hashCode() ?: 0)
        result = 31 * result + (outTopCenterTo?.hashCode() ?: 0)
        result = 31 * result + (outTopRightTo?.hashCode() ?: 0)
        result = 31 * result + (outCenterLeftTo?.hashCode() ?: 0)
        result = 31 * result + (outCenterRightTo?.hashCode() ?: 0)
        result = 31 * result + (outBottomLeftTo?.hashCode() ?: 0)
        result = 31 * result + (outBottomCenterTo?.hashCode() ?: 0)
        result = 31 * result + (outBottomRightTo?.hashCode() ?: 0)
        result = 31 * result + (centerTopLeftTo?.hashCode() ?: 0)
        result = 31 * result + (centerTopCenterTo?.hashCode() ?: 0)
        result = 31 * result + (centerTopRightTo?.hashCode() ?: 0)
        result = 31 * result + (centerCenterLeftTo?.hashCode() ?: 0)
        result = 31 * result + (centerCenterRightTo?.hashCode() ?: 0)
        result = 31 * result + (centerBottomLeftTo?.hashCode() ?: 0)
        result = 31 * result + (centerBottomCenterTo?.hashCode() ?: 0)
        result = 31 * result + (centerBottomRightTo?.hashCode() ?: 0)
        result = 31 * result + (layoutCallback?.hashCode() ?: 0)
        result = 31 * result + (paintCallback?.hashCode() ?: 0)
        result = 31 * result + percentageTranslate.hashCode()
        result = 31 * result + minWidth
        result = 31 * result + maxWidth
        result = 31 * result + minHeight
        result = 31 * result + maxHeight
        result = 31 * result + (widthHeightRatio?.hashCode() ?: 0)
        result = 31 * result + (ratioBaseOnWidth?.hashCode() ?: 0)
        result = 31 * result + (eIndex ?: 0)
        return result
    }
}