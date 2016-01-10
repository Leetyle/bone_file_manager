package com.fivesecs.explorer.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fivesecs.explorer.DirFragment;
import com.fivesecs.explorer.MainActivity;

import android.app.Fragment;
import android.util.Log;

public class ExpolererUtils {
	public static List<File> getFileList(File filePath) {
		Log.d(MainActivity.tag, "in ExporerUtils.getFileList, filePath: " + filePath.toString());
		File resultPath = filePath;
		List<File> fileList;
		if (filePath.toString().equals(File.separator)) {
			fileList = Arrays.asList(resultPath.listFiles());
		} else {

			fileList = new ArrayList<File>(Arrays.asList(resultPath.listFiles()));
			Log.d(MainActivity.tag, "Clicked file's parent: " + resultPath.getParent());
			fileList.add(0, new File(resultPath.getParent()));
		}
		for (File file : fileList) {
			Log.d(MainActivity.tag, file.getName());
		}
		return fileList;
	}

	public static List<FileItem> getFileItemList(File filePath) {
		List<FileItem> fileItemList = new ArrayList<FileItem>();
		if (filePath.toString().equals(File.separator)) {
			for (File file : filePath.listFiles()) {
				fileItemList.add(new FileItem(file, false));
			}
		} else {
			for (File file : filePath.listFiles()) {
				fileItemList.add(new FileItem(file, false));
			}
			fileItemList.add(0, new FileItem(new File(filePath.getParent()), false));
		}
		return fileItemList;
	}

	public static void deleteFiles(List<File> selectedFiles) {
		if(selectedFiles != null & selectedFiles.size() > 0) {
			for(File file : selectedFiles) {
				if(file.isFile()) {
					file.delete();
				} else {
					deleteDir(file);
//					file.delete();
				}
			}
		}		
	}

	private static void deleteDir(File dir) {
		File[] files = dir.listFiles();
		if(files!= null & files.length > 0) {
			for (File file : files) {
				if(file.isFile()) {
					file.delete();
				} else if(file.isDirectory()) {
					deleteDir(file);
				}
			}
		}
		dir.delete();
	}

}
