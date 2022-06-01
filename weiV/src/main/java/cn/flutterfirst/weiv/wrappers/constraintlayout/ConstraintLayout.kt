package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.content.Context
import android.view.ViewGroup

// ConstraintLayout design for weiV
class ConstraintLayout(context: Context) : ViewGroup(context) {

    init {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        getChildAt(0).measure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(getChildAt(0).measuredWidth, getChildAt(0).measuredHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        getChildAt(0).layout(0, 0, getChildAt(0).measuredWidth, getChildAt(0).measuredHeight)
    }
}