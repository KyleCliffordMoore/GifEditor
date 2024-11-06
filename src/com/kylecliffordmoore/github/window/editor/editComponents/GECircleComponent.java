package com.kylecliffordmoore.github.window.editor.editComponents;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GECircleComponent extends EditComponent {

	public GECircleComponent(double x, double y, int w, int h, EditComponentOptions options) {
		super(x, y, w, h, options);
		
	}

	public GECircleComponent(int x, int y, int w, int h, EditComponentOptions options) {
		super(x, y, w, h, options);
	}

	public GECircleComponent(Rectangle bounds, EditComponentOptions options) {
		super(bounds, options);
	}



	private static final long serialVersionUID = 8282925953739750276L;
	

	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		
		BasicStroke stroke = options.getBasicStroke();
		int strokeOffset = (int) Math.ceil(stroke.getLineWidth() / 2);
		
		graphics2D.setColor(options.getFillColor());
		graphics2D.fillOval(strokeOffset, strokeOffset, getWidth() - 2 * strokeOffset, getHeight() - 2 * strokeOffset);
		
		graphics2D.setColor(options.getStrokeColor());
		graphics2D.setStroke(stroke);
		graphics2D.drawOval(strokeOffset, strokeOffset, getWidth() - 2 * strokeOffset, getHeight() - 2 * strokeOffset);
		
		
	}
	
}
