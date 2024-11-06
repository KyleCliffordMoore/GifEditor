package com.kylecliffordmoore.github.window.editor.editComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

public class EditComponentOptions {
	public static final BasicStroke DEFAULT_STROKE = new BasicStroke(4.5f);
	public static final Color DEFAULT_STROKE_COLOR = new Color(255, 0, 0, 125);
	public static final Color DEFAULT_FILL_COLOR = Color.BLUE;
	public static final double DEFAULT_ROTATION = 0d;
	public static final Dimension DEFAULT_SIZE = new Dimension(30, 30);
	
	private BasicStroke stroke;
	private Color strokeColor;
	private Color fillColor;
	private double rotation;
	
	public EditComponentOptions() {
		this.stroke = new BasicStroke(1f);
		this.strokeColor = new Color(DEFAULT_STROKE_COLOR.getRed(), DEFAULT_STROKE_COLOR.getGreen(), DEFAULT_STROKE_COLOR.getBlue(), DEFAULT_STROKE_COLOR.getAlpha());
		this.fillColor = new Color(DEFAULT_FILL_COLOR.getRed(), DEFAULT_FILL_COLOR.getGreen(), DEFAULT_FILL_COLOR.getBlue(), DEFAULT_FILL_COLOR.getAlpha());
		this.rotation = DEFAULT_ROTATION;
	}
	
	public EditComponentOptions(EditComponentOptions other) {
		deepCopy(other, this);
	}
	
	public BasicStroke getBasicStroke() { return stroke; }
	public Color getStrokeColor() { return strokeColor; }
	public Color getFillColor() { return fillColor; }
	public double getRotation() { return rotation; }
	
	public EditComponentOptions setDeepCopyBasicStroke(BasicStroke stroke) { 
		float[] dashedArray = stroke.getDashArray();
		float[] copyDashArray = null;
		if (dashedArray != null) {
			final int size_ = dashedArray.length;
			copyDashArray = new float[size_];
			for (int i = 0; i < size_; i++) copyDashArray[i] = dashedArray[i];
		}

		this.stroke = new BasicStroke(
				stroke.getLineWidth(),
				stroke.getEndCap(),
				stroke.getLineJoin(),
				stroke.getMiterLimit(),
				copyDashArray,
				stroke.getDashPhase()
			);
		
		return this;
	}
	public EditComponentOptions setDeepCopyStrokeColor(Color strokeColor) {
		this.strokeColor = new Color(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue(), strokeColor.getAlpha());
		return this;
	}
	public EditComponentOptions setDeepCopyFillColor(Color fillColor) { 
		this.fillColor = new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), fillColor.getAlpha());
		return this;
	}
	public EditComponentOptions setRotation(double rotation) { 
		this.rotation = rotation;
		return this;
	}
	
	@Override
	public String toString() {
		return 
				"EditComponentOption = [" +
				"BasicStroke stroke = " + stroke + ", " +
				"Color strokeColor = " + strokeColor + ", " +
				"Color fillColor = " + fillColor + ", " +
				"double rotation = " + rotation  + ", " +
				"]";
	}
	
	public void deepCopy(EditComponentOptions original, EditComponentOptions modified) {
		
		if (modified == null) modified = new EditComponentOptions();
		
		modified
		.setDeepCopyBasicStroke(original.stroke)
		.setDeepCopyFillColor(original.fillColor)
		.setRotation(original.rotation)
		.setDeepCopyStrokeColor(original.strokeColor);
	}
	
	public EditComponentOptions getDeepCopy() {
		EditComponentOptions toRet = new EditComponentOptions();;
		deepCopy(this, toRet);
		return toRet;
	}
	
}
