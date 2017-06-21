package soundtest.com.soundtest.Utils;


import android.content.Context;

import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PublicUtils {
	public static float dbCount = 40;

	private static float lastDbCount = dbCount;
	private static float min = 0.5f;  //设置声音最低变化
	private static float value = 0;   // 声音分贝值
	public static String HigherAirplaneModePref1 = "settings put global airplane_mode_on ";
	public static String HigherAirplaneModePref2 = "am broadcast -a android.intent.action.AIRPLANE_MODE --ez state ";


	public static float setDbCount(float dbValue) {
		if (dbValue > lastDbCount) {
			value = dbValue - lastDbCount > min ? dbValue - lastDbCount : min;
		}else{
			value = dbValue - lastDbCount < -min ? dbValue - lastDbCount : -min;
		}
		dbCount = lastDbCount + value * 0.2f ; //防止声音变化太快
		lastDbCount = dbCount;
		return dbCount;
	}
	// 判断是否是飞行模式
	public static boolean AirplaneModeisOff(Context context) {

		return android.provider.Settings.System.getInt(
				context.getContentResolver(),
				android.provider.Settings.System.AIRPLANE_MODE_ON, 0) == 0;
	}

	/**
	 * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
	 *
	 * @return 应用程序是/否获取Root权限
	 */
	public static boolean upgradeRootPermission(String pkgCodePath) {
		Process process = null;
		DataOutputStream os = null;
		try {
			String cmd="chmod 777 " + pkgCodePath;
			process = Runtime.getRuntime().exec("su"); //切换到root帐号
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(cmd + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

	public static  void setSettingsOnHigh( int value)
	{
		// this should execute as system app, with write_secure_settings
		// permission
		// common app, can NOT do this
		// Settings.Global.putInt(
		// context.getContentResolver(),
		// Settings.Global.AIRPLANE_MODE_ON, value);
		String commond  = HigherAirplaneModePref1 + value + ";";
		//settings put global airplane_mode_on 1;am broadcast -a android.intent.action.AIRPLANE_MODE --ez state true
		//settings put global airplane_mode_on 0;am broadcast -a android.intent.action.AIRPLANE_MODE --ez state false
		if(value == 1)
			commond += HigherAirplaneModePref2 + "true";
		else
			commond += HigherAirplaneModePref2 + "false";
		String result = ShellUtil.runRootCmd(commond);
	}

	/**
	 * 获取下次提醒的时间,minute分后
	 */
	public static String GetNextWarnTime(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		Date date = calendar.getTime();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return time;
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return time;
	}
}
