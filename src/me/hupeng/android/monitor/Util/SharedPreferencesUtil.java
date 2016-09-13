package me.hupeng.android.monitor.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用于读写配置的通用类
 */
public class SharedPreferencesUtil {
    /**
     * 写入key,value对
     * */
    public static void writeString(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * 写入key,value对
     * */
    public static void writeInt(Context context, String key, int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    /**
     * 读出value值
     * */
    public static String readString(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }


    /**
     * 读出value值
     * */
    public static int readInt(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,0);
    }

    /**
     * 读出value值
     * */
    public static int readInt(Context context, String key, int def){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,def);
    }
}
