package cn.flutterfirst.weiv.ext

import android.content.Context
import android.widget.Button
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget

class weiVButton(
    override var key: Key? = null,
    var text: String? = null,
    var textSize: Float? = null,
    var textColor: Int? = null
) :
    LeafRenderWidget<Button>(key) {

    override fun createView(context: Context): Button = Button(context)

    override fun doParameter(view: Button, first: Boolean): Button {
        if (view.text != text) {
            view.text = text
        }
        if (view.currentTextColor != textColor) {
            view.setTextColor(textColor!!)
        }
        if (view.textSize != textSize) {
            view.textSize = textSize!!
        }
        return view
    }
}

fun WeiV.Button(
    key: Key? = null,
    text: String? = null,
    textSize: Float? = null,
    textColor: Int? = null
) {
    addLeafRenderWidget(
        weiVButton(
            key = key,
            text = text,
            textSize = textSize,
            textColor = textColor
        )
    )
}

