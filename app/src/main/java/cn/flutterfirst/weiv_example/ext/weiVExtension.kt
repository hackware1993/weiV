package cn.flutterfirst.weiv_example.ext

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Button
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ISerializableWidget
import cn.flutterfirst.weiv.core.extension.IWeiVExtension
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.wrappers.textview.TextConst
import org.json.JSONObject

class weiVButton(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    var text: String = "",
    var textSize: Float = TextConst.defaultTextSize,
    var textColor: Int = TextConst.defaultTextColor,
    var onClick: View.OnClickListener? = null,
    var enable: Boolean = true,
    extra: Any? = null
) :
    LeafRenderWidget<Button, weiVButton>(key, layoutParam, extra = extra), IWeiVExtension,
    ISerializableWidget<weiVButton> {

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
        if (view.isEnabled != enable) {
            view.isEnabled = enable
        }
        return view
    }

    @JavaOnly
    fun wText(text: String): weiVButton {
        this.text = text
        return this
    }

    @JavaOnly
    fun wTextSize(textSize: Float): weiVButton {
        this.textSize = textSize
        return this
    }

    @JavaOnly
    fun wTextColor(textColor: Int): weiVButton {
        this.textColor = textColor
        return this
    }

    @JavaOnly
    fun wOnClick(onClick: View.OnClickListener?): weiVButton {
        this.onClick = onClick
        return this
    }

    @JavaOnly
    fun wEnable(enable: Boolean): weiVButton {
        this.enable = enable
        return this
    }

    override fun fromJson(jsonObj: JSONObject, param: Map<String, Any?>): weiVButton {
        text = (param["text"] as String?) ?: ""
        textSize = ((param["textSize"] as Double?) ?: TextConst.defaultTextSize).toFloat()
        val colorStr = param["textColor"] as String?
        textColor = if (colorStr != null && colorStr.startsWith("#")) {
            Color.parseColor(colorStr)
        } else {
            TextConst.defaultTextColor
        }
        return this
    }

    override fun toString(): String {
        return "weiVButton(text='$text', textSize=$textSize, textColor=$textColor, enable=$enable)"
    }
}

@KotlinOnly
fun WeiV.Button(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    text: String = "",
    textSize: Float = TextConst.defaultTextSize,
    textColor: Int = TextConst.defaultTextColor,
    onClick: View.OnClickListener? = null,
    enable: Boolean = true,
    extra: Any? = null
): weiVButton {
    return addLeafRenderWidget(
        weiVButton(
            key,
            layoutParam,
            text,
            textSize,
            textColor,
            onClick,
            enable,
            extra
        )
    )
}

