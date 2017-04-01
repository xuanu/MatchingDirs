package apk.zeffect.cn.matchingdirs;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.am_show_tv);
        init();
    }

    private void init() {
        appendText("当前设备为：" + (isTablet(this) ? "平板" : "手机"));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        appendText("当前设备分辨率为：" + dm.widthPixels + "x" + dm.heightPixels + "(没有加上虚拟按键的高度)");
        appendText("当前设备DPI为：" + dm.densityDpi + "dpi" + "，为mdpi的" + dm.density + "倍");
        appendText("当前设备匹配文件夹为：" + getResources().getString(R.string.values_match) + ",(平板只匹配了sw600dp的文件夹)");
        appendText("简单说明一下匹配规则：120->160dpi=ldpi,160->240dpi=mdpi,240-320dpi=hdpi,320->480dpi=xhdpi,480->640dpi=xxhpdi,>640dpi=xxxhdpi");
        appendText("匹配sw<N>dp的规则：用最短的边如：1280*800,240dpi的平板，那么应该匹配到：800f/(240f/160)=533dp,基线160dpi,那么这个平板应该能匹配到小于sw533dp的文件夹");
        appendText("其它的一些限定符：比如屏幕宽高，尺寸，方向之类的，都是能找到就找，找不到就找别的。");
    }


    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context 上下文
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private void appendText(String pText) {
        if (pText == null) {
            pText = "";
        }
        mTextView.setText(mTextView.getText().toString().trim() + "\n" + pText);
    }

}
