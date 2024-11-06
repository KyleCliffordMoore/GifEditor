package com.kylecliffordmoore.github.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.kylecliffordmoore.github.window.listeners.GEComponentListener;
import com.kylecliffordmoore.github.window.listeners.GEDropComponent;
import com.kylecliffordmoore.github.window.listeners.GEFileDialogButtonActionListener;

public final class GEMainMenu extends GECommonPanel {

	private static final long serialVersionUID = -2436338355416987232L;


	GEDropComponent dropComponent;
	JButton button;
	
	public GEMainMenu() {
		
		addComponentListener(new GEComponentListener());
		
		dropComponent = new GEDropComponent();
		add(dropComponent);
		
		button = new JButton("Choose file");
		button.addActionListener(new GEFileDialogButtonActionListener((JFrame) this.getParent())); // Parent container
		add(button);
	}
	
	private void helper_resetBounds() {
		dropComponent.setBounds(helper_GetCenter(300, 300));
		Rectangle rect = helper_GetCenter(100, 50);
		rect.y += dropComponent.getHeight() / 1.5;
		button.setBounds(rect);
	}
	
	@Override
	public void setBounds(Rectangle r) {
		helper_resetBounds();
		super.setBounds(r);
	}
	@Override
	public void setBounds(int x, int y, int width, int height) {
		helper_resetBounds();
		super.setBounds(x, y, width, height);
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;

		helper_changeBackground(graphics2D, new Color(50,50,50));
		
	}

	
}