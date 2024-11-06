package com.kylecliffordmoore.github.window.editor;

import java.awt.Rectangle;

import com.kylecliffordmoore.github.main.Gif;
import com.kylecliffordmoore.github.window.GECommonPanel;
import com.kylecliffordmoore.github.window.listeners.GEComponentListener;

public final class EditorPanel extends GECommonPanel {

	private static final long serialVersionUID = -8907865813454629016L;
	
	SideBarPanel sideBar;
	EditablePanel editPanel;
	
	public EditorPanel(Gif gif) {
		
		addComponentListener(new GEComponentListener());
		
		sideBar = new SideBarPanel();
		add(sideBar);
		
		editPanel = new EditablePanel(gif);
		add(editPanel);
		
	}
	
	private void helper_resetBounds() {
		sideBar.setBounds(new Rectangle((int) (getWidth() * 7.0 / 8), 0, getWidth(), getHeight()));
		editPanel.setBounds(new Rectangle(0, 0, (int) (getWidth() * 7.0 / 8) + 1, getHeight()));
		
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
	
}
