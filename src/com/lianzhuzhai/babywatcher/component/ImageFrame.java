package com.lianzhuzhai.babywatcher.component;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.lianzhuzhai.babywatcher.action.SaveImageAction;
import com.lianzhuzhai.babywatcher.thread.ScheduleThread;

/**
 * A frame with an image panel
 */
@SuppressWarnings("serial")
public class ImageFrame extends JFrame {
	private static final String BUTTON_TEXT = "ÅÄÕÕ";
	private static final int PANEL_WIDTH = 640;
	private static final String FRAME_TITLE = "BabyWatcher";
	private static final int DEFAULT_WIDTH = 640;
	private static final int DEFAULT_HEIGHT = 560;

	public ImagePanel panel = null;
	public JButton jb = null;

	public ImageFrame(final ServerSocket ss) {
		// get screen dimensions
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		// center frame in screen
		setTitle(FRAME_TITLE);
		setLocation((screenWidth - DEFAULT_WIDTH) / 2, (screenHeight - DEFAULT_HEIGHT) / 2);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		addPanel(ss);
		addButton(ss);
		ScheduleThread.getInstance().startPhoneThread(ss);
	}

	private void addPanel(final ServerSocket ss) {
		this.getContentPane().setLayout(null);
		panel = new ImagePanel(ss);
		panel.setSize(PANEL_WIDTH, 480);
		panel.setLocation(0, 0);
		add(panel);
	}

	private void addButton(final ServerSocket ss) {
		jb = new JButton(BUTTON_TEXT);
		jb.setBounds(0, 480, 640, 50);
		add(jb);

		// Ìí¼Ó»Øµ÷
		SaveImageAction saveaction = new SaveImageAction(ss);
		jb.addActionListener(saveaction);
	}

	

}