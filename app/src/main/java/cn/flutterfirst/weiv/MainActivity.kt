package cn.flutterfirst.weiv

import android.widget.LinearLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.WeiVActivity
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.ext.Button

class MainActivity : WeiVActivity() {
    private var switch = false
    private var text = "weiV"

    override fun build(): Widget {
        return WeiV {
            Flex(orientation = LinearLayout.HORIZONTAL) {
                Text(text = text)
                Text(text = text)
                Flex(
                    key = Key(),
                    orientation = LinearLayout.VERTICAL
                ) {
                    if (switch) {
                        Text(text = text)
                    } else {
                        Text(text = text)
                    }
                    Button(text = text)
                }
            }
        }
    }
}