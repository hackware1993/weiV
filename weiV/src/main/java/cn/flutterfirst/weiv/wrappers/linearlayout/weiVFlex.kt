package cn.flutterfirst.weiv.wrappers.linearlayout

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

class weiVFlex(
    key: Key? = null,
    childWidgets: ArrayList<Widget> = ArrayList(),
    var orientation: Int = FlexDirection.HORIZONTAL,
) :
    ContainerRenderWidget<LinearLayout>(key, childWidgets) {

    override fun createView(context: Context): LinearLayout = LinearLayout(context)

    @SuppressLint("WrongConstant")
    override fun doParameter(view: LinearLayout, first: Boolean): LinearLayout {
        if (view.orientation != orientation) {
            view.orientation = orientation
        }
        return view
    }

    @JavaOnly
    fun wKey(key: Key? = null): weiVFlex {
        this.key = key
        return this
    }

    @JavaOnly
    fun wOrientation(orientation: Int = FlexDirection.HORIZONTAL): weiVFlex {
        this.orientation = orientation
        return this
    }

    override fun toString(): String {
        return "weiVFlex($orientation)"
    }
}

@KotlinOnly
fun WeiV.Flex(
    key: Key? = null,
    orientation: Int = FlexDirection.HORIZONTAL,
    block: WeiV.(widget: weiVFlex) -> Unit
) {
    addContainerRenderWidget(weiVFlex(key = key, ArrayList(), orientation = orientation), block)
}