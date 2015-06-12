package org.lxh.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

import org.lxh.util.UploadFile;

public class ServerThreadUtil implements Runnable {
	private static final String DIRPATH = "D:" + File.separator + "mldnfile"
			+ File.separator; // 目录路径
	private Socket client = null;
	private UploadFile upload = null;

	public ServerThreadUtil(Socket client) {
		this.client = client;
		System.out.println("新的客户端连接...");
	}

	@Override
	public void run() {
		try {
			PrintStream out = new PrintStream(this.client.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(
					client.getInputStream()); // 反序列化
			this.upload = (UploadFile) ois.readObject(); // 读取对象
			System.out.println("文件标题：" + this.upload.getTitle());
			System.out.println("文件类型：" + this.upload.getMimeType());
			System.out.println("文件大小：" + this.upload.getContentLength());
			out.print(this.saveFile()) ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean saveFile() throws Exception { // 负责文件内容的保存
		File file = new File(DIRPATH + UUID.randomUUID() + "."
				+ this.upload.getExt());
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}
		OutputStream output = null;
		try {
			output = new FileOutputStream(file) ;
			output.write(this.upload.getContentData()) ;
			return true ;
		} catch (Exception e) {
			throw e;
		} finally {
			output.close();
		}
	}
}
