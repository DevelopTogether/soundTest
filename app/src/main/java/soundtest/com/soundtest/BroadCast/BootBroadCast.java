package soundtest.com.soundtest.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import soundtest.com.soundtest.MainActivity;

/**
 * Created by ${王sir} on 2017/6/16.
 * application 监听手机启动后的广播
 */

public class BootBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent mIntent = new Intent(context, MainActivity.class);
            context.startActivity(mIntent);
        }

    }
}
