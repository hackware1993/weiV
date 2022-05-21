package cn.flutterfirst.weiv_example.embed;

import android.content.Context;
import android.util.AttributeSet;

import cn.flutterfirst.weiv.core.WeiV;
import cn.flutterfirst.weiv.wrappers.linearlayout.FlexDirection;

public class WeiVCounterJavaView extends BaseWeiVJavaView {
    private int count = 0;
    private int maxCount = 10;
    private int minCount = 0;

    public WeiVCounterJavaView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

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
