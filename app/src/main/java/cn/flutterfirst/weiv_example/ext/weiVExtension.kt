package cn.flutterfirst.weiv_example.ext

import android.content.Context
import android.view.View
import android.widget.Button
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.IWeiVExtension
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.wrappers.textview.TextConst

class weiVButton(
    key: Key? = null,
    var text: String = "",
    var textSize: Float = TextConst.defaultTextSize,
    var textColor: Int = TextConst.defaultTextColor,
    var onClick: View.OnClickListener? = null
) :
    LeafRenderWidget<Button>(key), IWeiVExtension {

    override fun createView(context: Context): Button = Button(context)

    override fun doParameter(view: Button, first: Boolean): Button {
        if (view.text != text) {
            view.text = text
        }
        if (view.currentTextColor != textColor) {
            view.setTextColor(textColor)
        }
        if (view.textSize != textSize) {
            view.textSize = textSize
        }
        view.setOnClickListener(onClick)
        return view
    }

    @JavaOnly
    fun wKey(key: Key? = null): weiVButton {
        this.key = key
        return this
    }

    @JavaOnly
    fun wText(text: String = ""): weiVButton {
        this.text = text
        return this
    }

    @JavaOnly
    fun wTextSize(textSize: Float = TextConst.defaultTextSize): weiVButton {
        this.textSize = textSize
        return this
    }

    @JavaOnly
    fun wTextColor(textColor: Int = TextConst.defaultTextColor): weiVButton {
        this.textColor = textColor
        return this
    }

    @JavaOnly
    fun wOnClick(onClick: View.OnClickListener?): weiVButton {
        this.onClick = onClick
        return this
    }

    override fun toString(): String {
        return "weiVButton($text)"
    }
}

fun WeiV.Button(
    key: Key? = null,
    text: String = "",
    textSize: Float = TextConst.defaultTextSize,
    textColor: Int = TextConst.defaultTextColor,
    onClick: View.OnClickListener? = null
) {
    addLeafRenderWidget(
        weiVButton(
            key = key,
            text = text,
            textSize = textSize,
            textColor = textColor,
            onClick = onClick
        )
    )
}

