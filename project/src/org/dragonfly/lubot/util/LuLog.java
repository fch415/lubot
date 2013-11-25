package org.dragonfly.lubot.util;

import android.util.Log;

public class LuLog {

	public final static String TAG = "Lubot";

	private static boolean isSlient = false;

	public static boolean isSlient() {
		return isSlient;
	}

	public static void setSlient(boolean is) {
		isSlient = is;
	}

	public static void d(String msg) {
		if (!isSlient)
			Log.d(TAG, msg);
	}

	public static void w(String msg) {
		if (!isSlient)
			Log.w(TAG, msg);
	}

	public static void i(String msg) {
		if (!isSlient)
			Log.i(TAG, msg);
	}

	public static void e(String msg) {
		if (!isSlient)
			Log.e(TAG, msg);
	}

}
