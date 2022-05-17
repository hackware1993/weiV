# weiV(pronounced the same as wave)

![wave.webp](https://github.com/hackware1993/weiV/blob/master/wave.webp?raw=true)

```kotlin
if ("weiV" == "View".reversed()) {
    Log.d(
        "weiV",
        "It means Inversion of Control, you should always manipulate the UI 's description Widget directly instead of the View."
    )
}
```

It has the following advantages:

1. Declarative UI writing doubles the efficiency of native development
2. Meets or exceeds the performance of the View system
    1. I ported my Flutter ConstraintLayout to Android, relying on its advanced layout algorithm,
       without introducing Intrinsic Measurement, so that the child elements in the View tree will
       only be laid out once in any case, making arbitrary nesting does not cause performance
       issues. Even if each level in the View tree is a mix of wrap_content and match_parent
    2. xml will be discarded
3. All your existing View system experience will be retained
4. All existing UI components will be reused

[简体中文](https://github.com/hackware1993/weiV/blob/master/README_CN.md)

# Progress

At present, the definition of DSL has been completed, and it can be parsed into a Widget tree. The
DSL style is as follows:

```kotlin
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
```

weiV is extensible. It will have all the commonly used widgets built in, which are wrappers for the
system View. But for third-party libraries, you need to write extensions, which are extremely simple
to write. For example, the extension for Button is as follows:

```kotlin
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
    currentWidgetContext.add(
        weiVButton(
            key = key,
            text = text,
            textSize = textSize,
            textColor = textColor
        )
    )
}
```

It is expected that weiV will be able to actually run soon. But there is still a long way to go.
First, you need to transplant Flutter ConstraintLayout, and secondly, you will probably rewrite a
weiV version of RecyclerView to support simple list usage like Flutter, without writing Adapter.

Subscribe to my WeChat official account to get the latest news of weiV. Follow-up will also share
some high-quality, unique, and thoughtful Flutter and Android technical articles.
![official_account.webp](https://github.com/hackware1993/weiV/blob/master/official_account.webp?raw=true)

# Support me

If it helps you a lot, consider sponsoring me a cup of milk tea, or giving a star. Your support is
the driving force for me to continue to maintain.
[Paypal](https://www.paypal.com/paypalme/hackware1993)
![support.webp](https://github.com/hackware1993/weiV/blob/master/support.webp?raw=true)

# Contact

hackware1993@gmail.com

# License

```
MIT License

Copyright (c) 2022 hackware1993

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
