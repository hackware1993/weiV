package cn.flutterfirst.weiv.wrappers.linearlayout

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.extension.ISerializableWidget
import cn.flutterfirst.weiv.core.extension.IWeiVExtension
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.*
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import org.json.JSONObject

open class weiVFlex<V : LinearLayout>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    childWidgets: ArrayList<Widget<*>> = ArrayList(),
    open var orientation: Int = FlexDirection.HORIZONTAL,
    open var gravity: Int = Gravity.START or Gravity.TOP,
    extra: Any? = null,
) :
    ContainerRenderWidget<V, weiVFlex<V>>(key, layoutParam, childWidgets, extra), IWeiVExtension,
    ISerializableWidget<weiVFlex<V>> {

    override fun createView(context: Context): V = LinearLayout(context) as V

    @SuppressLint("WrongConstant")
    override fun doParameter(view: V, first: Boolean): V {
        if (view.orientation != orientation) {
            view.orientation = orientation
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (view.gravity != gravity) {
                view.gravity = gravity
            }
        } else {
            view.gravity = gravity
        }
        return view
    }

    override fun processChildLayoutParam(
        parent: V,
        child: View,
        childLayoutParam: LayoutParam<*>?,
    ) {
        assert(childLayoutParam == null || childLayoutParam is FlexLayoutParam)
        var flexParam = childLayoutParam as FlexLayoutParam?
        if (flexParam == null) {
            flexParam = FlexLayoutParam()
        }
        if (flexParam != child.getLayoutParam<FlexLayoutParam>()) {
            child.setLayoutParam(flexParam)
            val lp = LinearLayout.LayoutParams(flexParam.width, flexParam.height)
            lp.gravity = flexParam.gravity
            lp.leftMargin = flexParam.leftMargin
            lp.topMargin = flexParam.topMargin
            lp.rightMargin = flexParam.rightMargin
            lp.bottomMargin = flexParam.bottomMargin
            lp.weight = flexParam.weight
            child.layoutParams = lp
        }
    }

    @JavaOnly
    open fun wOrientation(orientation: Int): weiVFlex<V> {
        this.orientation = orientation
        return this
    }

    @JavaOnly
    open fun wGravity(gravity: Int): weiVFlex<V> {
        this.gravity = gravity
        return this
    }

    override fun fromJson(jsonObj: JSONObject, param: Map<String, Any?>): weiVFlex<V> {
        orientation = (param["orientation"] as Int?) ?: FlexDirection.HORIZONTAL
        return this
    }

    override fun toString(): String {
        return "weiVFlex(orientation=$orientation, childCount=${childWidgets!!.size})"
    }
}

var creator: IExtensionCreator<weiVFlex<*>>? = null

@KotlinOnly
fun LeafRenderWidget<*, *>.applyFlexLayoutParams(block: FlexLayoutParam.() -> Unit) {
    layoutParam = FlexLayoutParam()
    block(layoutParam as FlexLayoutParam)
}

@KotlinOnly
fun WeiV.Flex(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    orientation: Int = FlexDirection.HORIZONTAL,
    gravity: Int = Gravity.START or Gravity.TOP,
    extra: Any? = null,
    block: WeiV.(widget: weiVFlex<*>) -> Unit,
): weiVFlex<*> {
    if (creator == null) {
        creator = ExtensionMgr.getExtension(InternalWidgetDesc.FLEX)
    }

    return addContainerRenderWidget(
        creator!!.createWidget(
            key,
            layoutParam,
            ArrayList<Widget<*>>(),
            orientation,
            gravity,
            extra
        ), block
    )
}