package cn.flutterfirst.weiv.core.widgets

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam

class XmlViewWidget<VIEW : View, PARAM>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    var param: PARAM? = null,
    var viewCreator: () -> VIEW,
    var onParamChanged: (view: VIEW, param: PARAM?, first: Boolean) -> Unit,
    extra: Any? = null
) : LeafRenderWidget<VIEW, XmlViewWidget<VIEW, PARAM>>(key, layoutParam, extra = extra) {

    override fun createView(context: Context) = viewCreator()

    override fun doParameter(view: VIEW, first: Boolean): VIEW {
        onParamChanged(view, param, first)
        return view
    }

    @JavaOnly
    fun wParam(param: PARAM?): XmlViewWidget<VIEW, PARAM> {
        this.param = param
        return this
    }

    @JavaOnly
    fun wViewCreator(viewCreator: () -> VIEW): XmlViewWidget<VIEW, PARAM> {
        this.viewCreator = viewCreator
        return this
    }

    @JavaOnly
    fun wOnParamChanged(onParamChanged: (view: VIEW, param: PARAM?, first: Boolean) -> Unit): XmlViewWidget<VIEW, PARAM> {
        this.onParamChanged = onParamChanged
        return this
    }
}

@KotlinOnly
fun <VIEW : View, PARAM> WeiV.XmlView(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    param: PARAM? = null,
    viewCreator: () -> VIEW,
    onParamChanged: (view: VIEW, param: PARAM?, first: Boolean) -> Unit,
    extra: Any? = null
) {
    addLeafRenderWidget(
        XmlViewWidget(
            key,
            layoutParam,
            param,
            viewCreator,
            onParamChanged,
            extra
        )
    )
}