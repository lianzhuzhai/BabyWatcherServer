package com.lianzhuzhai.babywatcher.component;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A panel that displays a tiled image
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	private ServerSocket ss;
	private Image image;
	private InputStream ins;

	public ImagePanel(ServerSocket ss) {
		this.ss = ss;
	}

	public void getimage() throws IOException {
		Socket s = this.ss.accept();
		this.ins = s.getInputStream();
		this.image = ImageIO.read(ins);
		this.ins.close();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image == null)
			return;
		g.drawImage(image, 0, 0, null);
	}

}
