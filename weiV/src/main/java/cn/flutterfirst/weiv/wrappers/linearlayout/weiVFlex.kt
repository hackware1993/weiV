package cn.flutterfirst.weiv.wrappers.linearlayout

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.extension.IWeiVExtension
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc

open class weiVFlex<T : LinearLayout>(
    key: Key? = null,
    childWidgets: ArrayList<Widget> = ArrayList(),
    open var orientation: Int = FlexDirection.HORIZONTAL,
) :
    ContainerRenderWidget<T>(key, childWidgets), IWeiVExtension {

    override fun createView(context: Context): T = LinearLayout(context) as T

    @SuppressLint("WrongConstant")
    override fun doParameter(view: T, first: Boolean): T {
        if (view.orientation != orientation) {
            view.orientation = orientation
        }
        return view
    }

    @JavaOnly
    open fun wKey(key: Key? = null): weiVFlex<T> {
        this.key = key
        return this
    }

    @JavaOnly
    open fun wOrientation(orientation: Int = FlexDirection.HORIZONTAL): weiVFlex<T> {
        this.orientation = orientation
        return this
    }

    override fun toString(): String {
        return "weiVFlex($orientation)"
    }
}

var creator: IExtensionCreator<LeafRenderWidget<*>>? = null

@KotlinOnly
fun WeiV.Flex(
    key: Key? = null,
    orientation: Int = FlexDirection.HORIZONTAL,
    block: WeiV.(widget: weiVFlex<LinearLayout>) -> Unit
) {
    if (creator == null) {
        creator = ExtensionMgr.getExtension(InternalWidgetDesc.FLEX)
    }

    addContainerRenderWidget(
        creator!!.createWidget(
            key,
            ArrayList<Widget>(),
            orientation
        ) as weiVFlex<LinearLayout>, block
    )
}