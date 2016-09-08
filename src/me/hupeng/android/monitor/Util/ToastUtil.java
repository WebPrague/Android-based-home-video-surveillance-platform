package me.hupeng.android.monitor.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * 用于弹框的工具类
 * @author HUPENG
 */
public class ToastUtil {
    /**
     * 弹框
     * */
    public static void toast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
