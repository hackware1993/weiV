package cn.flutterfirst.weiv_example;

import cn.flutterfirst.weiv.core.WeiV;
import cn.flutterfirst.weiv.wrappers.linearlayout.FlexDirection;

public class WeiVJavaCounter extends AppWeiVJavaActivity {
    private int count = 0;
    private int maxCount = 10;
    private int minCount = 0;

    @Override
    public WeiV build() {
        return WeiV(() -> {
            Flex((it) -> {
                it.wOrientation(FlexDirection.VERTICAL);

                Button().wText("Add count").wOnClick(v -> {
                    setState(() -> {
                        if (count < maxCount) {
                            count++;
                        }
                    });
                });

                Button().wText("Sub count").wOnClick(v -> {
                    setState(() -> {
                        if (count > minCount) {
                            count--;
                        }
                    });
                });

                Text().wText("count = " + count);
            });
        });
    }
}
