package cn.flutterfirst.weiv.core.dynamic

import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebView

class JsContext(context: Context, path: String) {
    val webView = WebView(context)
    var callback: IOnJsCall? = null

    inner class JsInterface {
        @JavascriptInterface
        fun callMethod(methodName: String, vararg param: Any?) {
            callback?.onJsCallNativeMethod(methodName, param)
        }
    }

    interface IOnJsCall {
        fun onJsCallNativeMethod(methodName: String, vararg param: Any?)
    }

    init {
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(JsInterface(), "weiV")
        webView.loadUrl(path)
    }

    fun callJsMethodASync(
        methodName: String,
        resultCallback: ValueCallback<String>,
        vararg param: Any?
    ) {
        webView.evaluateJavascript("javascript:$methodName()", resultCallback)
    }

    fun destroy() {
        webView.destroy()
    }
}