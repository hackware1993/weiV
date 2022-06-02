package cn.flutterfirst.weiv_example

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.activities.WeiVActivity
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IGlobalWidgetAttachObserver
import cn.flutterfirst.weiv.core.keys.ValueKey
import cn.flutterfirst.weiv.core.others.LayoutParam.Companion.matchParent
import cn.flutterfirst.weiv.core.widgets.*
import cn.flutterfirst.weiv.wrappers.linearlayout.Flex
import cn.flutterfirst.weiv.wrappers.linearlayout.FlexDirection
import cn.flutterfirst.weiv.wrappers.linearlayout.applyFlexLayoutParams
import cn.flutterfirst.weiv.wrappers.textview.Text
import cn.flutterfirst.weiv.wrappers.textview.weiVText
import cn.flutterfirst.weiv_example.embed.WeiVEmbedCounterJavaActivity
import cn.flutterfirst.weiv_example.embed.WeiVEmbedCounterKotlinActivity
import cn.flutterfirst.weiv_example.ext.Button

class WeiVCounterKotlinActivity : WeiVActivity() {
    private var count = 0
    private val maxCount = 10
    private val minCount = 0
    private var url = "https://baidu.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ExtensionMgr.globalWidgetAttachObserver = object : IGlobalWidgetAttachObserver {
            val lightSkinTextColor = Color.BLACK
            val darkSkinTextColor = Color.RED

            override fun onWidgetAttach(widget: Widget<*>): Widget<*> {
                // For java change skin
                if (widget is weiVText<*, *>) {
                    if (SkinManager.isLight) {
                        widget.textColor = lightSkinTextColor
                    } else {
                        widget.textColor = darkSkinTextColor
                    }
                }
                return widget
            }
        }
    }

    // Modular
    fun WeiV.moduleItem(index: Int) {
        Button(text = "This button is from module, $index")
    }

    override fun build(buildCount: Int) = WeiV {
        Flex {
            it.orientation = FlexDirection.VERTICAL

            Flex {
                Button(text = "Add count", enable = count < maxCount, onClick = {
                    setState {
                        count++
                    }
                }).applyFlexLayoutParams {
                    leftMargin = 20
                }

                Button(text = "Sub count", enable = count > minCount, onClick = {
                    setState {
                        count--
                    }
                })

                Button(text = "Change app skin, isLight = ${SkinManager.isLight}", onClick = {
                    SkinManager.changeSkin()
                }).applyFlexLayoutParams {
                    width = 0
                    weight = 1f
                }
            }.applySelfParams {
                gravity = Gravity.CENTER_VERTICAL
            }.applyFlexLayoutParams {
                width = matchParent
                height = 300
            }

            Text(text = "count = $count")

            // Widgets wrapped by Const will not be updated
            Const(key = ValueKey("constArea"), buildCount = buildCount) {
                Text(text = "Widgets wrapped by Const will not be updated, count = $count")
            }

            Stateful(key = ValueKey("stateful"), state = object : StatefulWidget.State() {
                private var innerCount = 0

                override fun build(buildCount: Int) = WeiV {
                    Button(
                        text = "This button maintains state alone, innerCount = $innerCount",
                        onClick = {
                            setState {
                                innerCount++
                            }
                        }
                    )
                }
            })

            repeat(count) {
                moduleItem(it)
            }

            // Modular
            merge {
                WeiV {
                    Text(text = "Merge with outer layer")
                }
            }

            Button(text = "Open weiV counter(Java)", onClick = {
                startActivity(
                    Intent(
                        this@WeiVCounterKotlinActivity,
                        WeiVCounterJavaActivity::class.java
                    )
                )
            })

            Button(text = "Open weiV embed counter(Kotlin)", onClick = {
                startActivity(
                    Intent(
                        this@WeiVCounterKotlinActivity,
                        WeiVEmbedCounterKotlinActivity::class.java
                    )
                )
            })

            Button(text = "Open weiV embed counter(Java)", onClick = {
                startActivity(
                    Intent(
                        this@WeiVCounterKotlinActivity,
                        WeiVEmbedCounterJavaActivity::class.java
                    )
                )
            })

            Button(text = "Change WebView url", onClick = {
                setState {
                    url = "https://flutterfirst.cn"
                }
            })

            XmlView(key = ValueKey("webView"), viewCreator = {
                val webView = WebView(this@WeiVCounterKotlinActivity)
                webView.settings.javaScriptEnabled = true
                webView.layoutParams = ViewGroup.LayoutParams(-1, -1)
                webView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        if (url.startsWith("http")) {
                            return false
                        } else {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                this@WeiVCounterKotlinActivity.startActivity(intent)
                            } catch (e: Exception) {
                            }
                            return true
                        }
                    }
                }
                return@XmlView webView
            }, param = url, onParamChanged = { webView, url, first ->
                if (first) {
                    webView.loadUrl(url!!)
                    webView.setTag(R.id.current_url, url)
                } else {
                    val currentUrl = webView.getTag(R.id.current_url)
                    if (currentUrl != url) {
                        webView.loadUrl(url!!)
                        webView.setTag(R.id.current_url, url)
                    }
                }
            })
        }
    }
}