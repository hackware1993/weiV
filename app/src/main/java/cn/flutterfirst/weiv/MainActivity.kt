package cn.flutterfirst.weiv

import Flex
import Text
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
                Text(text = text, textSize = 14f)
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
                    repeat(10) {
                        Button(text = text + it)
                    }
                    for (i in 1..5) {
                        Text(text = text + i)
                    }
                }
            }
        }
    }
}