package soundtest.com.soundtest.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by ${王sir} on 2017/6/16.
 * application  sp工具类
 */

public class SharedPreferencesUtils {

    public static void saveTosp(Context context, String className, String key, String value) {

        SharedPreferences sp = context.getSharedPreferences(className, context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putString(key, value);
        et.commit();
        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
    }

    public static String getDataFromSp(Context context,String className,String key) {
        SharedPreferences sp = context.getSharedPreferences(className, context.MODE_PRIVATE);

        return sp.getString(key,"-1");
    }
}
