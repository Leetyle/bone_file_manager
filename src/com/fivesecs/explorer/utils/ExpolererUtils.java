package com.fivesecs.explorer.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fivesecs.explorer.Facade;

import android.util.Log;

public class ExpolererUtils {
	public static List<File> getFileList(File filePath) {
		Log.d(Facade.tag, "in ExporerUtils.getFileList, filePath: " + filePath.toString());
		File resultPath = filePath;
		List<File> fileList;
		if(filePath.toString().equals(File.separator)){
			fileList = Arrays.asList(resultPath.listFiles());
		} else {
			
			fileList = new ArrayList<File>(Arrays.asList(resultPath.listFiles()));
			Log.d(Facade.tag, "Clicked file's parent: " + resultPath.getParent());
			fileList.add(0, new File(resultPath.getParent()));
		}
		for(File file: fileList) {
			Log.d(Facade.tag, file.getName());
		}
		return fileList;
	}
	
}
