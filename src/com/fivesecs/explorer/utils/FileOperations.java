package com.fivesecs.explorer.utils;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class FileOperations {

	public static void openFile(Context context, File file) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), getMIMEType(file));
		((Activity) context).startActivity(intent);
	}

	public static String getMIMEType(File file) {
		String type = "";
		String fileName = file.getName();
		String fileEnds = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
		if (fileEnds.equals("m4a") || fileEnds.equals("mp3") || fileEnds.equals("mid") || fileEnds.equals("xmf")
				|| fileEnds.equals("ogg") || fileEnds.equals("wav")) {
			type = "audio/*";
		} else if (fileEnds.equals("3gp") || fileEnds.equals("mp4")) {
			type = "video/*";
		} else if (fileEnds.equals("jpg") || fileEnds.equals("gif") || fileEnds.equals("png") || fileEnds.equals("jpeg")
				|| fileEnds.equals("bmp")) {
			type = "image/*";
		} else {
			type = "*/*";
		}
		return type;
	}
}
