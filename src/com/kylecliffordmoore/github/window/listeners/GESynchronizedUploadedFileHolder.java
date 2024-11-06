package com.kylecliffordmoore.github.window.listeners;

import java.io.File;

public final class GESynchronizedUploadedFileHolder {

	private static File file;
	private static boolean isNew = false;
	
	protected static synchronized void setFile(File file_) {
		file = file_;
		isNew = true;
	}
	
	public static synchronized File getNewOrNull() {
		if (!isNew) return null;
		isNew = false;
		return file;
	}
	
}
