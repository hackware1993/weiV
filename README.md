# weiV

[简体中文](https://github.com/hackware1993/weiV/blob/master/README_CN.md)

![wave.webp](https://github.com/hackware1993/weiV/blob/master/wave.webp?raw=true)

weiV (pronounced the same as wave), a new declarative UI development framework based on the Android
View system. The core source files are only 30 KB.

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
5. It is written in Kotlin but supports Java friendly

**No one wants to overturn their past experience with the View system, Compose's design is too
bad.**

# Progress

At present, the definition of DSL has been completed, and it can be parsed into a Widget tree. The
DSL style is as follows:

Kotlin style:

```kotlin
class WeiVCounterKotlinActivity : WeiVActivity() {
    private var count = 0
    private val maxCount = 10
    private val minCount = 0

    override fun build() = WeiV {
        Flex {
            it.orientation = FlexDirection.VERTICAL

            Button(text = "Add count", enable = count < maxCount, onClick = {
                setState {
                    count++
                }
            })

            Button(text = "Sub count", enable = count > minCount, onClick = {
                setState {
                    count--
                }
            })

            Text(text = "count = $count")
        }
    }
}
```

Java style:

```java
public class WeiVCounterJavaActivity extends BaseWeiVJavaActivity {
    private int count = 0;
    private int maxCount = 10;
    private int minCount = 0;

    @Override
    public WeiV build() {
        return WeiV(() -> {
            Flex((it) -> {
                it.wOrientation(FlexDirection.VERTICAL);

                Button().wText("Add count").wEnable(count < maxCount).wOnClick(v -> {
                    setState(() -> {
                        count++;
                    });
                });

                Button().wText("Sub count").wEnable(count > minCount).wOnClick(v -> {
                    setState(() -> {
                        count--;
                    });
                });

                Text().wText("count = " + count);
            });
        });
    }
}
```

![effect.gif](https://github.com/hackware1993/weiV/blob/master/effect.gif?raw=true)

weiV is extensible. It will have all the commonly used widgets built in, which are wrappers for the
system View. But for third-party libraries, you need to write extensions, which are extremely simple
to write. For example, the extension for Button is as follows:

```kotlin
class weiVButton @JvmOverloads constructor(
    key: Key? = null,
    var text: String = "",
    var textSize: Float = TextConst.defaultTextSize,
    var textColor: Int = TextConst.defaultTextColor,
    var onClick: View.OnClickListener? = null,
    var enable: Boolean = true
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
        if (view.isEnabled != enable) {
            view.isEnabled = enable
        }
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

    @JavaOnly
    fun wEnable(enable: Boolean = true): weiVButton {
        this.enable = enable
        return this
    }

    override fun toString(): String {
        return "weiVButton(text = $text, enable = $enable)"
    }
}

@KotlinOnly
fun WeiV.Button(
    key: Key? = null,
    text: String = "",
    textSize: Float = TextConst.defaultTextSize,
    textColor: Int = TextConst.defaultTextColor,
    onClick: View.OnClickListener? = null,
    enable: Boolean = true
) {
    addLeafRenderWidget(
        weiVButton(
            key = key,
            text = text,
            textSize = textSize,
            textColor = textColor,
            onClick = onClick,
            enable = enable
        )
    )
}
```

weiV is based on the View system, so it can be embedded anywhere in the View tree. You can embed
Flutter and Compose in weiV, or embed weiV in Compose and Flutter. It is recommended to embed weiV
on top of Compose to improve Compose performance. 😀

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
