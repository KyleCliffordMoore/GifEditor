package com.kylecliffordmoore.github.window.listeners;

import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public final class GEDropComponent extends JComponent {

	private static final long serialVersionUID = 1133772058311491718L;
	private static final String DIA_PATH = "/com/kylecliffordmoore/github/assets/dropImageArea.png";
	private static BufferedImage dropImageArea;	
	
	public GEDropComponent() {
		
		setDropTarget(new DropTarget() {

			private static final long serialVersionUID = -8281154390846124135L;

			@SuppressWarnings("unchecked")
			public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            
		            GESynchronizedUploadedFileHolder.setFile(droppedFiles.size() != 0 ? droppedFiles.get(0) : null);
		            
		            evt.dropComplete(true);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		dropImageArea = null;
		try {
			dropImageArea = ImageIO.read(getClass().getResource(DIA_PATH));
		} catch (IOException e) { e.printStackTrace(); }
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(dropImageArea, 0, 0, null);
	}
	
}
