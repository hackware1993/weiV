package cn.flutterfirst.weiv_example;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jetbrains.annotations.NotNull;

import cn.flutterfirst.weiv.core.WeiV;
import cn.flutterfirst.weiv.core.others.IParamChangedCallback;
import cn.flutterfirst.weiv.core.widgets.StatefulWidget;
import cn.flutterfirst.weiv.wrappers.linearlayout.FlexDirection;

public class WeiVCounterJavaActivity extends BaseWeiVJavaActivity {
    private int count = 0;
    private int maxCount = 10;
    private int minCount = 0;
    private String url = "https://baidu.com";

    private void moduleItem(int index) {
        Button().wText("This button is from module, " + index);
    }

    @Override
    public WeiV build(int buildCount) {
        return WeiV(() -> {
            Flex((it) -> {
                it.wOrientation(FlexDirection.VERTICAL);

                Flex(() -> {
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

                    Button().wText("Change app skin, isLight = " + SkinManager.isLight()).wOnClick(v -> {
                        SkinManager.changeSkin();
                    });
                });

                Text().wText("count = " + count);

                // Widgets wrapped by Const will not be updated
                Const(buildCount, () -> {
                    Text().wText("Widgets wrapped by Const will not be updated, count = " + count);
                });

                Stateful(new StatefulWidget.State() {
                    private int innerCount = 0;

                    @NotNull
                    @Override
                    public WeiV build(int buildCount) {
                        return WeiV(() -> {
                            Button().wText("This button maintains state alone, innerCount = " + innerCount).wOnClick(v -> {
                                setState(() -> {
                                    innerCount++;
                                });
                            });
                        });
                    }
                });

                for (int i = 0; i < 3; i++) {
                    moduleItem(i);
                }

                merge(WeiV(() -> {
                    Text().wText("Merge with outer layer");
                }));

                Button().wText("Change WebView url").wOnClick(v -> {
                    setState(() -> {
                        url = "https://flutterfirst.cn";
                    });
                });

                XmlView(() -> {
                    WebView webView = new WebView(WeiVCounterJavaActivity.this);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            if (url.startsWith("http")) {
                                return false;
                            } else {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                                return true;
                            }
                        }
                    });
                    return webView;
                }, (IParamChangedCallback<WebView, String>) (webView, url, first) -> {
                    if (first) {
                        webView.loadUrl(url);
                        webView.setTag(R.id.current_url, url);
                    } else {
                        String currentUrl = (String) webView.getTag(R.id.current_url);
                        if (!currentUrl.equals(url)) {
                            webView.loadUrl(url);
                            webView.setTag(R.id.current_url, url);
                        }
                    }
                }).wParam(url);
            });
        });
    }
}
