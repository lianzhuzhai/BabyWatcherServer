package com.lianzhuzhai.babywatcher;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JFrame;

import com.lianzhuzhai.babywatcher.component.ImageFrame;

/**
 * 在服务器开启情况下，启动客户端，创建套接字接收图像
 */

public class Main {
	private static final int SOCKET_PORT = 6000;
	public static ServerSocket ss = null;

	public static void main(String args[]) throws IOException {
		ss = new ServerSocket(SOCKET_PORT);

		final ImageFrame frame = new ImageFrame(ss);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		while (true) {
			frame.panel.getimage();
			frame.repaint();
		}
	}

}
