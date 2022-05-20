package cn.flutterfirst.weiv_example;

import org.jetbrains.annotations.NotNull;

import cn.flutterfirst.weiv.core.WeiV;
import cn.flutterfirst.weiv.core.widgets.StatefulWidget;
import cn.flutterfirst.weiv.wrappers.linearlayout.FlexDirection;

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

                Stateful().wState(new StatefulWidget.State() {
                    private int innerCount = 0;

                    @NotNull
                    @Override
                    public WeiV build() {
                        return WeiV(() -> {
                            Button().wText("This button maintains state alone, innerCount = " + innerCount).wOnClick(v -> {
                                setState(() -> {
                                    innerCount++;
                                });
                            });
                        });
                    }
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
