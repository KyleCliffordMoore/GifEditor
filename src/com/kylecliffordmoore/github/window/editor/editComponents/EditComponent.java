package com.kylecliffordmoore.github.window.editor.editComponents;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public abstract class EditComponent extends JComponent {

	private static final long serialVersionUID = -6476442932268503487L;

	protected Point2D.Double locationDouble;
	
	protected EditComponentOptions options;
	
	public EditComponent(Rectangle bounds, EditComponentOptions options) {
		setBounds(bounds);
		this.options = options;
	}
	
	public EditComponent(double x, double y, int w, int h, EditComponentOptions options) {
		setBounds(x, y, w, h);
		this.options = options;
	}
	
	public EditComponent(int x, int y, int w, int h, EditComponentOptions options) {
		setBounds(x, y, w, h);
		this.options = options;
	}
	
	public void setBounds(double x, double y, int width, int height) {
		locationDouble = new Point2D.Double(x, y);
		super.setBounds((int) x, (int) y, width, height);
	}
	
	public Point2D.Double getLocationDouble() {
		return locationDouble;
	}
	
	/***
	 * @see setBounds(double x, double y, int width, int height)
	 * */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		locationDouble = new Point2D.Double(x, y);
		super.setBounds(x, y, width, height);
	}
	
	/***
	 * @see setBounds(double x, double y, int width, int height)
	 * */
	@Override
	public void setBounds(Rectangle r) {
		locationDouble = new Point2D.Double(r.getX(), r.getY());
		super.setBounds(r);
	}
}
