package com.kylecliffordmoore.github.window.listeners;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

public final class GEFileDialogButtonActionListener implements ActionListener {
	
	Frame fileDialogParentFrame;
	
	public GEFileDialogButtonActionListener(Frame fileDialogParentFrame) {
		this.fileDialogParentFrame = fileDialogParentFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FileDialog fileDialog = new FileDialog(fileDialogParentFrame, "Select file", FileDialog.LOAD);
		fileDialog.setDirectory("C:\\");
		FilenameFilter filenameFilter = new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		    	
		    	return name.toLowerCase().endsWith(".gif");
		    }
		};
		fileDialog.setFilenameFilter(filenameFilter);
		fileDialog.setVisible(true);
		String fileName = fileDialog.getFile();
		String directory = fileDialog.getDirectory();

		GESynchronizedUploadedFileHolder.setFile(directory != null && fileName != null ? new File(directory + fileName) : null);
	}

}
