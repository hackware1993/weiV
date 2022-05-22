package cn.flutterfirst.weiv.core.widgets

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam

class XmlViewWidget<V : View, P>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    var param: P? = null,
    var viewCreator: () -> V,
    var onParamChanged: (view: V, param: P?, first: Boolean) -> Unit,
    extra: Any? = null
) : ContainerRenderWidget<V, XmlViewWidget<V, P>>(key, layoutParam, ArrayList(), extra) {

    override fun createView(context: Context) = viewCreator()

    override fun doParameter(view: V, first: Boolean): V {
        onParamChanged(view, param, first)
        return view
    }

    @JavaOnly
    fun wParam(param: P?): XmlViewWidget<V, P> {
        this.param = param
        return this
    }

    @JavaOnly
    fun wViewCreator(viewCreator: () -> V): XmlViewWidget<V, P> {
        this.viewCreator = viewCreator
        return this
    }

    @JavaOnly
    fun wOnParamChanged(onParamChanged: (view: V, param: P?, first: Boolean) -> Unit): XmlViewWidget<V, P> {
        this.onParamChanged = onParamChanged
        return this
    }
}

@KotlinOnly
fun <V : View, P> WeiV.XmlView(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    param: P? = null,
    viewCreator: () -> V,
    onParamChanged: (view: V, param: P?, first: Boolean) -> Unit,
    extra: Any? = null
): XmlViewWidget<*, *> {
    return addLeafRenderWidget(
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