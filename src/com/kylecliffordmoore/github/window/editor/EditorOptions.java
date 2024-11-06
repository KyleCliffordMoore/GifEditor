package com.kylecliffordmoore.github.window.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import com.kylecliffordmoore.github.window.editor.editComponents.EditComponent;
import com.kylecliffordmoore.github.window.editor.editComponents.EditComponentOptions;
import com.kylecliffordmoore.github.window.editor.editComponents.GECircleComponent;
import com.kylecliffordmoore.github.window.editor.editComponents.GEToolSelector;

public final class EditorOptions {
	
	private static GEToolSelector currentTool = GEToolSelector.CIRCLE;
	private static boolean scaleCompWithZoom = false;
	private static BasicStroke stroke = EditComponentOptions.DEFAULT_STROKE;
	private static Color strokeColor = EditComponentOptions.DEFAULT_STROKE_COLOR; 
	private static Color fillColor = EditComponentOptions.DEFAULT_FILL_COLOR;
	private static double rotation = EditComponentOptions.DEFAULT_ROTATION;
	private static Dimension sizeDimensions = EditComponentOptions.DEFAULT_SIZE;
	
	private EditorOptions() {} // Do Nothing
	
	public static void setCurrentTool(GEToolSelector currentTool_) { currentTool = currentTool_; }
	public static void setScaleCompWithZoom(boolean flag) { scaleCompWithZoom = flag; }
	public static void setBasicStroke(BasicStroke stroke_) { stroke = stroke_; }
	public static void setStrokeColor(Color strokeColor_) { strokeColor = strokeColor_; }
	public static void setFillColor(Color fillColor_) { fillColor = fillColor_; }
	public static void setRotation(double rotation_) { rotation = rotation_; }
	public static void setDimension(Dimension sizeDimensions_) { sizeDimensions = sizeDimensions_; }
	
	public static GEToolSelector getCurrentTool() { return currentTool; }
	public static boolean getScaleCompWithZoom() { return scaleCompWithZoom; }
	public static BasicStroke getBasicStroke() { return stroke; }
	public static Color getStrokeColor() { return strokeColor; }
	public static Color getFillColor() { return fillColor; }
	public static double getRotation() { return rotation; }
	public static Dimension getDimension() { return sizeDimensions; }
	
	private static EditComponentOptions getNewEditComponentOptions() {
		return (new EditComponentOptions())
				.setDeepCopyBasicStroke(stroke)
				.setDeepCopyFillColor(fillColor)
				.setDeepCopyStrokeColor(strokeColor)
				.setRotation(rotation);
	}
	
	// TODO add all shapes
	public static EditComponent createEditComponentInstance(double x, double y, AffineTransform affineTransform) {

		try {
			AffineTransform inverseAffineTransform = affineTransform.createInverse();
			double scaleAmount = inverseAffineTransform.getScaleX();
			Rectangle componentBounds = inverseAffineTransform
					.createTransformedShape(
						new Rectangle((int) x, (int) y, sizeDimensions.width, sizeDimensions.height)
					).getBounds();
			
			EditComponentOptions currentOptionsCopy = getNewEditComponentOptions();
			
			if (scaleCompWithZoom) {
				/* for stroke */
				
				BasicStroke stroke = currentOptionsCopy.getBasicStroke();
				float dashphase = (float) (stroke.getDashPhase() * scaleAmount);
				float wid = (float) (currentOptionsCopy.getBasicStroke().getLineWidth() * scaleAmount);
				
				// create new stroke because it can not have values changed
				// Dashed array can be the same (does not matter it is same address as it should not be called from anywhere else)
				// may have to scale dashed array at somepoint?
				// not sure
				stroke = new BasicStroke(wid, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), stroke.getDashArray(), dashphase);
				currentOptionsCopy.setDeepCopyBasicStroke(stroke);
				
			} else {
				componentBounds.setBounds(componentBounds.x, componentBounds.y, sizeDimensions.width, sizeDimensions.height);
			}
			
			EditComponent comp = switch (currentTool) {
				case CUSTOM:
					yield null;
				case TEXT:
					yield null;
				case PAINT:
					yield null;
				case SELECT:
					yield null;
				case RECTANGLE:
					yield null;
				case OVAL:
					yield null;
				case SQUARE:
					componentBounds.height = componentBounds.width;
					yield null;
				case CIRCLE:
					componentBounds.height = componentBounds.width;
					yield new GECircleComponent(componentBounds, currentOptionsCopy);
				default:
					yield null;
			};
			
			return comp;
			
		} catch (NoninvertibleTransformException err) { // should always have an inverse
			err.printStackTrace();
			return null;
		} 
		
	}
	
}
