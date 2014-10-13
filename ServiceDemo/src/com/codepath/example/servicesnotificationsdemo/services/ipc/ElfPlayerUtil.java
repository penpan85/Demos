package com.codepath.example.servicesnotificationsdemo.services.ipc;

import java.io.File;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class ElfPlayerUtil {

	// 获取SD卡中elfPlayer目录中的某个文件
	public static Uri getFileinSD(String filename) {

		// 如果SD卡可以读写
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

			// 获取SD卡的目录
			File sdDir = Environment.getExternalStorageDirectory();

			Log.i("yao", "sdDir ==> " + sdDir.toString());
			// 获取文件对象
			File file = new File(sdDir, File.separator + "Download" + File.separator + filename);
			Uri uri = Uri.parse("file://" + file.getAbsolutePath());
			Log.i("yao", "uri ==> " + uri.toString());
			return uri;

		} else {
			// 如果无法读取则返回空
			return null;
		}

	}
}
