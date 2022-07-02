package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.content.Context
import android.view.View
import android.view.ViewGroup

// ConstraintLayout design for weiV
class ConstraintLayout(context: Context) : ViewGroup(context) {
    var childrenList = ArrayList<View>()

    var needsRecalculateConstraints = true
    var needsLayout = true
    var needsReorderPaintingOrder = true
    var needsPaint = true
    var needsReorderEventOrder = true

    init {
    }

    override fun addView(child: View?, index: Int, params: LayoutParams?) {
        super.addView(child, index, params)
    }

    override fun removeAllViews() {
        super.removeAllViews()
        markNeedsRecalculateConstraints()
    }

    override fun removeView(view: View?) {
        super.removeView(view)
        markNeedsRecalculateConstraints()
    }

    override fun removeViewAt(index: Int) {
        super.removeViewAt(index)
        markNeedsRecalculateConstraints()
    }

    private fun fillChildrenList() {
        childrenList.clear()
        repeat(childCount) {
            childrenList.add(getChildAt(it))
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        getChildAt(0).measure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(getChildAt(0).measuredWidth, getChildAt(0).measuredHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        getChildAt(0).layout(0, 0, getChildAt(0).measuredWidth, getChildAt(0).measuredHeight)
    }

    fun markNeedsRecalculateConstraints() {
        needsRecalculateConstraints = true;
        needsReorderPaintingOrder = true;
        needsReorderEventOrder = true;
    }

    fun markNeedsLayout() {
    }

    fun markNeedsReorderPaintingOrder() {
    }

    fun markNeedsPaint() {
    }

    fun markNeedsReorderEventOrder() {
    }
}