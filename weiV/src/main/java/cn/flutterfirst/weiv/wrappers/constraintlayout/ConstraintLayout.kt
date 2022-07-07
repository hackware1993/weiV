package cn.flutterfirst.weiv.wrappers.constraintlayout

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import cn.flutterfirst.weiv.core.others.LayoutParam.Companion.matchParent
import cn.flutterfirst.weiv.core.others.LayoutParam.Companion.wrapContent
import cn.flutterfirst.weiv.core.others.getLayoutParam
import kotlin.math.min


// ConstraintLayout design for weiV
class ConstraintLayout(context: Context) : ViewGroup(context) {
    var childrenList = ArrayList<View>()

    // Self config
    var showLayoutPerformanceOverlay: Boolean = false
    var showHelperWidgets: Boolean = false
    var showClickArea: Boolean = false
    var showZIndex: Boolean = false
    var showChildDepth: Boolean = false
    var debugPrintConstraints: Boolean = false
    var _width = matchParent
    var _height = matchParent
    var _size: Int? = null

    //
    var needsRecalculateConstraints = true
    var needsLayout = true
    var needsReorderPaintingOrder = true
    var needsPaint = true
    var needsReorderEventOrder = true

    var size: Size? = null
    val helperNodeMap: Map<ConstraintId, ConstrainedNode> = HashMap()

    init {
    }

    override fun addView(child: View?, index: Int, params: LayoutParams?) {
        super.addView(child, index, params)
        fillChildrenList()
    }

    override fun removeAllViews() {
        super.removeAllViews()
        fillChildrenList()
        markNeedsRecalculateConstraints()
    }

    override fun removeView(view: View?) {
        super.removeView(view)
        fillChildrenList()
        markNeedsRecalculateConstraints()
    }

    override fun removeViewAt(index: Int) {
        super.removeViewAt(index)
        fillChildrenList()
        markNeedsRecalculateConstraints()
    }

    private fun fillChildrenList() {
        childrenList.clear()
        repeat(childCount) {
            childrenList.add(getChildAt(it))
        }
    }

    private fun constrainSize(originSize: Int, measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.AT_MOST -> {
                min(size, originSize)
            }
            MeasureSpec.EXACTLY -> {
                size
            }
            else -> {
                originSize
            }
        }
    }

    private fun debugCheckIds(): Boolean {
        val declaredIdSet = HashSet<ConstraintId>()
        declaredIdSet.add(CL.parent)

        if (helperNodeMap.isNotEmpty()) {
            for (key in helperNodeMap.keys) {
                if (!declaredIdSet.add(key)) {
                    throw ConstraintLayoutException("Duplicate id in ConstraintLayout.")
                }
            }
        }

        val referencedIdSet = HashSet<ConstraintId>()
        childrenList.forEach { view ->
            val constraintLayoutParam = view.getLayoutParam<ConstraintLayoutParam>()!!

            if (constraintLayoutParam.id != null) {
                if (!declaredIdSet.add(constraintLayoutParam.id!!)) {
                    throw ConstraintLayoutException("Duplicate id in ConstraintLayout.")
                }
            }

            if (constraintLayoutParam.left != null) {
                referencedIdSet.add(constraintLayoutParam.left!!.id!!)
            }

            if (constraintLayoutParam.top != null) {
                referencedIdSet.add(constraintLayoutParam.top!!.id!!)
            }

            if (constraintLayoutParam.right != null) {
                referencedIdSet.add(constraintLayoutParam.right!!.id!!)
            }

            if (constraintLayoutParam.bottom != null) {
                referencedIdSet.add(constraintLayoutParam.bottom!!.id!!)
            }

            if (constraintLayoutParam.baseline != null) {
                referencedIdSet.add(constraintLayoutParam.baseline!!.id!!)
            }

            // TODO
        }

        // TODO

        // The id used by all constraints must be defined
        // TODO

        return true
    }

    private fun buildConstrainedNodeTrees(selfSizeConfirmed: Boolean): Map<ConstraintId, ConstrainedNode> {
        val nodesMap = LinkedHashMap<ConstraintId, ConstrainedNode>()
        val parentNode = ConstrainedNode()
        parentNode.nodeId = CL.parent
        parentNode.depth = if (selfSizeConfirmed) 0 else childCount + 1
        parentNode.notLaidOut = false

        if (!selfSizeConfirmed) {
            nodesMap[CL.parent] = parentNode
        }

        fun getConstrainedNodeForChild(id: ConstraintId, childIndex: Int? = null): ConstrainedNode {
            if (id == CL.parent) {
                return parentNode
            }

            var localId = id
            if (id is RelativeConstraintId) {
                val targetIndex = childIndex!! + id.siblingIndexOffset;
                localId = IndexConstraintId(targetIndex);
            } else if (id is IndexConstraintId) {
                if (id.siblingIndex < 0) {
                    localId = IndexConstraintId(childCount + id.siblingIndex);
                }
            }

            var node = nodesMap[localId]
            if (node == null) {
                node = ConstrainedNode()
                node.nodeId = localId
                nodesMap[localId] = node
            }

            return node
        }

        // TODO

        return nodesMap
    }

    @SuppressLint("Range", "DrawAllocation", "AssertionSideEffect")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var resolvedWidth: Int
        if (_width >= 0) {
            resolvedWidth = constrainSize(_width, widthMeasureSpec)
        } else {
            resolvedWidth = if (_width == matchParent) {
                when (MeasureSpec.getMode(widthMeasureSpec)) {
                    MeasureSpec.AT_MOST -> {
                        wrapContent
                    }
                    MeasureSpec.EXACTLY -> {
                        MeasureSpec.getSize(widthMeasureSpec)
                    }
                    else -> {
                        wrapContent
                    }
                }
            } else {
                wrapContent
            }
        }

        var resolvedHeight: Int
        if (_height >= 0) {
            resolvedHeight = constrainSize(_height, heightMeasureSpec)
        } else {
            resolvedHeight = if (_width == matchParent) {
                when (MeasureSpec.getMode(heightMeasureSpec)) {
                    MeasureSpec.AT_MOST -> {
                        wrapContent
                    }
                    MeasureSpec.EXACTLY -> {
                        MeasureSpec.getSize(heightMeasureSpec)
                    }
                    else -> {
                        wrapContent
                    }
                }
            } else {
                wrapContent
            }
        }

        var selfSizeConfirmed = false
        if (resolvedWidth != wrapContent && resolvedHeight != wrapContent) {
            size = Size(resolvedWidth, resolvedHeight)
            selfSizeConfirmed = true
        } else if (resolvedWidth != wrapContent) {
            size = Size(resolvedWidth, 0)
        } else if (resolvedHeight != wrapContent) {
            size = Size(0, resolvedHeight)
        }

        if (needsRecalculateConstraints) {
            assert(debugCheckIds())

        }

        childrenList.forEach { view ->
            val layoutParam = view.getLayoutParam<ConstraintLayoutParam>()!!
            val widthSpec = MeasureSpec.makeMeasureSpec(layoutParam.width, MeasureSpec.EXACTLY)
            val heightSpec = MeasureSpec.makeMeasureSpec(layoutParam.height, MeasureSpec.EXACTLY)
            view.measure(widthSpec, heightSpec)
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
            MeasureSpec.getSize(heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        childrenList.forEach { view ->
            view.layout(300, 300, 300 + view.measuredWidth, 300 + view.measuredHeight)
        }
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