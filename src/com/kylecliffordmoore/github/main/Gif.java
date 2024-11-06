package com.kylecliffordmoore.github.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;

public class Gif {

	private File file;

	public Gif(File file) {
		this.file = file;
	}

	public Gif(URI uri) {
		file = new File(uri);
	}
	
	public File getFile() {
		return file;
	}
	
	public URI uri() {
		return file.toURI();
	}
	
	// Cool links:
	// https://codereview.stackexchange.com/questions/113998/making-gifs-with-java
	// https://stackoverflow.com/questions/14488452/java-imagewriter-bufferedimage-to-gif
	
	public void imagesToGifFile(List<BufferedImage> images, String writePath) throws IOException {
		
		ImageWriter writer = ImageIO.getImageWritersByFormatName("gif").next();
		writer.setOutput(ImageIO.createImageOutputStream(new FileOutputStream(new File(writePath))));
		
		ImageWriteParam gifWriteParam = writer.getDefaultWriteParam();
		
		writer.prepareWriteSequence(null);
		
		for (int i = 0; i < images.size(); i++) {			
			writer.writeToSequence(new IIOImage(images.get(i), null, null), gifWriteParam);
		}
		
		writer.endWriteSequence();
		writer.dispose();
	}
	
	public List<BufferedImage> getFrames() throws IOException, URISyntaxException {

		List<BufferedImage> images = new ArrayList<BufferedImage>();
		File gifFile = new File(this.file.toURI());

		ImageInputStream imageInputStream = ImageIO.createImageInputStream(new FileInputStream(gifFile));

		// Gets an iterator of all the registered "imagereaders" that can decode the
		// ".gif" extension and then grabs the first one.
		ImageReader reader = ImageIO.getImageReadersBySuffix("gif").next();

		// Tell the reader from where to reader.
		reader.setInput(imageInputStream, false); // Does not seek forward.

		// For certain things such as gifs, the number of frames is not given.
		// setting the param for search to true, it will scan for the number of frames.
		final int numFrames = reader.getNumImages(true);

		Graphics2D masterGraphics = null;
		BufferedImage masterImage = null;
		for (int i = 0; i < numFrames; i++) {
			BufferedImage frame = null;
			int width, height;

			try {
				frame = reader.read(i);
				width = reader.getWidth(i); // this assumes consisent size
				height = reader.getHeight(i);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			if (masterImage == null) {
				masterImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				masterGraphics = masterImage.createGraphics();
				masterGraphics.setBackground(new Color(0, 0, 0, 0));
			}
			
			// Place the new set of pixels onto the current image
			masterGraphics.drawImage(frame, 0, 0, null);

			BufferedImage copy = new BufferedImage(masterImage.getColorModel(), masterImage.copyData(null),
					masterImage.isAlphaPremultiplied(), null);
			
			images.add(copy);
			
		}

		reader.dispose();
		imageInputStream.close();

		return images;
	}
	
	
}
