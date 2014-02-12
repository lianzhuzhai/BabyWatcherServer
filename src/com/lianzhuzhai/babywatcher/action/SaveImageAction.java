package com.lianzhuzhai.babywatcher.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;

public class SaveImageAction implements ActionListener {
	RandomAccessFile inFile = null;
	byte byteBuffer[] = new byte[1024];
	InputStream ins;
	private ServerSocket ss;

	public SaveImageAction(ServerSocket ss) {
		this.ss = ss;
	}

	public void actionPerformed(ActionEvent event) {
		try {
			Socket s = ss.accept();
			ins = s.getInputStream();

			// 文件选择器以当前的目录打开
			JFileChooser jfc = new JFileChooser(".");
			jfc.showSaveDialog(new javax.swing.JFrame());
			// 获取当前的选择文件引用
			File savedFile = jfc.getSelectedFile();

			// 已经选择了文件
			if (savedFile != null) {
				// 读取文件的数据，可以每次以快的方式读取数据
				try {
					inFile = new RandomAccessFile(savedFile, "rw");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			int amount;
			while ((amount = ins.read(byteBuffer)) != -1) {
				inFile.write(byteBuffer, 0, amount);
			}
			inFile.close();
			ins.close();
			s.close();
			javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "已接保存成功", "提示!",
					javax.swing.JOptionPane.PLAIN_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}