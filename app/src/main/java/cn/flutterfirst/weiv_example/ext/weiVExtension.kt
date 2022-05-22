package cn.flutterfirst.weiv_example.ext

import android.content.Context
import android.view.View
import android.widget.Button
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.wrappers.textview.TextConst
import cn.flutterfirst.weiv.wrappers.textview.weiVText
import org.json.JSONObject

class weiVButton(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    text: String = "",
    textSize: Float = TextConst.defaultTextSize,
    textColor: Int = TextConst.defaultTextColor,
    var onClick: View.OnClickListener? = null,
    var enable: Boolean = true,
    extra: Any? = null
) :
    weiVText<Button, weiVButton>(key, layoutParam, text, textSize, textColor, extra) {

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

    override fun createView(context: Context) = Button(context)

    override fun doParameter(view: Button, first: Boolean): Button {
        super.doParameter(view, first)
        if (view.isEnabled != enable) {
            view.isEnabled = enable
        }
        view.setOnClickListener(onClick)
        return view
    }

    override fun fromJson(
        jsonObj: JSONObject,
        param: Map<String, Any?>
    ): weiVText<Button, weiVButton> {
        super.fromJson(jsonObj, param)
        enable = (param["enable"] as Boolean?) ?: true
        return this
    }

    override fun parseExtra(extraString: String?) {
        extra = extraString
        if ((extra as String?)?.startsWith("click://") == true) {
            onClick = View.OnClickListener {
            }
        }
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

