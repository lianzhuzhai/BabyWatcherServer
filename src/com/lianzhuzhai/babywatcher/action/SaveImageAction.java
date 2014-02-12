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

			// �ļ�ѡ�����Ե�ǰ��Ŀ¼��
			JFileChooser jfc = new JFileChooser(".");
			jfc.showSaveDialog(new javax.swing.JFrame());
			// ��ȡ��ǰ��ѡ���ļ�����
			File savedFile = jfc.getSelectedFile();

			// �Ѿ�ѡ�����ļ�
			if (savedFile != null) {
				// ��ȡ�ļ������ݣ�����ÿ���Կ�ķ�ʽ��ȡ����
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
			javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "�ѽӱ���ɹ�", "��ʾ!",
					javax.swing.JOptionPane.PLAIN_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}