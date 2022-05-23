# weiV

[简体中文](https://github.com/hackware1993/weiV/blob/master/README_CN.md)

![wave.webp](https://github.com/hackware1993/weiV/blob/master/wave.webp?raw=true)

weiV (pronounced the same as wave), a new declarative UI development framework based on the Android
View system. The core source files are only 35 KB.

[Download weiV Counter apk](https://github.com/hackware1993/weiV/blob/master/weiV_Counter.apk)
Building the counter release obfuscated package took only 4 seconds and was 41 KB in size.
![weiV_Counter_effect.webp](https://github.com/hackware1993/weiV/blob/master/weiV_Counter_effect.webp?raw=true)

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
6. At present, the initial support for real-time effective dynamization has begun. You can issue JS,
   use JS to write page logic, and generate JSON describing the Widget tree and pass it to the
   native, and the native uses a non-reflection method to convert it into a real Widget tree and
   render. I might consider implementing a declarative API in JS later

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

    override fun build(buildCount: Int) = WeiV {
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
    public WeiV build(int buildCount) {
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

# The following functions have been implemented and are still under rapid iteration

1. Scalable
    1. Easily adapt third-party UI components to implement declarative APIs
    2. The internal implementation can be replaced. For example, Text is rendered with TextView by
       default. You can replace it with AppCompatTextView
2. Provides JsContext based on WebView, which can be easily dynamic with WeiVParser, and will
   continue to be enhanced later
3. Friendly, full support for Java
4. Provides XmlViewWidget that allows you to inline all existing views and implement a declarative
   API without writing extensions
5. Provides StatefulWidget to achieve separate management of subtree state, with it you no longer
   need Fragment
6. Provides WeiVView, WeiVJavaView to embed weiV anywhere
7. ConstWidget is provided to achieve the same effect as Flutter const Widget

Kotlin:

```kotlin
class WeiVCounterKotlinActivity : WeiVActivity() {
    override fun build(buildCount: Int) = WeiV {
        // Subtree wrapped with Const will not be updated, Lambda will only execute once
        Const(buildCount = buildCount) {
            Text(text = "Widgets wrapped by Const will not be updated, count = $count")
        }
    }
}
```

Java:

```java
class WeiVCounterJavaActivity extends WeiVJavaActivity {
    public WeiV build(int buildCount) {
        // Subtree wrapped with Const will not be updated, Lambda will only execute once
        Const(buildCount, () -> {
            Text().wText("Widgets wrapped by Const will not be updated, count = " + count);
        });
    }
}
```

8. Provides a Hook for global Widget creation and update, which can easily achieve skinning and
   night mode
9. Provides a UI modular solution

Near-term plans:

1. Improve the core logic of the declarative architecture to support efficient updating of the three
   trees of Widget, Element, and View
2. Porting Flutter ConstraintLayout to achieve the above performance goals and bring unprecedented
   development efficiency and experience, ConstraintLayout provided by Compose Haven't gotten rid of
   imperative thinking, it's really inefficient to use
3. Rewrite common core controls such as RecyclerView and ViewPager2 to implement declarative API
4. Wrap all system built-in common components to implement declarative API, some may be rewritten
5. Support animation in an extremely simple way, property animation is no longer suitable under the
   declarative system
6. Develop layout preview, this may be a little troublesome
7. Porting Flutter PVState to provide a more lightweight and easy-to-use state management solution
   under a declarative UI
8. Built-in lightweight QuickJS engine, use pure JS (abandon HTML, CSS) to develop cross-platform
   apps, get rid of the dependence on WebView and achieve high-performance synchronous calls between
   native and JS
9. **Implements stateful hot reloading for Android. By developing an Android Studio plugin, when you
   change the code, the plugin generates the JSON of the widget tree according to the latest code
   and sends it to the App through ADB. The App restores the JSON to the real widget tree and
   re-renders the UI. The state of the entire process application is preserved. This will further
   improve the efficiency of Android development**

It is expected that all the code will be written, and the amount of code will be around 30,000
lines.

The ultimate goal: the only declarative UI development framework under Java, a better declarative UI
development framework under Kotlin than Compose.

Some might say that Compose supports cross-platform, I think cross-platform you should prefer
Flutter over Compose.

Flutter should be prioritized at all times for developing UI parts. Even if there is no cross-end
requirement, when performance is not a problem, the efficiency it brings to you is doubled. Maybe
80% of UI will be built with Flutter in the future. If you gave up Flutter because of nesting hell,
now is the time to pick it up again, because my Flutter ConstraintLayout has eliminated nesting
hell.

weiV is extensible. It will have all the commonly used widgets built in, which are wrappers for the
system View. But for third-party libraries, you need to write extensions, which are extremely simple
to write. For example, the extension for Button is as follows:

```kotlin
class weiVButton(
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
