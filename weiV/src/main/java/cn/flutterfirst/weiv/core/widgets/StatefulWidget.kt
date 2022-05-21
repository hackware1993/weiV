package cn.flutterfirst.weiv.core.widgets

import android.content.Context
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.Build
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.views.WeiVView

class StatefulWidget(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    var state: State,
    var param: HashMap<String, Any?>? = null,
) : LeafRenderWidget<StatefulWidget.StatefulView, StatefulWidget>(key, layoutParam) {

    override fun createView(context: Context): StatefulView {
        state.param = param
        return StatefulView(context, state)
    }

    override fun doParameter(view: StatefulView, first: Boolean): StatefulView {
        if (!first) {
            view.state.didUpdateParam(param)
        }
        return view
    }

    @JavaOnly
    fun wState(state: State): StatefulWidget {
        this.state = state
        return this
    }

    @JavaOnly
    fun wParam(param: HashMap<String, Any?>?): StatefulWidget {
        this.param = param
        return this
    }

    class StatefulView(context: Context, state: State) : WeiVView(context) {
        val state: State

        init {
            state.view = this
            state.initState(context)
            this.state = state
        }

        override fun onDetachedFromWindow() {
            super.onDetachedFromWindow()
            state.dispose()
        }

        override fun build(): WeiV {
            return state.build()
        }
    }

    abstract class State {
        var param: HashMap<String, Any?>? = null
        lateinit var view: StatefulView

        open fun initState(context: Context) {
        }

        abstract fun build(): WeiV

        open fun didUpdateParam(param: HashMap<String, Any?>?) {
            this.param = param
        }

        @KotlinOnly
        fun setState(block: () -> Unit) {
            block()
            view.update(build())
        }

        @JavaOnly
        fun setState(build: Build) {
            build.build()
            view.update(build())
        }

        open fun dispose() {
        }
    }
}

@KotlinOnly
fun WeiV.Stateful(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    state: StatefulWidget.State,
    param: HashMap<String, Any?>? = null,
): StatefulWidget {
    return addLeafRenderWidget(
        StatefulWidget(
            key = key,
            layoutParam = layoutParam,
            state = state,
            param = param
        )
    )
}