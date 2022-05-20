package cn.flutterfirst.weiv.wrappers.linearlayout

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.extension.ISerializableWidget
import cn.flutterfirst.weiv.core.extension.IWeiVExtension
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import org.json.JSONObject

open class weiVFlex<T : LinearLayout>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    childWidgets: ArrayList<Widget<*>> = ArrayList(),
    open var orientation: Int = FlexDirection.HORIZONTAL,
) :
    ContainerRenderWidget<T, weiVFlex<T>>(key, layoutParam, childWidgets), IWeiVExtension,
    ISerializableWidget<weiVFlex<T>> {

    override fun createView(context: Context): T = LinearLayout(context) as T

    @SuppressLint("WrongConstant")
    override fun doParameter(view: T, first: Boolean): T {
        if (view.orientation != orientation) {
            view.orientation = orientation
        }
        return view
    }

    @JavaOnly
    open fun wOrientation(orientation: Int): weiVFlex<T> {
        this.orientation = orientation
        return this
    }

    override fun fromJson(jsonObj: JSONObject, param: Map<String, Any?>): weiVFlex<T> {
        orientation = (param["orientation"] as Int?) ?: FlexDirection.HORIZONTAL
        return this
    }

    override fun toString(): String {
        return "weiVFlex($orientation)"
    }
}

var creator: IExtensionCreator<weiVFlex<*>>? = null

@KotlinOnly
fun WeiV.Flex(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    orientation: Int = FlexDirection.HORIZONTAL,
    block: WeiV.(widget: weiVFlex<*>) -> Unit
): weiVFlex<*> {
    if (creator == null) {
        creator = ExtensionMgr.getExtension(InternalWidgetDesc.FLEX)
    }

    return addContainerRenderWidget(
        creator!!.createWidget(
            key,
            layoutParam,
            ArrayList<Widget<*>>(),
            orientation
        ), block
    )
}