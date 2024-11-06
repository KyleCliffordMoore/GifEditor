package com.kylecliffordmoore.github.window.listeners;

import com.kylecliffordmoore.github.window.editor.editComponents.EditComponent;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;



public class GEEditComponentsListener implements ComponentListener {
	Dimension prev;
	
	public void setPrevIfNull(int w, int h) { // I hate this as a solution
		if (prev == null) prev = new Dimension(w, h);
	}
	
	@Override
	public void componentShown(ComponentEvent e) {}
	
	@Override
	public void componentResized(ComponentEvent e) {
		JPanel jPanel = (JPanel) e.getComponent();
		
		double xoff = (jPanel.getWidth() -  prev.getWidth()) / 2d;
		double yoff = (jPanel.getHeight() -  prev.getHeight()) / 2d;
		for (Component component : jPanel.getComponents()) {
//			if (component instanceof EditComponent) { // Dont need this right now but may need it later
				EditComponent ec = (EditComponent) component;
				ec.setBounds(
						ec.getLocationDouble().getX() + xoff,
						ec.getLocationDouble().getY() + yoff,
						ec.getWidth(),
						ec.getHeight()
				);
//			}
		}
		
		prev.setSize(jPanel.getWidth(), jPanel.getHeight());
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {}
	
	@Override
	public void componentHidden(ComponentEvent e) {}
}
