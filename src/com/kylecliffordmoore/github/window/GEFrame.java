package com.kylecliffordmoore.github.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kylecliffordmoore.github.main.Gif;
import com.kylecliffordmoore.github.window.editor.EditorPanel;
import com.kylecliffordmoore.github.window.listeners.GEComponentListener;


public class GEFrame extends JFrame {

	private static final long serialVersionUID = -2043010104971906759L;
	
	private JPanel currentPanel;
	
	public GEFrame(String windowTitle, int windowWidth, int windowHeight) {
		
		super(windowTitle);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(
				(screenDimensions.width - windowWidth) >> 1,
				(screenDimensions.height - windowHeight) >> 1,
				windowWidth,
				windowHeight
			);
		
		setVisible(true);
		
		currentPanel = new GEMainMenu();
		currentPanel.setLayout(null);
		currentPanel.setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		getContentPane().add(currentPanel);
		
		addComponentListener(new GEComponentListener());
		
		validate();
	}
	
	public void setToEditorPanel(Gif gif) {
		remove(currentPanel);
		currentPanel = new EditorPanel(gif);
		currentPanel.setLayout(null);
		currentPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
		
		getContentPane().add(currentPanel);
		validate();
	}
	
}
