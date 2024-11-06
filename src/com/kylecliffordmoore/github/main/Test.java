package com.kylecliffordmoore.github.main;

import java.io.File;

import com.kylecliffordmoore.github.window.GEFrame;
import com.kylecliffordmoore.github.window.listeners.GESynchronizedUploadedFileHolder;

public class Test {

	public static void main(String[] args) {
		
		// starts on main menu panel
		GEFrame frame = new GEFrame("Gif Editor Program v2.0.0.1", 1200, 800);

		File file = null;
		while (file == null) {
			try { 
				Thread.sleep(10);
				file = GESynchronizedUploadedFileHolder.getNewOrNull();
			} catch (InterruptedException e) { e.printStackTrace(); }
		}; // wait for file upload!
		
		System.out.println("File detected: " + file);
		
		frame.setToEditorPanel(new Gif(file));
		
		
	}

}
