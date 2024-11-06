package com.kylecliffordmoore.github.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public abstract class GECommonPanel extends JPanel {

	private static final long serialVersionUID = 8692221045053841215L;

	protected Rectangle helper_GetCenter(int w, int h) {
		return new Rectangle((getWidth() - w) >> 1, (getHeight() - h) >> 1, w, h);
	}
	
	protected void helper_changeBackground(Graphics2D graphics2D, Color c) {
		graphics2D.setColor(c);
		graphics2D.fillRect(0, 0, getWidth(), getHeight());
	}
	
}
