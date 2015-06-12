package org.lxh.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8888);
		Socket client = server.accept(); // 接收客户端请求
		PrintStream out = new PrintStream(client.getOutputStream());
		BufferedReader buf = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		StringBuffer info = new StringBuffer() ;
		info.append("Android : ") ;
		info.append(buf.readLine()) ;
		out.print(info) ;
		out.close() ;
		buf.close() ;
		client.close() ;
		server.close() ;
	}

}
