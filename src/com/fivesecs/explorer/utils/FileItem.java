package com.fivesecs.explorer.utils;

import java.io.File;

public class FileItem {
	private File file;
	private boolean isSelected;
//	private int currentPosition;

	public FileItem(File file, boolean bool) {
		this.file = file;
		this.isSelected = bool;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isSelected() {
		return isSelected;
	}
	
	

//	public int getCurrentPosition() {
//		return currentPosition;
//	}
//
//	public void setCurrentPosition(int currentPosition) {
//		this.currentPosition = currentPosition;
//	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
