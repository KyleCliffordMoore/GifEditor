package com.kylecliffordmoore.github.window.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;

import com.kylecliffordmoore.github.window.GECommonPanel;

public class SideBarPanel extends GECommonPanel {

	private static final long serialVersionUID = 1361176533377193567L;
	
	JButton paint;
	JButton square;
	JButton rectangle;
	JButton circle;
	
	public SideBarPanel() {
		paint = new JButton("This is a test");
		square = new JButton();
		rectangle = new JButton();
		circle = new JButton();
		
		setLayout(new GridLayout(4, 10));
		
		add(paint);
		add(square);
		add(rectangle);
		add(circle);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
//		paint.setBounds(getWidth() * (1/4), getHeight() / , getWidth() / 2, );
	}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
	}
	
	@Override
	public void paint(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;

		helper_changeBackground(graphics2D, new Color(0, 0, 0));
		graphics2D.setColor(new Color (50, 50, 50));
		graphics2D.fill(new Rectangle(0, 0, getWidth(), getHeight()));
	}
	
}
