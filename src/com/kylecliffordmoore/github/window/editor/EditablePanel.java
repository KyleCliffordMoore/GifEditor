package com.kylecliffordmoore.github.window.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import com.kylecliffordmoore.github.main.Gif;
import com.kylecliffordmoore.github.window.GECommonPanel;
import com.kylecliffordmoore.github.window.listeners.GEEditComponentsListener;

public class EditablePanel extends GECommonPanel implements MouseWheelListener, MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 8963607518942323474L;
	private static final int IMAGE_BORDER_SIZE = 5;
	
	private GEEditComponentsListener editComponentsListener;
	
	private List<BufferedImage> frames;
	private int index;
	
	private boolean zoomer;
	private boolean dragger;
	private boolean released;
	private double zoomFactor;
	private double prevZoomFactor;
    private double xOffset;
    private double yOffset;
    private int xDiff;
    private int yDiff;
    private Point startPoint;
    private AffineTransform affineTransform;
	
	public EditablePanel(Gif gif) {
		this.index = 0;
		try {
			this.frames = gif.getFrames();
		}
		catch (IOException e) { e.printStackTrace(); } 
		catch (URISyntaxException e) { e.printStackTrace(); } // may take a while to load gif frames
		
		this.zoomer = false;
		this.dragger = false;
		this.released = false;
		this.zoomFactor = 1;
		this.prevZoomFactor = 1;
		this.xOffset = 0;
		this.yOffset = 0;
		this.affineTransform = new AffineTransform();
		
		this.editComponentsListener =  new GEEditComponentsListener();
		
		addMouseWheelListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		addComponentListener(this.editComponentsListener);
		
		setLayout(null);
	}
	
	private void helper_paintImageBorder(Graphics2D graphics2D, Rectangle imageBounds) {
		Rectangle imageBorder = new Rectangle();
		imageBorder.x = imageBounds.x - IMAGE_BORDER_SIZE;
		imageBorder.y = imageBounds.y - IMAGE_BORDER_SIZE;
		imageBorder.width = imageBounds.width + IMAGE_BORDER_SIZE;
		imageBorder.height = imageBounds.height + IMAGE_BORDER_SIZE;
		
		graphics2D.setColor(Color.GRAY);
		graphics2D.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
		
		graphics2D.draw(imageBorder);
	}
	
	private void helper_doZooming(Graphics2D graphics2D) {
        if (zoomer) {

        	affineTransform = new AffineTransform();
        	
            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            affineTransform.translate(xOffset, yOffset);
            affineTransform.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            graphics2D.transform(affineTransform);
            zoomer = false;
        }
        

	}
	
	private void helper_doDragging(Graphics2D graphics2D) {
        if (dragger) {
        	affineTransform = new AffineTransform();
        	
        	affineTransform.translate(xOffset + xDiff, yOffset + yDiff);
        	affineTransform.scale(zoomFactor, zoomFactor);
            graphics2D.transform(affineTransform);

            if (released) {
                xOffset += xDiff;
                yOffset += yDiff;
                dragger = false;
            }

        }
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		editComponentsListener.setPrevIfNull(width, height);
		super.setBounds(x, y, width, height);
	}
	
	@Override
	public void setBounds(Rectangle r) {
		editComponentsListener.setPrevIfNull(r.width, r.height);
		super.setBounds(r);
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		
		zoomer = true;

        //Zoom in
        if (event.getWheelRotation() < 0) {
            zoomFactor *= 1.1;
            repaint();
        }
        //Zoom out
        if (event.getWheelRotation() > 0) {
            zoomFactor /= 1.1;
            repaint();
        }
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		if (SwingUtilities.isRightMouseButton(event)) {
		
			Point curPoint = event.getLocationOnScreen();
			xDiff = curPoint.x - startPoint.x;
			yDiff = curPoint.y - startPoint.y;

			dragger = true;
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent event) {}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO remove this just for testing rn
		if (SwingUtilities.isMiddleMouseButton(event)) {
			System.out.println("saving image");
			
			Rectangle temp = helper_GetCenter(frames.get(index).getWidth(), frames.get(index).getHeight());
			
	        BufferedImage image = new BufferedImage(temp.width, temp.height, BufferedImage.TYPE_INT_ARGB);
	        
	        // Get the Graphics2D object from the BufferedImage
	        Graphics2D g2d = image.createGraphics();
	        
	        g2d.translate(-temp.x, -temp.y);
	        // Paint the panel onto the BufferedImage
	        paintAll(g2d);
	        
	        try {
				ImageIO.write(image, "png", new File("C:\\Users\\Bonny_ly2xnnt\\OneDrive\\Documents\\img.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        // Dispose the graphics object
	        g2d.dispose();
		}
		
		if (SwingUtilities.isLeftMouseButton(event)) {	
			
			add(EditorOptions.createEditComponentInstance(
					MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x,
					MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y,
					affineTransform
				), 0
			);
			zoomer = true;
			repaint();

		}
	}
	
    @Override
    public void mousePressed(MouseEvent event) {
    	if (SwingUtilities.isRightMouseButton(event)) {
            released = false;
            startPoint = MouseInfo.getPointerInfo().getLocation();
    	}

    }

    @Override
    public void mouseReleased(MouseEvent event) {
    	
    	if (SwingUtilities.isRightMouseButton(event)) {
    		released = true;
        	repaint();
    	}
    }

	@Override
	public void mouseEntered(MouseEvent event) {}

	@Override
	public void mouseExited(MouseEvent event) {}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		
		helper_changeBackground(graphics2D, Color.BLACK);
		
		helper_doZooming(graphics2D);
		helper_doDragging(graphics2D);
		
		// Cool code here:
		Rectangle imageBounds = helper_GetCenter(frames.get(index).getWidth(), frames.get(index).getHeight());
		helper_paintImageBorder(graphics2D, imageBounds);
		
		graphics2D.drawImage(frames.get(index), imageBounds.x, imageBounds.y, null);
	}

}
