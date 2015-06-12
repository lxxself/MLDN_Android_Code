package org.lxh.server;

import java.net.ServerSocket;

public class MyServer {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8888); // 服务器端端口
		boolean flag = true; // 定义标记，可以一直死循环
		while (flag) { // 通过标记判断循环
			new Thread(new ServerThreadUtil(server.accept())).start(); // 启动线程
		}
		server.close(); // 关闭服务器
	}

}
