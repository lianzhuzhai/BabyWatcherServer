package com.lianzhuzhai.babywatcher.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.lianzhuzhai.babywatcher.util.DateUtils;

/**
 * @author ligangbj7466
 * 
 */
public class ScheduleThread {
	private static final String PHONE_KEEP_URL = "D:\\watcherPhone\\";
	private static final long PHONE_DELAY_MIN = 0;
	private static final long PHONE_PERIOD_MIN = 5;
	private static ScheduleThread instance = null;

	public static ScheduleThread getInstance() {
		if (null == instance) {
			return new ScheduleThread();
		}
		return instance;
	}

	private ScheduleThread() {

	}

	// 启用定时线程将图像数据保存下来
	public void startPhoneThread(final ServerSocket ss) {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		Runnable phoneMe = new Runnable() {
			public void run() {
				savePhone();
			}

			private void savePhone() {
				try {
					RandomAccessFile inFile = null;
					byte[] byteBuffer = new byte[1024];

					Socket s = ss.accept();
					InputStream ins = s.getInputStream();

					File savedFile = new File(PHONE_KEEP_URL + DateUtils.formatDate(new Date(), DateUtils.format5)
							+ ".jpg");
					// 已经选择了文件
					if (savedFile.createNewFile()) {
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
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		ses.scheduleAtFixedRate(phoneMe, PHONE_DELAY_MIN, PHONE_PERIOD_MIN, TimeUnit.MINUTES);
	}
}
