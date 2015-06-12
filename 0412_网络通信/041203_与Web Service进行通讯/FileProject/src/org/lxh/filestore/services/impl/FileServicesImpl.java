package org.lxh.filestore.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.lxh.filestore.services.IFileServices;

public class FileServicesImpl implements IFileServices {

	public String load(String fileName) throws Exception {
		File file = new File("D:" + File.separator + "userprofile"
				+ File.separator + fileName);
		if(!file.exists()){	// 文件不存在
			return null ;
		}
		StringBuffer buf = new StringBuffer() ;
		Scanner scan = new Scanner(new FileInputStream(file)) ;
		scan.useDelimiter("\n") ;
		while(scan.hasNext()){
			buf.append(scan.next()) ;
		}
		scan.close(); 
		return buf.toString() ;
	}

	public void save(String fileName, String content) throws Exception {
		File file = new File("D:" + File.separator + "userprofile"
				+ File.separator + fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		PrintStream out = new PrintStream(new FileOutputStream(file));
		out.print(content);
		out.close();
	}

}
