package com.kylecliffordmoore.github.window.listeners;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GEComponentListener implements ComponentListener {
	
	@Override
    public void componentResized(ComponentEvent evt) {
    	Component c = (Component)evt.getSource();
    	c.setSize(c.getWidth(), c.getHeight());
    }

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}
}
